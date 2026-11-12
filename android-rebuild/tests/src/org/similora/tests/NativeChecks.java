package org.similora.tests;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.webkit.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.*;
import org.json.*;
/** Separate, same-signer instrumentation APK. No test entrypoint ships in the production APK. */
public final class NativeChecks extends Instrumentation {
 void check(boolean yes,String label){if(!yes)throw new AssertionError(label);}
 Method method(Class<?> c,String name,Class<?>...args)throws Exception{Method m=c.getDeclaredMethod(name,args);m.setAccessible(true);return m;}
 Field field(Class<?> c,String name)throws Exception{Field f=c.getDeclaredField(name);f.setAccessible(true);return f;}
 String js(WebView view,String expression)throws Exception{CountDownLatch wait=new CountDownLatch(1);String[] value={null};runOnMainSync(()->view.evaluateJavascript(expression,r->{value[0]=r;wait.countDown();}));check(wait.await(90,TimeUnit.SECONDS),"JS callback timed out");return value[0];}
 @Override public void onCreate(Bundle b){super.onCreate(b);start();}
 @Override public void onStart(){Bundle result=new Bundle();List<String> passed=new ArrayList<>();Context c=getTargetContext();String id=UUID.randomUUID().toString().replace("-","");String other=UUID.randomUUID().toString().replace("-","");Activity activity=null;
 try{
  ApplicationInfo info=c.getApplicationInfo();check((info.flags&ApplicationInfo.FLAG_DEBUGGABLE)==0,"Release debugging disabled");check((info.flags&ApplicationInfo.FLAG_ALLOW_BACKUP)==0,"Backups disabled");check((info.flags&ApplicationInfo.FLAG_USES_CLEARTEXT_TRAFFIC)==0,"Cleartext disabled");passed.add("manifest protections");if(Build.VERSION.SDK_INT>=26)check(c.getPackageManager().getApplicationIcon(info) instanceof android.graphics.drawable.AdaptiveIconDrawable,"Adaptive launcher resource");passed.add("adaptive launcher resource");
  Class<?> vault=Class.forName("org.similora.app.Vault",true,c.getClassLoader());Constructor<?> constructor=vault.getDeclaredConstructor(Context.class,String.class);constructor.setAccessible(true);Object store=constructor.newInstance(c,id);Method read=method(vault,"read",String.class),batch=method(vault,"batch",String.class),healthy=method(vault,"healthy"),restore=method(vault,"restore",String.class);
  batch.invoke(store,new JSONObject().put("case","SYNTHETIC_PRIVATE_SENTINEL_77642").toString());check(read.invoke(store,"case").equals("SYNTHETIC_PRIVATE_SENTINEL_77642"),"Vault roundtrip");Object reopened=constructor.newInstance(c,id);check(read.invoke(reopened,"case").equals("SYNTHETIC_PRIVATE_SENTINEL_77642"),"Vault persistence");Object isolated=constructor.newInstance(c,other);check(read.invoke(isolated,"case")==null,"Account isolation");passed.add("keystore encryption, persistence and per-account isolation");
  File data=new File(c.getNoBackupFilesDir(),"records-"+id+".vault");byte[] bytes=new byte[(int)data.length()];try(FileInputStream in=new FileInputStream(data)){check(in.read(bytes)==bytes.length,"Read encrypted file");}check(!new String(bytes,StandardCharsets.ISO_8859_1).contains("SYNTHETIC_PRIVATE_SENTINEL"),"No plaintext on disk");bytes[bytes.length-1]^=1;try(FileOutputStream out=new FileOutputStream(data)){out.write(bytes);}Object damaged=constructor.newInstance(c,id);check(!(Boolean)healthy.invoke(damaged),"Tampering detected");try{batch.invoke(damaged,"{}");throw new AssertionError("Damaged data must not be overwritten");}catch(InvocationTargetException expected){}
  restore.invoke(damaged,new JSONObject().put("openrepertory_patients","{\"patients\":{}}").put("openrepertory_cases","{\"cases\":{}}").toString());check((Boolean)healthy.invoke(damaged),"Explicit recovery");check(c.getNoBackupFilesDir().list((dir,name)->name.startsWith("records-"+id+".vault.before-restore")).length==1,"Retain damaged encrypted file");passed.add("tamper rejection and recoverable restore");
  Intent intent=new Intent(Intent.ACTION_MAIN).setClassName(c,"org.similora.app.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);activity=startActivitySync(intent);final Activity app=activity;
  runOnMainSync(()->{try{method(app.getClass(),"showAccount").invoke(app);}catch(Exception e){throw new RuntimeException(e);}});
  WebView auth=(WebView)field(app.getClass(),"auth").get(app);check(auth!=null,"Account screen exists");check(js(auth,"typeof openrepertorySecure + ':' + typeof openrepertoryNative").equals("\"undefined:undefined\""),"Remote pages have no native bridge");runOnMainSync(()->{WebSettings settings=auth.getSettings();check(!settings.getAllowFileAccess()&&!settings.getAllowContentAccess(),"Remote WebView cannot read files");check(settings.getMixedContentMode()==WebSettings.MIXED_CONTENT_NEVER_ALLOW,"Mixed content denied");});passed.add("remote login bridge isolation and WebView policy");
  // Exercise the bundled UI with a synthetic, locally granted fixture. No server accounts are changed.
  for(int i=0;i<20&&((java.util.concurrent.atomic.AtomicBoolean)field(app.getClass(),"checking").get(app)).get();i++)Thread.sleep(1000);
  final Object fixture=constructor.newInstance(c,id);final Method create=method(app.getClass(),"createClinical");runOnMainSync(()->{try{((Handler)field(app.getClass(),"ui").get(app)).removeCallbacks((Runnable)field(app.getClass(),"ticker").get(app));field(app.getClass(),"accountMode").setBoolean(app,false);field(app.getClass(),"uid").set(app,id);field(app.getClass(),"vault").set(app,fixture);field(app.getClass(),"approved").setBoolean(app,true);field(app.getClass(),"validUntil").setLong(app,SystemClock.elapsedRealtime()+300000);field(app.getClass(),"foreground").setBoolean(app,true);create.invoke(app);}catch(Exception e){throw new RuntimeException(e);}});
  WebView web=(WebView)field(app.getClass(),"clinical").get(app);boolean ready=false;for(int i=0;i<150;i++){Thread.sleep(1000);if(field(app.getClass(),"ready").getBoolean(app)){ready=true;break;}}check(ready,"Clinical UI loaded");check(js(web,"document.querySelector('.brand-name').textContent").equals("\"Similora\""),"Similora brand");passed.add("bundled native WebView startup and Similora UI");
  result.putString("result","PASS "+passed.toString());finish(Activity.RESULT_OK,result);
 }catch(Throwable error){result.putString("result","FAIL after "+passed+": "+error.toString());finish(Activity.RESULT_CANCELED,result);}
 finally{if(activity!=null){Activity app=activity;runOnMainSync(()->app.finish());}try{KeyStore ks=KeyStore.getInstance("AndroidKeyStore");ks.load(null);for(String value:new String[]{id,other}){for(File f:c.getNoBackupFilesDir().listFiles())if(f.getName().startsWith("records-"+value))f.delete();ks.deleteEntry("similora.vault."+value);}}catch(Exception ignored){}}
 }
}
