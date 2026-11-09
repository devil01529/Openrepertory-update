import importlib.util,json,gzip,tempfile,unittest
from pathlib import Path
spec=importlib.util.spec_from_file_location('audit',Path(__file__).resolve().parents[1]/'scripts/audit_github_dump.py');m=importlib.util.module_from_spec(spec);spec.loader.exec_module(m)
class Tests(unittest.TestCase):
 def fixture(self,p):
  dump=p/'dump.gz';book=p/'book.json'
  with gzip.open(dump,'wt') as f:f.write('COPY public.info (abbrev) FROM stdin;\npublicum\n\\.\nCOPY public.remedy (id, nameabbrev) FROM stdin;\n1\tAcon.\n\\.\nCOPY public.rubric (abbrev,id) FROM stdin;\npublicum\t9\n\\.\nCOPY public.rubricremedy (abbrev,rubricid,remedyid,weight) FROM stdin;\npublicum\t9\t1\t2\n\\.\n')
  book.write_text(json.dumps({'oorep_abbrev':'publicum','chapters':[{'rubrics':[{'id':'r','sourceIds':['9'],'remedies':[{'r':'Acon.','g':2}]}]}]}))
  return dump,book
 def test_complete_and_resume(self):
  with tempfile.TemporaryDirectory() as d:
   p=Path(d);dump,book=self.fixture(p);a=m.run(dump,book,p/'out');b=m.run(dump,book,p/'out')
   self.assertEqual(a,b);self.assertEqual(a['groups_with_differences'],0)
 def test_changed_input_rejected(self):
  with tempfile.TemporaryDirectory() as d:
   p=Path(d);dump,book=self.fixture(p);m.run(dump,book,p/'out');book.write_text(book.read_text()+' ')
   with self.assertRaises(ValueError):m.run(dump,book,p/'out')
 def test_source_absent(self):
  with tempfile.TemporaryDirectory() as d:
   p=Path(d);dump,book=self.fixture(p);x=json.loads(book.read_text());x['oorep_abbrev']='boericke';book.write_text(json.dumps(x))
   self.assertEqual(m.run(dump,book,p/'out')['status'],'SOURCE_NOT_IN_GITHUB_DUMP')
 def test_escape(self):
  self.assertEqual(m.unescape(r'a\tb'),'a\tb');self.assertIsNone(m.unescape(r'\N'))
if __name__=='__main__':unittest.main()
