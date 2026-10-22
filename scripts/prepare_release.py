"""Verify an APK and prepare its independently signed GitHub Release assets.

Private keys are supplied as file paths, never copied to output or repository.
Requires Python cryptography and the pinned Android build tools.
"""
from pathlib import Path
import argparse,base64,datetime,hashlib,json,os,re,shutil,subprocess
from cryptography.hazmat.primitives import hashes,serialization
from cryptography.hazmat.primitives.asymmetric import padding
ROOT=Path(__file__).resolve().parents[1]

def inspect_apk(apk,config):
    tools=Path(os.environ.get('REPERTORY_TOOLS','/workspace/tools'))/'android/android-15'
    cert=subprocess.check_output([str(tools/'apksigner'),'verify','--print-certs',str(apk)],text=True)
    certs=re.findall(r'Signer #\d+ certificate SHA-256 digest: ([a-f0-9]+)',cert)
    if certs!=[config['apkCertificateSha256']]:raise ValueError('APK signer differs from the existing installed identity')
    badging=subprocess.check_output([str(tools/'aapt2'),'dump','badging',str(apk)],text=True)
    identity=re.search(r"package: name='([^']+)' versionCode='(\d+)' versionName='([^']+)'",badging)
    if not identity:raise ValueError('Cannot read APK identity')
    package,code,name=identity.groups()
    if (package,int(code),name)!=(config['packageName'],config['versionCode'],config['versionName']):raise ValueError('APK identity/version differs from release-config.json')
    min_sdk=int(re.search(r"(?:minSdkVersion|sdkVersion):'(\d+)'",badging)[1])
    return dict(packageName=package,versionCode=int(code),versionName=name,minSdk=min_sdk,
                size=apk.stat().st_size,sha256=hashlib.sha256(apk.read_bytes()).hexdigest())

def prepare(apk,key_file,notes_file,out,tag,config):
    info=inspect_apk(apk,config)
    if not re.fullmatch(r'v[0-9]+\.[0-9]+(?:\.[0-9]+)?',tag):raise ValueError('Use a release tag such as v1.19')
    if tag!='v'+config['versionName'].removesuffix('-dev'):raise ValueError('Tag must match this APK version')
    notes=notes_file.read_text().strip()
    if not notes or len(notes)>12000:raise ValueError('Release notes must contain 1–12000 characters')
    key=serialization.load_pem_private_key(key_file.read_bytes(),password=None)
    public=base64.b64encode(key.public_key().public_bytes(serialization.Encoding.DER,serialization.PublicFormat.SubjectPublicKeyInfo)).decode()
    if public!=config['manifestPublicKey']:raise ValueError('Metadata signing key does not match the public key embedded in the APK')
    filename='Open-Repertory-'+tag[1:]+'.apk'
    payload=dict(schema=1,repository=config['repository'],**info,
                 apkUrl=f"https://github.com/{config['repository']}/releases/download/{tag}/{filename}",
                 notes=notes,publishedAt=datetime.datetime.now(datetime.timezone.utc).isoformat())
    data=json.dumps(payload,ensure_ascii=False,separators=(',',':')).encode()
    signature=key.sign(data,padding.PKCS1v15(),hashes.SHA256())
    envelope=dict(schema=1,payload=base64.b64encode(data).decode(),signature=base64.b64encode(signature).decode())
    out.mkdir(parents=True,exist_ok=True)
    shutil.copy2(apk,out/filename)
    (out/'update.json').write_text(json.dumps(envelope,indent=2)+'\n')
    (out/'RELEASE_NOTES.md').write_text(notes+'\n')
    (out/'SHA256SUMS').write_text(f"{info['sha256']}  {filename}\n")
    print(json.dumps({'repository':config['repository'],'tag':tag,'version':info['versionName'],'sha256':info['sha256'],'output':str(out)},indent=2))

if __name__=='__main__':
    p=argparse.ArgumentParser(description=__doc__)
    p.add_argument('apk',type=Path);p.add_argument('--verify-apk-only',action='store_true')
    p.add_argument('--key-file',type=Path);p.add_argument('--notes',type=Path);p.add_argument('--out',type=Path);p.add_argument('--tag')
    a=p.parse_args();config=json.loads((ROOT/'release-config.json').read_text())
    if a.verify_apk_only:print(json.dumps(inspect_apk(a.apk,config),indent=2))
    else:
        if not all((a.key_file,a.notes,a.out,a.tag)):p.error('--key-file, --notes, --out and --tag are required')
        prepare(a.apk,a.key_file,a.notes,a.out,a.tag,config)
