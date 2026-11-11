package org.similora.tests;
import android.app.*;import android.content.*;import android.os.*;import android.webkit.*;import org.json.*;import java.io.*;import java.lang.reflect.*;import java.util.concurrent.*;import java.net.*;import javax.net.ssl.*;
/** Live test account is injected only into the separate QA APK; never a production asset. */
public final class SessionChecks extends Instrumentation {
 Bundle args;
 @Override public void onCreate(Bundle b){super.onCreate(b);args=b;start();}
 Object field(Activity a,String name)throws Exception{Field f=a.getClass().getDeclaredField(name);f.setAccessible(true);return f.get(a);}
 String read(InputStream in)throws Exception{ByteArrayOutputStream out=new ByteArrayOutputStream();byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);return out.toString("UTF-8");}
 @Override public void onStart(){Bundle result=new Bundle();Activity app=null;try{
  JSONObject fixture=new JSONObject(read(getContext().getAssets().open("session-fixture.json")));String origin="https://similora.similora-167bde6ed1.workers.dev";boolean revoked="revoked".equals(args.getString("expect"));
  HttpsURLConnection request=(HttpsURLConnection)new URL(origin+"/api/session").openConnection();request.setConnectTimeout(15000);request.setReadTimeout(15000);request.setRequestProperty("Cookie",fixture.getString("cookie").split(";")[0]);int status=request.getResponseCode();if(status!=200)throw new AssertionError("Live native session status "+status);JSONObject identity=new JSONObject(read(request.getInputStream()));request.disconnect();if(!fixture.getString("uid").equals(identity.getString("id")))throw new AssertionError("Session identity mismatch");
  HttpsURLConnection owner=(HttpsURLConnection)new URL(origin+"/api/accounts").openConnection();owner.setConnectTimeout(15000);owner.setReadTimeout(15000);owner.setRequestProperty("Cookie",fixture.getString("cookie").split(";")[0]);if(owner.getResponseCode()!=403)throw new AssertionError("Member owner endpoint was not denied");owner.disconnect();
  CountDownLatch cookieReady=new CountDownLatch(1);runOnMainSync(()->{try{android.webkit.CookieManager.getInstance().setCookie(origin+"/",fixture.getString("cookie"),success->cookieReady.countDown());android.webkit.CookieManager.getInstance().flush();}catch(Exception e){throw new RuntimeException(e);}});if(!cookieReady.await(20,TimeUnit.SECONDS))throw new AssertionError("Cookie setup timed out");
  app=startActivitySync(new Intent(Intent.ACTION_MAIN).setClassName(getTargetContext(),"org.similora.app.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
  boolean done=false;for(int i=0;i<150;i++){Thread.sleep(1000);if(revoked){if(field(app,"auth")!=null&&!(Boolean)field(app,"approved")){done=true;break;}}else if((Boolean)field(app,"ready")&&(Boolean)field(app,"approved")){done=true;break;}}
  if(!done)throw new AssertionError("App did not reach expected "+(revoked?"revoked account screen":"approved clinical screen"));
  result.putString("result","PASS live native HTTPS session, correct account identity, owner API denial, "+(revoked?"revoked member blocked":"approved member entered bundled clinical workspace"));finish(Activity.RESULT_OK,result);
 }catch(Throwable error){result.putString("result","FAIL "+error.toString());finish(Activity.RESULT_CANCELED,result);}finally{if(app!=null){Activity activity=app;runOnMainSync(()->activity.finish());}}}
}
