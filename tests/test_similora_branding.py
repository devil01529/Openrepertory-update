from pathlib import Path
import subprocess,tempfile,unittest
ROOT=Path(__file__).resolve().parents[1]
class Branding(unittest.TestCase):
 def test_idempotence_and_data_preservation(self):
  with tempfile.TemporaryDirectory() as tmp:
   p=Path(tmp);(p/'js').mkdir();(p/'data').mkdir()
   (p/'index.html').write_text('<title>Open Repertory</title>')
   (p/'js/app.js').write_text('const label="Open Repertory source"; const providers=/(?:OOREP|OpenRep)/;')
   (p/'js/platform.js').write_text('"Open Repertory report";"openrepertory_browser_store_v2";"ORBK2";')
   original=b'{"author":"Original author","grade":3}'
   (p/'data/book.json').write_bytes(original)
   script=ROOT/'integration/similora/apply_branding.py'
   subprocess.run(['python3',str(script),str(p)],check=True,capture_output=True)
   once=(p/'js/app.js').read_bytes()
   subprocess.run(['python3',str(script),str(p)],check=True,capture_output=True)
   self.assertEqual(once,(p/'js/app.js').read_bytes())
   self.assertEqual((p/'index.html').read_text(),'<title>Similora</title>')
   self.assertEqual((p/'index.html.before-similora').read_text(),'<title>Open Repertory</title>')
   self.assertEqual((p/'data/book.json').read_bytes(),original)
   self.assertIn('openrepertory_browser_store_v2',(p/'js/platform.js').read_text())
   self.assertIn('ORBK2',(p/'js/platform.js').read_text())
   self.assertEqual((p/'img/brandmark.png').read_bytes(),(ROOT/'integration/similora/similora-mark-v1.png').read_bytes())
if __name__=='__main__':unittest.main()
