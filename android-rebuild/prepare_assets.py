"""Recover the immutable v1.41 asset baseline and apply only the reviewed Similora UI overlay."""
from pathlib import Path
import argparse,sys,subprocess,hashlib,json,shutil
ROOT=Path(__file__).resolve().parent;REPO=ROOT.parent
sys.path.insert(0,str(REPO/'scripts'));from recover_app_assets import extract_assets
p=argparse.ArgumentParser();p.add_argument('destination',type=Path);a=p.parse_args();destination=a.destination.resolve()
if destination==REPO or REPO in destination.parents:raise SystemExit('Keep clinical assets outside the public repository')
apk=REPO/'prepared/v1.41/Open-Repertory-1.41.apk'
if hashlib.sha256(apk.read_bytes()).hexdigest()!='9be52ef0524a509cf69a22cd7c09634b1946d166f25297988b1046d456710b59':raise SystemExit('Baseline APK checksum mismatch')
extract_assets(apk,destination)
web=destination/'assets/www';subprocess.run([sys.executable,str(REPO/'integration/similora/apply_branding.py'),str(web)],check=True)
for source in web.rglob('*before-similora*'):
 target=destination/'branding-backup'/source.relative_to(web);target.parent.mkdir(parents=True,exist_ok=True);shutil.move(source,target)
shutil.copyfile(ROOT/'similora-mobile.js',web/'js/similora-mobile.js')
index=web/'index.html';index.write_text(index.read_text().replace('<script src="js/start.js"></script>','<script src="js/similora-mobile.js"></script>\n<script src="js/start.js"></script>'))
shutil.copyfile(ROOT/'res/drawable/similora.png',web/'img/brandmark.png')
subprocess.run([sys.executable,str(ROOT/'ai_overlay.py'),str(web)],check=True)
recovered=json.loads((destination/'RECOVERY.json').read_text())['files'];count=0
for path,digest in recovered.items():
 if path.startswith('assets/www/data/'):
  assert hashlib.sha256((destination/path).read_bytes()).hexdigest()==digest;count+=1
print('Prepared clinical assets; unchanged data files:',count)
