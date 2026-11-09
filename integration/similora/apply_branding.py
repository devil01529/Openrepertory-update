"""Apply product branding to Android web assets; does not build or sign an APK."""
from pathlib import Path
import argparse, shutil
p=argparse.ArgumentParser(description=__doc__);p.add_argument('web_assets',type=Path);a=p.parse_args()
root=a.web_assets.resolve()
if not (root/'index.html').is_file():p.error('Expected the directory containing index.html')
for name in ['index.html','js/app.js','js/platform.js','js/clinical-ui.js','js/materia.js','js/backup.js','js/patients.js','css/glass-web.css']:
 f=root/name
 if not f.exists():continue
 text=f.read_text();updated=text.replace('Open Repertory','Similora')
 if name=='js/app.js':updated=updated.replace('(?:Similora source|OOREP','(?:OOREP').replace('(?:Open Repertory source|OOREP','(?:OOREP').replace('(?:OOREP','(?:Open Repertory source|OOREP')
 if updated!=text:
  backup=f.with_name(f.name+'.before-similora')
  if not backup.exists():shutil.copyfile(f,backup)
  f.write_text(updated)
image=root/'img/brandmark.png';image.parent.mkdir(parents=True,exist_ok=True)
backup=image.with_name('brandmark.before-similora.png')
if image.exists() and not backup.exists():shutil.copyfile(image,backup)
shutil.copyfile(Path(__file__).with_name('similora-mark-v1.png'),image)
print('Similora web branding applied. Move backup copies outside packaged assets before building. Native labels/icons and signing require the retained Android project.')
