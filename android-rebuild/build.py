"""Build Similora from native Java source and locally recovered, verified clinical assets.
No private keys or clinical datasets are committed by this script.
"""
import argparse, subprocess, zipfile, shutil, os, hashlib, json
from pathlib import Path
ROOT=Path(__file__).resolve().parent
p=argparse.ArgumentParser();p.add_argument('--tools',type=Path,required=True);p.add_argument('--assets',type=Path,required=True);p.add_argument('--signing',type=Path,required=True);a=p.parse_args()
tools=a.tools.resolve();bt=tools/'android-15';android=tools/'android-35/android.jar';build=ROOT/'build';build.mkdir(exist_ok=True);classes=build/'classes';shutil.rmtree(classes,ignore_errors=True);classes.mkdir();dex=build/'dex';shutil.rmtree(dex,ignore_errors=True);dex.mkdir()
def run(args):subprocess.run([str(x) for x in args],check=True)
run([bt/'aapt2','compile','--dir',ROOT/'res','-o',build/'resources.zip'])
run([bt/'aapt2','link','-o',build/'base.apk','-I',android,'--manifest',ROOT/'AndroidManifest.xml','--min-sdk-version','23','--target-sdk-version','35',build/'resources.zip'])
sources=sorted((ROOT/'src').rglob('*.java'));run(['java','-jar',tools/'ecj.jar','-source','8','-target','8','-encoding','UTF-8','-warn:none','-bootclasspath',str(android)+os.pathsep+str(bt/'core-lambda-stubs.jar'),'-d',classes,*sources])
with zipfile.ZipFile(build/'classes.jar','w') as z:
 for f in classes.rglob('*.class'):z.write(f,str(f.relative_to(classes)))
run(['java','-cp',bt/'lib/d8.jar','com.android.tools.r8.D8','--release','--min-api','23','--lib',android,'--output',dex,build/'classes.jar'])
unsigned=build/'unsigned.apk';shutil.copyfile(build/'base.apk',unsigned)
with zipfile.ZipFile(unsigned,'a',compression=zipfile.ZIP_DEFLATED,compresslevel=6) as z:
 for f in sorted(a.assets.rglob('*')):
  if f.is_file():
   if 'before-similora' in f.name:raise SystemExit('Move branding backups outside packaged assets first')
   z.write(f,'assets/'+str(f.relative_to(a.assets)))
 z.write(dex/'classes.dex','classes.dex')
run([bt/'zipalign','-f','4',unsigned,build/'aligned.apk'])
out=build/('Similora-'+json.loads((ROOT/'release-config.json').read_text())['versionName']+'.apk')
run(['java','-jar',bt/'lib/apksigner.jar','sign','--ks',a.signing/'similora-release.p12','--ks-key-alias','similora','--ks-pass','file:'+str(a.signing/'store-password'),'--out',out,build/'aligned.apk'])
verification=subprocess.check_output(['java','-jar',str(bt/'lib/apksigner.jar'),'verify','--verbose','--print-certs',str(out)],text=True)
print(verification)
expected=json.loads((ROOT/'release-config.json').read_text())['apkCertificateSha256']
if 'certificate SHA-256 digest: '+expected not in verification:raise SystemExit('Signing certificate differs from the committed release identity')
(build/'SHA256SUMS').write_text(hashlib.sha256(out.read_bytes()).hexdigest()+'  '+out.name+'\n')
print('Built and signature-verified:',out)
