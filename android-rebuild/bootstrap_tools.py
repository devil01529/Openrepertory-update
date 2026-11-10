"""Download pinned official Android build tools and Eclipse Java compiler into a private cache."""
from pathlib import Path
import argparse,urllib.request,zipfile,hashlib,os
p=argparse.ArgumentParser();p.add_argument('destination',type=Path);a=p.parse_args();root=a.destination.resolve();root.mkdir(parents=True,exist_ok=True)
files=[('https://dl.google.com/android/repository/build-tools_r35_linux.zip','tools.zip','sha1','2cfaa0bbb2336e9ec18ed3ecea84fa2e2af607bc'),('https://dl.google.com/android/repository/platform-35_r02.zip','platform.zip','sha1','0bb560a90a7a2cbd0dd8348224d518b638fe7949')]
for url,name,algorithm,digest in files:
 target=root/name;urllib.request.urlretrieve(url,target)
 if hashlib.new(algorithm,target.read_bytes()).hexdigest()!=digest:raise SystemExit('Tool checksum mismatch')
 with zipfile.ZipFile(target) as z:
  for member in z.infolist():
   path=(root/member.filename).resolve()
   if not path.is_relative_to(root):raise SystemExit('Unsafe tool archive')
  z.extractall(root)
  for member in z.infolist():
   if member.external_attr>>16:os.chmod(root/member.filename,member.external_attr>>16)
 target.unlink()
url='https://repo.maven.apache.org/maven2/org/eclipse/jdt/ecj/3.39.0/ecj-3.39.0.jar'
urllib.request.urlretrieve(url,root/'ecj.jar')
expected=Path(__file__).with_name('ecj.sha256').read_text().strip()
assert hashlib.sha256((root/'ecj.jar').read_bytes()).hexdigest()==expected
print('Pinned build tools ready:',root)
