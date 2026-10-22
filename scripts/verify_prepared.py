"""Verify prepared public release files with Python standard library + OpenSSL."""
from pathlib import Path
import base64,hashlib,json,re,subprocess,sys,tempfile
root=Path(__file__).resolve().parents[1];tag=sys.argv[1]
if not re.fullmatch(r'v[0-9]+\.[0-9]+(?:\.[0-9]+)?',tag):raise SystemExit('Invalid release tag')
folder=root/'prepared'/tag;c=json.loads((root/'release-config.json').read_text());outer=json.loads((folder/'update.json').read_text())
if outer.get('schema')!=1:raise SystemExit('Unknown update format')
data=base64.b64decode(outer['payload'],validate=True);signature=base64.b64decode(outer['signature'],validate=True)
with tempfile.TemporaryDirectory() as d:
 d=Path(d);(d/'payload').write_bytes(data);(d/'signature').write_bytes(signature)
 subprocess.run(['openssl','dgst','-sha256','-verify',str(root/'release-public-key.pem'),'-signature',str(d/'signature'),str(d/'payload')],check=True)
m=json.loads(data);filename='Open-Repertory-'+tag[1:]+'.apk';apk=folder/filename
if m['schema']!=1 or m['repository']!=c['repository'] or m['packageName']!=c['packageName'] or m['versionCode']!=c['versionCode'] or m['versionName']!=c['versionName'] or tag!='v'+c['versionName'].removesuffix('-dev'):raise SystemExit('Wrong release identity')
if m['apkUrl']!=f"https://github.com/{c['repository']}/releases/download/{tag}/{filename}":raise SystemExit('Wrong release URL')
if m['size']!=apk.stat().st_size or m['sha256']!=hashlib.sha256(apk.read_bytes()).hexdigest():raise SystemExit('APK changed')
print('Prepared public release is verified:',tag)
