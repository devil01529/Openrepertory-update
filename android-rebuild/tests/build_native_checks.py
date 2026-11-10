import subprocess,zipfile,os
from pathlib import Path
ROOT=Path(__file__).resolve().parents[1];TOOLS=Path(os.environ.get('SIMILORA_ANDROID_TOOLS','/workspace/scratch/android-tools'));BT=TOOLS/'android-15';SDK=TOOLS/'android-35/android.jar';OUT=ROOT/'build/native-tests';OUT.mkdir(parents=True,exist_ok=True);SIGN=Path(os.environ.get('SIMILORA_SIGNING_DIR','/workspace/.similora-signing'))
def run(args):subprocess.run([str(x) for x in args],check=True)
run(['java','-jar',TOOLS/'ecj.jar','-source','8','-target','8','-warn:none','-bootclasspath',str(SDK)+':'+str(BT/'core-lambda-stubs.jar'),'-d',OUT,*list((ROOT/'tests/src').rglob('*.java'))])
with zipfile.ZipFile(OUT/'tests.jar','w') as z:
 for p in OUT.rglob('*.class'):z.write(p,str(p.relative_to(OUT)))
run(['java','-cp',BT/'lib/d8.jar','com.android.tools.r8.D8','--min-api','23','--lib',SDK,'--output',OUT,OUT/'tests.jar'])
run([BT/'aapt2','link','-o',OUT/'unsigned.apk','-I',SDK,'--manifest',ROOT/'tests/AndroidManifest.xml'])
with zipfile.ZipFile(OUT/'unsigned.apk','a') as z:
 z.write(OUT/'classes.dex','classes.dex')
 fixture=os.environ.get('SIMILORA_TEST_SESSION_FILE')
 if fixture:z.write(fixture,'assets/session-fixture.json')
run([BT/'zipalign','-f','4',OUT/'unsigned.apk',OUT/'aligned.apk'])
run(['java','-jar',BT/'lib/apksigner.jar','sign','--ks',SIGN/'similora-release.p12','--ks-pass','file:'+str(SIGN/'store-password'),'--out',OUT/'Similora-QA.apk',OUT/'aligned.apk'])
print(OUT/'Similora-QA.apk')
