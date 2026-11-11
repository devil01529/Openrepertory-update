"""Verify the prepared independent Similora channel using public material only."""
import base64,hashlib,json,subprocess,tempfile,argparse
parser=argparse.ArgumentParser();parser.add_argument("--prepared-only",action="store_true");args=parser.parse_args()
from pathlib import Path
ROOT=Path(__file__).resolve().parent
config=json.loads((ROOT/'release-config.json').read_text());folder=ROOT.parent/'prepared'/('v'+config['versionName'])
envelope=json.loads((folder/'similora-update.json').read_text());raw=base64.b64decode(envelope['payload'],validate=True)
assert envelope['schema']==1
with tempfile.TemporaryDirectory() as name:
 t=Path(name);(t/'key.der').write_bytes(base64.b64decode(config['manifestPublicKey'],validate=True));(t/'signature').write_bytes(base64.b64decode(envelope['signature'],validate=True))
 subprocess.run(['openssl','pkey','-pubin','-inform','DER','-in',str(t/'key.der'),'-out',str(t/'key.pem')],check=True)
 subprocess.run(['openssl','dgst','-sha256','-verify',str(t/'key.pem'),'-signature',str(t/'signature')],input=raw,check=True)
p=json.loads(raw)
for key in ['packageName','repository','versionCode','versionName']:assert p[key]==config[key],key
assert p['schema']==1 and p['minSdk']==23
apk=folder/('Similora-'+config['versionName']+'.apk')
assert p['apkUrl']==f"https://github.com/{config['repository']}/releases/download/v{config['versionName']}/{apk.name}"
assert apk.stat().st_size==p['size'] and hashlib.sha256(apk.read_bytes()).hexdigest()==p['sha256']
if not args.prepared_only: assert (ROOT/'similora-update.json').read_bytes()==(folder/'similora-update.json').read_bytes()
print('Verified independent Similora metadata, identity, APK hash and size')
