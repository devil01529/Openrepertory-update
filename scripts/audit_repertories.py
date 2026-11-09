"""Read-only packaged repertory audit; optional exhaustive OOREP comparison.
Raw source responses stay in the supplied local cache, not in the public repo.
"""
import argparse, collections, concurrent.futures, hashlib, json, time
from datetime import datetime, timezone
from pathlib import Path
from urllib.parse import urlencode
from urllib.request import urlopen


def walk(rows):
    for row in rows:
        yield row
        yield from walk(row.get('sub', []))


def structural(book):
    rows = [r for c in book['chapters'] for r in walk(c['rubrics'])]
    index_rows = [r for c in book['chapters'] for r in c.get('indexRows', [])]
    ids = [r['id'] for r in rows + index_rows if r.get('id')]
    links = [v for r in rows for v in r.get('remedies', [])]
    return {'nodes': len(rows), 'chapters': len(book['chapters']),
            'index_rows':len(index_rows),
            'source_ids': len({str(s) for r in rows + index_rows for s in r.get('sourceIds', [])}),
            'duplicate_node_ids': [k for k,v in collections.Counter(ids).items() if v > 1],
            'remedies_used': len({v['r'] for v in links}), 'scored_links': len(links),
            'grade_counts': dict(collections.Counter(str(v['g']) for v in links)),
            'invalid_scored_grades': sum(v['g'] not in [1,2,3,4,5] for v in links),
            'references': sum(len(r.get('references', [])) for r in rows)}


def fetch_page(source, page, cache):
    target = cache / source / f'{page}.json'
    if target.exists():
        raw = target.read_bytes()
    else:
        query = urlencode(dict(repertory=source, symptom='***', page=page,
                               remedyString='', minWeight=0, getRemedies=1))
        for attempt in range(2):
            try:
                raw = urlopen('https://www.oorep.com/api/lookup_rep?' + query, timeout=20).read()
                response = json.loads(raw)
                if response[0]['currPage'] != page:
                    raise ValueError('Wrong page')
                target.parent.mkdir(parents=True, exist_ok=True)
                target.write_bytes(raw)
                break
            except Exception:
                if attempt == 1:
                    raise
                time.sleep(attempt + 1)
    value = json.loads(raw)[0]
    if value['currPage'] != page:
        raise ValueError('Cached page mismatch')
    return value, hashlib.sha256(raw).hexdigest()


def compare(book, cache, cached_only=False):
    source = book['oorep_abbrev']
    if cached_only and not (cache/source/'0.json').exists():
        raise ValueError('No cached first page; live comparison unavailable')
    first, _ = fetch_page(source, 0, cache)
    live = {}; page_hashes = {}; duplicates = []
    pages = sorted(int(p.stem) for p in (cache/source).glob('*.json')) if cached_only else list(range(first['totalNumberOfPages']))
    with concurrent.futures.ThreadPoolExecutor(max_workers=1) as pool:
        for page, (value, digest) in zip(pages, pool.map(lambda p: fetch_page(source,p,cache), pages)):
            if value['totalNumberOfResults'] != first['totalNumberOfResults']:
                raise ValueError('Source changed during pagination')
            page_hashes[str(page)] = digest
            for result in value['results']:
                for entry in result['subRubrics']:
                    sid = str(entry['rubric']['id'])
                    if sid in live:
                        duplicates.append(sid)
                    live[sid] = entry
    packaged = {}
    for chapter in book['chapters']:
        for row in list(walk(chapter['rubrics'])) + chapter.get('indexRows', []):
            for sid in row.get('sourceIds', []):
                packaged[str(sid)] = row
    missing = sorted(set(live)-set(packaged)); extra = sorted(set(packaged)-set(live))
    paths = []; raw_changes = []; score_changes = []; hierarchy_changes = []
    for sid in sorted(set(live)&set(packaged)):
        row = packaged[sid]; entry = live[sid]; rubric = entry['rubric']
        if 'sourcePath' in row and row['sourcePath'] != rubric['fullPath']:
            paths.append(sid)
        for a,b in [('sourceMotherId','mother'),('sourceChapterId','chapterId'),('sourceIsMother','isMother')]:
            if a in row and row[a] != rubric[b]:
                hierarchy_changes.append(sid); break
        raw = collections.Counter((v['remedy']['nameAbbrev'],v['weight'],v['remedy']['id']) for v in entry['weightedRemedies'])
        if 'sourceRemedyRecords' in row:
            old = collections.Counter((v['r'],v['g'],v['sourceRemedyId']) for v in row['sourceRemedyRecords'])
            if raw != old: raw_changes.append(sid)
        by_remedy = collections.defaultdict(set)
        for (remedy, grade, _), count in raw.items(): by_remedy[remedy].add(grade)
        expected = {(r,next(iter(gs))) for r,gs in by_remedy.items() if len(gs)==1 and next(iter(gs)) in [1,2,3,4,5]}
        actual = {(v['r'],v['g']) for v in row.get('remedies',[])}
        if expected != actual: score_changes.append(sid)
    return {'source': source, 'api_total': first['totalNumberOfRepertoryRubrics'],
            'queried_total':first['totalNumberOfResults'], 'unique_live_ids':len(live),
            'complete_live_coverage': len(live)==first['totalNumberOfRepertoryRubrics'] and not duplicates,
            'duplicate_live_ids':duplicates, 'missing_source_ids':missing,'extra_packaged_ids':extra if len(live)==first['totalNumberOfRepertoryRubrics'] else [],
            'packaged_ids_not_observed_in_partial_fetch':extra if len(live)!=first['totalNumberOfRepertoryRubrics'] else [],
            'pages_compared':len(pages),'pages_expected':first['totalNumberOfPages'],
            'changed_paths':paths,'changed_raw_associations':raw_changes,
            'changed_scored_associations':score_changes,'changed_hierarchy':hierarchy_changes,
            'page_sha256':page_hashes,
            'scope':'Electronic source comparison, not printed-book proofreading; score comparison uses unambiguous positive grades 1–5.'}


def main():
    p=argparse.ArgumentParser();p.add_argument('data',type=Path);p.add_argument('--cache',type=Path,required=True);p.add_argument('--output',type=Path,required=True);p.add_argument('--live',nargs='*',default=[]);p.add_argument('--cached-only',action='store_true');a=p.parse_args()
    registry=json.loads((a.data/'source_registry.json').read_text());out={'date':datetime.now(timezone.utc).date().isoformat(),'books':{},'retired':registry['retired_sources']}
    a.output.parent.mkdir(parents=True,exist_ok=True)
    for source in registry['bundled_sources']:
        path=a.data/source['file'];book=json.loads(path.read_text());record=structural(book);record['sha256']=hashlib.sha256(path.read_bytes()).hexdigest()
        if source['id'] in a.live:
            print('Live comparison:',source['id'],flush=True)
            try:record['live']=compare(book,a.cache,a.cached_only)
            except Exception as e:record['live_error']=str(e)
        out['books'][source['id']]=record
        a.output.write_text(json.dumps(out,indent=2)+'\n')
        print(source['id'],record['nodes'],'nodes', 'live complete' if record.get('live',{}).get('complete_live_coverage') else 'structural/partial',flush=True)

if __name__=='__main__':main()
