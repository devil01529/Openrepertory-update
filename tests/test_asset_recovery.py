import importlib.util
from pathlib import Path
import tempfile
import unittest
import zipfile

spec = importlib.util.spec_from_file_location('recovery', Path(__file__).resolve().parents[1] / 'scripts/recover_app_assets.py')
recovery = importlib.util.module_from_spec(spec)
spec.loader.exec_module(recovery)


class RecoveryTests(unittest.TestCase):
    def run_archive(self, entries, existing=False):
        with tempfile.TemporaryDirectory() as d:
            root = Path(d)
            apk = root / 'test.apk'
            target = root / 'output'
            with zipfile.ZipFile(apk, 'w') as z:
                for name, value in entries:
                    z.writestr(name, value)
            if existing:
                target.mkdir()
                (target / 'keep').write_text('unchanged')
            if existing or any('..' in n or '\\' in n for n, _ in entries):
                with self.assertRaises(ValueError):
                    recovery.extract_assets(apk, target)
                if existing:
                    self.assertEqual((target / 'keep').read_text(), 'unchanged')
                else:
                    self.assertFalse(target.exists())
            else:
                self.assertEqual(recovery.extract_assets(apk, target), 1)
                self.assertEqual((target / 'assets/www/app.js').read_text(), 'example')
                self.assertFalse((target / 'classes.dex').exists())

    def test_assets_only(self):
        self.run_archive([('assets/www/app.js', 'example'), ('classes.dex', 'native')])

    def test_no_overwrite(self):
        self.run_archive([('assets/www/app.js', 'example')], True)

    def test_traversal(self):
        self.run_archive([('assets/../../outside', 'bad')])

    def test_backslash(self):
        self.run_archive([('assets/..\\outside', 'bad')])


if __name__ == '__main__':
    unittest.main()
