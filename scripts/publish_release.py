"""Publish a prepared, verified release using the owner's normal GitHub CLI login.

No credentials are stored by this script. Public APK downloads need no GitHub login.
"""
from pathlib import Path
import argparse,base64,hashlib,json,subprocess,urllib.request
from cryptography.hazmat.primitives import hashes,serialization
from cryptography.hazmat.primitives.asymmetric import padding
ROOT=Path(__file__).resolve().parents[1]
p=argparse.ArgumentParser(description=__doc__);p.add_argument('directory',type=Path);p.add_argument('--tag',required=True);p.add_argument('--draft',action='store_true');a=p.parse_args()
c=json.loads((ROOT/'release-config.json').read_text());repo=c['repository']
outer=json.loads((a.directory/'update.json').read_text());data=base64.b64decode(outer['payload'],validate=True)
pub=serialization.load_der_public_key(base64.b64decode(c['manifestPublicKey']));pub.verify(base64.b64decode(outer['signature'],validate=True),data,padding.PKCS1v15(),hashes.SHA256())
m=json.loads(data);name=m['apkUrl'].rsplit('/',1)[-1];apk=a.directory/name
if m['repository']!=repo or m['packageName']!=c['packageName'] or m['versionCode']!=c['versionCode'] or m['versionName']!=c['versionName']:raise SystemExit('Release/config identity mismatch')
if m['apkUrl']!=f'https://github.com/{repo}/releases/download/{a.tag}/{name}':raise SystemExit('Tag or asset URL mismatch')
if apk.stat().st_size!=m['size'] or hashlib.sha256(apk.read_bytes()).hexdigest()!=m['sha256']:raise SystemExit('Prepared APK changed')
metadata=json.loads(subprocess.check_output(['gh','api','repos/'+repo],text=True))
if metadata['visibility']!='public':raise SystemExit('Update repository must be public; credentials must never be embedded in the APK')
if metadata['archived'] or not metadata.get('permissions',{}).get('push'):raise SystemExit('Repository is not writable')
exists=subprocess.run(['gh','release','view',a.tag,'--repo',repo,'--json','isDraft'],capture_output=True,text=True)
if exists.returncode==0:
    if not json.loads(exists.stdout)['isDraft']:raise SystemExit('This release is already public. Publish corrections under a higher version, never replace a published release.')
else:subprocess.run(['gh','release','create',a.tag,'--repo',repo,'--target',metadata['default_branch'],'--draft','--title','Open Repertory '+m['versionName'],'--notes-file',str(a.directory/'RELEASE_NOTES.md')],check=True)
assets=[str(a.directory/file) for file in [name,'update.json','RELEASE_NOTES.md','SHA256SUMS']]
# Only drafts may be retried. Public assets are immutable by this workflow.
subprocess.run(['gh','release','upload',a.tag,*assets,'--repo',repo,'--clobber'],check=True)
if not a.draft:
    subprocess.run(['gh','release','edit',a.tag,'--repo',repo,'--draft=false','--latest'],check=True)
    url=f'https://github.com/{repo}/releases/latest/download/update.json'
    with urllib.request.urlopen(url,timeout=60) as response:remote=json.loads(response.read(65537))
    if remote!=outer:raise SystemExit('Published manifest differs from the prepared release')
print(('Draft ready: ' if a.draft else 'Published: ')+f'https://github.com/{repo}/releases/tag/{a.tag}')
