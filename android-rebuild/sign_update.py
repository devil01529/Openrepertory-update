"""Sign the new Similora update channel. Private signing files stay outside Git."""
import argparse,base64,hashlib,json,subprocess,datetime
from pathlib import Path
ROOT=Path(__file__).resolve().parent
p=argparse.ArgumentParser();p.add_argument('--apk',type=Path,required=True);p.add_argument('--signing',type=Path,required=True);p.add_argument('--notes',type=Path,required=True);a=p.parse_args()
c=json.loads((ROOT/'release-config.json').read_text());version=c['versionName']
payload={'schema':1,'packageName':c['packageName'],'repository':c['repository'],'versionCode':c['versionCode'],'versionName':version,'minSdk':23,'size':a.apk.stat().st_size,'sha256':hashlib.sha256(a.apk.read_bytes()).hexdigest(),'apkUrl':f"https://github.com/{c['repository']}/releases/download/v{version}/{a.apk.name}",'notes':a.notes.read_text(),'publishedAt':datetime.datetime.now(datetime.timezone.utc).isoformat()}
raw=json.dumps(payload,separators=(',',':'),ensure_ascii=False).encode()
signature=subprocess.check_output(['openssl','dgst','-sha256','-sign',str(a.signing/'update-private.pem')],input=raw)
# Verify against the committed public identity before writing any public metadata.
import tempfile
with tempfile.TemporaryDirectory() as temp:
 t=Path(temp);(t/'public.der').write_bytes(base64.b64decode(c['manifestPublicKey']));(t/'signature').write_bytes(signature)
 subprocess.run(['openssl','pkey','-pubin','-inform','DER','-in',str(t/'public.der'),'-out',str(t/'public.pem')],check=True,stdout=subprocess.DEVNULL)
 subprocess.run(['openssl','dgst','-sha256','-verify',str(t/'public.pem'),'-signature',str(t/'signature')],input=raw,check=True)
(ROOT/'similora-update.json').write_text(json.dumps({'schema':1,'payload':base64.b64encode(raw).decode(),'signature':base64.b64encode(signature).decode()},indent=2)+'\n')
print('Signed metadata for',version,payload['sha256'])
