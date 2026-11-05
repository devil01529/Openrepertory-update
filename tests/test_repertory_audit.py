import importlib.util
from pathlib import Path
import tempfile
import unittest
from unittest.mock import patch

spec=importlib.util.spec_from_file_location('audit',Path(__file__).resolve().parents[1]/'scripts/audit_repertories.py')
audit=importlib.util.module_from_spec(spec);spec.loader.exec_module(audit)

class AuditTests(unittest.TestCase):
    def fixtures(self):
        row={'id':'b-1','sourceIds':['1'],'sourcePath':'Mind, example','sourceMotherId':-1,'sourceChapterId':1,'sourceIsMother':False,'remedies':[{'r':'A.','g':2}],'sourceRemedyRecords':[{'r':'A.','g':2,'sourceRemedyId':9}]}
        book={'oorep_abbrev':'example','chapters':[{'rubrics':[row]}]}
        entry={'rubric':{'id':1,'fullPath':'Mind, example','mother':-1,'chapterId':1,'isMother':False},'weightedRemedies':[{'remedy':{'id':9,'nameAbbrev':'A.'},'weight':2}]}
        page={'totalNumberOfPages':1,'totalNumberOfResults':1,'totalNumberOfRepertoryRubrics':1,'results':[{'subRubrics':[entry]}]}
        return book,row,page,entry
    def compare(self,book,page):
        with patch.object(audit,'fetch_page',return_value=(page,'fixture')):
            return audit.compare(book,Path('/unused'))
    def test_matching(self):
        b,r,p,e=self.fixtures();v=self.compare(b,p)
        self.assertTrue(v['complete_live_coverage']);self.assertEqual(v['missing_source_ids'],[]);self.assertEqual(v['changed_scored_associations'],[])
    def test_missing_same_count(self):
        b,r,p,e=self.fixtures();r['sourceIds']=['2'];v=self.compare(b,p)
        self.assertEqual(v['missing_source_ids'],['1']);self.assertEqual(v['extra_packaged_ids'],['2'])
    def test_grade_change(self):
        b,r,p,e=self.fixtures();e['weightedRemedies'][0]['weight']=3;v=self.compare(b,p)
        self.assertEqual(v['changed_raw_associations'],['1']);self.assertEqual(v['changed_scored_associations'],['1'])
    def test_path_and_parent_change(self):
        b,r,p,e=self.fixtures();e['rubric']['fullPath']='Other';e['rubric']['mother']=8;v=self.compare(b,p)
        self.assertEqual(v['changed_paths'],['1']);self.assertEqual(v['changed_hierarchy'],['1'])
    def test_unsupported_grade_unscored(self):
        b,r,p,e=self.fixtures();r['remedies']=[];e['weightedRemedies'][0]['weight']=30
        self.assertEqual(self.compare(b,p)['changed_scored_associations'],[])
    def test_duplicate_pages_not_complete(self):
        b,r,p,e=self.fixtures();p['results'].append({'subRubrics':[e]})
        self.assertFalse(self.compare(b,p)['complete_live_coverage'])
    def test_chapter_index_is_not_missing(self):
        b,r,p,e=self.fixtures();b['chapters'][0]['rubrics']=[];b['chapters'][0]['indexRows']=[r]
        self.assertEqual(self.compare(b,p)['missing_source_ids'],[])
        self.assertEqual(audit.structural(b)['index_rows'],1)
    def test_partial_does_not_call_unseen_rows_extra(self):
        b,r,p,e=self.fixtures();r['sourceIds']=['2'];p['totalNumberOfRepertoryRubrics']=200
        v=self.compare(b,p)
        self.assertEqual(v['extra_packaged_ids'],[])
        self.assertEqual(v['packaged_ids_not_observed_in_partial_fetch'],['2'])
    def test_structural_nested(self):
        b,r,p,e=self.fixtures();r['sub']=[{'id':'b-1','remedies':[{'r':'A.','g':30}]}];v=audit.structural(b)
        self.assertEqual(v['duplicate_node_ids'],['b-1']);self.assertEqual(v['invalid_scored_grades'],1)

if __name__=='__main__':unittest.main()
