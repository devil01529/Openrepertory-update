import importlib.util,unittest
from pathlib import Path
spec=importlib.util.spec_from_file_location('retention',Path(__file__).resolve().parents[1]/'scripts/retain_releases.py')
m=importlib.util.module_from_spec(spec);spec.loader.exec_module(m)
class RetentionTests(unittest.TestCase):
 def setUp(self):self.rows=[dict(id=i,tag_name=f'v1.{i}',draft=False,published_at=f'2026-10-{i:02d}T01:00:00Z') for i in range(1,12)]
 def test_count_and_oldest_first(self):
  old,keep=m.plan(list(reversed(self.rows)),'v1.11');self.assertEqual([r['id'] for r in old],list(range(1,7)));self.assertEqual([r['id'] for r in keep],list(range(7,12)))
 def test_drafts_excluded(self):
  self.rows.append(dict(id=12,tag_name='v1.12',draft=True,published_at=None));self.assertEqual(len(m.plan(self.rows,'v1.11')[1]),5)
 def test_short_history(self):self.assertEqual(m.plan(self.rows[:3],'v1.3')[0],[])
 def test_protect_latest(self):
  with self.assertRaises(ValueError):m.plan(self.rows,'v1.2')
 def test_no_duplicate_tags(self):
  with self.assertRaises(ValueError):m.plan(self.rows+[self.rows[0]],'v1.11')
 def test_bad_date_stops_cleanup(self):
  with self.assertRaises((ValueError,AttributeError)):m.plan([{**self.rows[0],'published_at':None}],'v1.1')
 def test_prerelease_is_also_a_published_version(self):
  self.rows[-1]['prerelease']=True;self.assertEqual(m.plan(self.rows,'v1.10')[1][-1]['tag_name'],'v1.11')
 def test_prepared_future_and_drafts_survive(self):
  import tempfile
  with tempfile.TemporaryDirectory() as d:
   root=Path(d)
   for name in ['v1.19','v1.20','v1.29','v1.30','v1.33','v1.34','v1.35']:(root/name).mkdir()
   retained=[{'tag_name':'v1.'+str(i)} for i in range(29,34)]
   active={r['tag_name'] for r in retained}|{'v1.20'}
   removed=m.prepared_to_prune(root.iterdir(),active,retained,[])
   self.assertEqual([p.name for p in removed],['v1.19'])
if __name__=='__main__':unittest.main()
