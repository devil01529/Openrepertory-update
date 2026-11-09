"""Resumable, single-book comparison of the GitHub OOREP SQL dump.
Does not execute SQL. Reads only clinical tables; writes reports locally.
"""
import argparse,collections,gzip,hashlib,json,sqlite3
from pathlib import Path

def sha(p):return hashlib.sha256(Path(p).read_bytes()).hexdigest()
def walk(rows):
 for r in rows:
  yield r
  yield from walk(r.get('sub',[]))
def unescape(s):
 if s==r'\N':return None
 import re
 return re.sub(r'\\([\\tnr])',lambda m:{'t':'\t','n':'\n','r':'\r','\\':'\\'}[m[1]],s)
def run(dump,bookfile,output):
 output.mkdir(parents=True,exist_ok=True)
 book=json.loads(bookfile.read_text());source=book['oorep_abbrev']
 identity=json.dumps({'dump_sha256':sha(dump),'apk_book_sha256':sha(bookfile),'source':source},sort_keys=True)
 db=sqlite3.connect(output/'checkpoint.sqlite')
 db.execute('create table if not exists state(identity text)')
 old=db.execute('select identity from state').fetchone()
 if old and old[0]!=identity:raise ValueError('Inputs changed; use a fresh checkpoint directory')
 if not old:db.execute('insert into state values (?)',(identity,));db.commit()
 db.execute('create table if not exists results(position integer primary key, result text)')
 rubrics={};links=collections.defaultdict(set);names={};catalog=[];table=''
 with gzip.open(dump,'rt') as f:
  for line in f:
   if line.startswith('COPY '):table=line.split()[1];continue
   if line=='\\.\n':table='';continue
   if table not in ['public.info','public.remedy','public.rubric','public.rubricremedy']:continue
   fields=[unescape(x) for x in line.rstrip('\n').split('\t')]
   if table=='public.info':catalog.append(fields[0])
   elif table=='public.remedy':names[fields[0]]=fields[1]
   elif table=='public.rubric' and fields[0]==source:rubrics[fields[1]]=fields
   elif table=='public.rubricremedy' and fields[0]==source:links[fields[1]].add((fields[2],int(fields[3])))
 if source not in catalog:
  result={'status':'SOURCE_NOT_IN_GITHUB_DUMP','source':source,'available':catalog,'identity':json.loads(identity)}
  (output/'report.json').write_text(json.dumps(result,indent=2)+'\n');return result
 rows=[r for c in book['chapters'] for r in list(walk(c['rubrics']))+c.get('indexRows',[]) if r.get('sourceIds')]
 start=db.execute('select coalesce(max(position),-1)+1 from results').fetchone()[0]
 for position in range(start,len(rows)):
  row=rows[position];ids=list(map(str,row['sourceIds']))
  absent=[i for i in ids if i not in rubrics]
  expected={(names[rem],g) for sid in ids for rem,g in links[sid] if rem in names}
  actual={(r['r'],r['g']) for r in row.get('remedies',[])}
  detail={'node_id':row.get('id'),'source_ids_not_in_dump':absent,'associations_only_in_dump':sorted(expected-actual),'associations_only_in_apk':sorted(actual-expected)}
  db.execute('insert into results values (?,?)',(position,json.dumps(detail)))
  if position%500==0:db.commit()
 db.commit()
 packaged={str(i) for r in rows for i in r['sourceIds']}
 results=[json.loads(x[0]) for x in db.execute('select result from results order by position')]
 differences=[r for r in results if r['source_ids_not_in_dump'] or r['associations_only_in_dump'] or r['associations_only_in_apk']]
 report={'status':'COMPARISON_COMPLETE_REVIEW_DIFFERENCES','source':source,'identity':json.loads(identity),'source_rows':len(rubrics),'packaged_source_ids':len(packaged),'groups_compared':len(rows),'dump_source_ids_missing_from_apk':sorted(set(rubrics)-packaged),'apk_source_ids_absent_from_dump_count':len(packaged-set(rubrics)),'groups_with_differences':len(differences),'scope':'Grouped source IDs and exact abbreviation/grade sets; differences require edition review, not automatic insertion. No full-path or printed-book verification.'}
 (output/'differences.json').write_text(json.dumps(differences,indent=2)+'\n')
 (output/'report.json').write_text(json.dumps(report,indent=2)+'\n')
 db.close();return report

if __name__=='__main__':
 p=argparse.ArgumentParser();p.add_argument('dump',type=Path);p.add_argument('book',type=Path);p.add_argument('output',type=Path);a=p.parse_args()
 print(json.dumps(run(a.dump,a.book,a.output),indent=2))
