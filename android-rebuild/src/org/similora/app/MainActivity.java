package org.similora.app;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.*;
import android.print.PrintManager;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import org.json.*;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/** Separate remote account screen and bundled clinical WebView; no remote native bridge. */
public final class MainActivity extends Activity {
 static final String ORIGIN="https://similora.similora-167bde6ed1.workers.dev";
 static final String LOCAL="https://appassets.androidplatform.net/assets/www/";
 final Handler ui=new Handler(Looper.getMainLooper());
 final ExecutorService network=Executors.newFixedThreadPool(3);
 final AtomicBoolean checking=new AtomicBoolean();
 final ConcurrentHashMap<String,HttpsURLConnection> aiConnections=new ConcurrentHashMap<>();
 final Set<String> cancelled=Collections.synchronizedSet(new HashSet<String>());
 FrameLayout root; StartupScene cover; TextView message; ProgressBar spinner; Button retry,account,updateButton;
 boolean accountLoadFailed=false;
 WebView clinical,auth; volatile boolean approved=false,foreground=false,accountMode=false,destroyed=false;
 volatile String uid=""; volatile long validUntil=0; volatile Vault vault;
 AppUpdates updates; ValueCallback<Uri[]> chooser;
 String exportId=null,exportText=null,exportName=null,exportUid=null; boolean ready=false;
 final Runnable ticker=new Runnable(){public void run(){if(foreground&&!accountMode)verifySession();if(!destroyed)ui.postDelayed(this,30000);}};
 final BroadcastReceiver lockReceiver=new BroadcastReceiver(){public void onReceive(Context c,Intent i){approved=false;mask("Device locked. Unlock to continue.",false);}};
 @Override public void onCreate(Bundle state){
  super.onCreate(state);getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
  root=new FrameLayout(this);root.setFitsSystemWindows(true);root.setBackgroundColor(Color.rgb(8,34,46));setContentView(root);
  cover=new StartupScene(this);
  message=new TextView(this);message.setTextSize(16);message.setTextColor(Color.rgb(233,245,245));message.setGravity(Gravity.CENTER);message.setPadding(0,8,0,8);message.setAccessibilityLiveRegion(View.ACCESSIBILITY_LIVE_REGION_POLITE);cover.controls.addView(message);
  spinner=new ProgressBar(this);cover.controls.addView(spinner,new LinearLayout.LayoutParams(36,36));
  retry=new Button(this);retry.setText("Try again");retry.setOnClickListener(v->{if(clinical!=null&&!ready&&!accountMode)clinical.reload();verifySession();});cover.controls.addView(retry);
  account=new Button(this);account.setText("Account & access");account.setOnClickListener(v->showAccount());cover.controls.addView(account);
  updateButton=new Button(this);updateButton.setText("App updates");updateButton.setOnClickListener(v->{if(updates!=null&&canUpdate())updates.show();});cover.controls.addView(updateButton);root.addView(cover,new FrameLayout.LayoutParams(-1,-1));
  WebView.setWebContentsDebuggingEnabled(false);CookieManager.getInstance().setAcceptCookie(true);
  try{updates=new AppUpdates(this,new AppUpdates.Host(){public boolean allowed(){return canUpdate();}public void beforeInstall(Runnable action){if(canUpdate())action.run();}});}catch(Exception e){message.setText("Update checks unavailable. Reopen the app to retry.");}
  IntentFilter filter=new IntentFilter(Intent.ACTION_SCREEN_OFF);if(Build.VERSION.SDK_INT>=33)registerReceiver(lockReceiver,filter,Context.RECEIVER_NOT_EXPORTED);else registerReceiver(lockReceiver,filter);
  mask("Checking your account…",true);ui.postDelayed(ticker,30000);
 }
 boolean canUpdate(){return foreground&&!destroyed&&!((KeyguardManager)getSystemService(KEYGUARD_SERVICE)).isDeviceLocked();}
 boolean permitted(){return approved&&vault!=null&&SystemClock.elapsedRealtime()<validUntil&&!destroyed;}
 void requireAccess(){if(!permitted())throw new SecurityException("Approved session required");}
 void mask(String text,boolean busy){if(destroyed)return;getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);cover.setVisibility(View.VISIBLE);cover.bringToFront();message.setText(text);cover.afterIntro=null;cover.active(foreground);spinner.setVisibility(busy?View.VISIBLE:View.GONE);retry.setVisibility(busy?View.GONE:View.VISIBLE);account.setVisibility(busy?View.GONE:View.VISIBLE);updateButton.setVisibility(busy?View.GONE:View.VISIBLE);}
 void settings(WebView view){WebSettings s=view.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(false);s.setAllowContentAccess(false);s.setAllowFileAccessFromFileURLs(false);s.setAllowUniversalAccessFromFileURLs(false);s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);s.setMediaPlaybackRequiresUserGesture(true);if(Build.VERSION.SDK_INT>=26)s.setSafeBrowsingEnabled(true);CookieManager.getInstance().setAcceptThirdPartyCookies(view,false);}
 boolean accountUrl(Uri uri){return "https".equals(uri.getScheme())&&Uri.parse(ORIGIN).getHost().equals(uri.getHost())&&(uri.getPort()==-1||uri.getPort()==443)&&uri.getUserInfo()==null;}
 boolean navigateAccount(Uri u){if(!accountUrl(u))return true;if(u.getPath()!=null&&u.getPath().startsWith("/workspace")){accountMode=false;mask("Opening your clinical workspace…",true);verifySession();return true;}return false;}
 void showAccount(){
  accountMode=true;approved=false;cancelPending();if(updates!=null)updates.pause();
  if(auth==null){auth=new WebView(this);settings(auth);root.addView(auth,new FrameLayout.LayoutParams(-1,-1));auth.setWebViewClient(new WebViewClient(){
   @Override public boolean shouldOverrideUrlLoading(WebView view,WebResourceRequest r){return navigateAccount(r.getUrl());}
   @Override public boolean shouldOverrideUrlLoading(WebView view,String url){return navigateAccount(Uri.parse(url));}
   @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest r){return accountUrl(r.getUrl())?null:denied();}
   @Override public void onReceivedSslError(WebView v,SslErrorHandler h,SslError e){accountLoadFailed=true;h.cancel();mask("Secure connection could not be verified. Check your phone’s date and connection.",false);}
   @Override public void onReceivedError(WebView v,WebResourceRequest r,WebResourceError e){if(r.isForMainFrame()){accountLoadFailed=true;mask("Account page could not load. Check your connection and try again.",false);}}
   @Override public void onPageFinished(WebView v,String url){CookieManager.getInstance().flush();if(accountMode&&!accountLoadFailed&&foreground){cover.whenFinished(()->{if(accountMode&&!accountLoadFailed&&foreground){cover.active(false);cover.setVisibility(View.GONE);auth.bringToFront();getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);}});}}
  });}
  if(clinical!=null)clinical.setVisibility(View.GONE);auth.setVisibility(View.VISIBLE);auth.bringToFront();mask("Opening account & access…",true);accountLoadFailed=false;auth.loadUrl(ORIGIN+"/account?nativeIntro=1");
 }
 void verifySession(){
  if(destroyed||checking.getAndSet(true))return;
  KeyguardManager km=(KeyguardManager)getSystemService(KEYGUARD_SERVICE);if(km.isDeviceLocked()){checking.set(false);approved=false;mask("Unlock your phone to continue.",false);return;}
  final String cookies=CookieManager.getInstance().getCookie(ORIGIN+"/");
  network.execute(()->{try{
   HttpsURLConnection connection=(HttpsURLConnection)new URL(ORIGIN+"/api/session").openConnection();connection.setInstanceFollowRedirects(false);connection.setConnectTimeout(12000);connection.setReadTimeout(12000);connection.setRequestProperty("Accept","application/json");if(cookies!=null)connection.setRequestProperty("Cookie",cookies);
   JSONObject data;try{int status=connection.getResponseCode();if(status==401||status==403){ui.post(()->{approved=false;showAccount();});return;}if(status!=200)throw new IOException("Session service unavailable");data=new JSONObject(readLimited(connection.getInputStream(),65536));}finally{connection.disconnect();}
   if(!"approved".equals(data.optString("status"))){ui.post(()->{approved=false;showAccount();});return;}
   String next=data.getString("id");if(!next.matches("[a-f0-9]{32}"))throw new IOException("Invalid account identity");
   Vault nextVault=next.equals(uid)&&vault!=null?vault:new Vault(this,next);
   ui.post(()->{if(destroyed||accountMode)return;if(((KeyguardManager)getSystemService(KEYGUARD_SERVICE)).isDeviceLocked()){approved=false;mask("Unlock your phone to continue.",false);return;}boolean changed=!next.equals(uid);if(changed){cancelPending();if(clinical!=null){clinical.removeJavascriptInterface("openrepertorySecure");clinical.removeJavascriptInterface("openrepertoryNative");root.removeView(clinical);clinical.destroy();clinical=null;ready=false;}}
    uid=next;vault=nextVault;
    if(exportText==null&&vault.healthy())try{String pending=vault.read("similora.pending_export");if(pending!=null){JSONObject draft=new JSONObject(pending);exportName=draft.getString("name");exportText=draft.getString("text");exportUid=uid;}}catch(Exception ignored){}
    approved=true;validUntil=SystemClock.elapsedRealtime()+60000;if(auth!=null)auth.setVisibility(View.GONE);if(clinical==null)createClinical();else clinical.setVisibility(View.VISIBLE);if(ready&&foreground)reveal();
   });
  }catch(Exception e){ui.post(()->{approved=false;mask("Connection or encrypted storage could not be verified. Your saved records remain on this phone. Try again; do not clear app data.",false);});}finally{checking.set(false);}});
 }
 static String readLimited(InputStream in,int limit)throws IOException{try(InputStream input=in;ByteArrayOutputStream out=new ByteArrayOutputStream()){byte[] buffer=new byte[8192];int n;while((n=input.read(buffer))!=-1){if(out.size()+n>limit)throw new IOException("Response too large");out.write(buffer,0,n);}return out.toString("UTF-8");}}
 static WebResourceResponse denied(){return new WebResourceResponse("text/plain","UTF-8",403,"Forbidden",Collections.singletonMap("Cache-Control","no-store"),new ByteArrayInputStream(new byte[0]));}
 void createClinical(){
  mask("Preparing your clinical workspace…",true);clinical=new WebView(this);settings(clinical);clinical.setBackgroundColor(Color.rgb(238,246,248));root.addView(clinical,0,new FrameLayout.LayoutParams(-1,-1));clinical.addJavascriptInterface(new SecureBridge(),"openrepertorySecure");clinical.addJavascriptInterface(new NativeBridge(),"openrepertoryNative");
  clinical.setWebViewClient(new WebViewClient(){
   @Override public WebResourceResponse shouldInterceptRequest(WebView v,WebResourceRequest r){Uri u=r.getUrl();if(!"https".equals(u.getScheme())||!"appassets.androidplatform.net".equals(u.getHost())||u.getPort()!=-1||!r.getMethod().equals("GET"))return denied();String path=u.getPath();if(path==null||!path.startsWith("/assets/")||path.contains("..")||path.contains("\\")||path.indexOf(0)>=0)return denied();try{String name=path.substring(8);String mime=name.endsWith(".js")?"text/javascript":name.endsWith(".css")?"text/css":name.endsWith(".html")?"text/html":name.endsWith(".json")?"application/json":name.endsWith(".png")?"image/png":name.endsWith(".svg")?"image/svg+xml":name.endsWith(".ttf")?"font/ttf":"application/octet-stream";return new WebResourceResponse(mime,"UTF-8",200,"OK",Collections.singletonMap("Cache-Control","no-store"),getAssets().open(name));}catch(Exception e){return new WebResourceResponse("text/plain","UTF-8",404,"Not found",Collections.emptyMap(),new ByteArrayInputStream(new byte[0]));}}
   @Override public boolean shouldOverrideUrlLoading(WebView v,WebResourceRequest r){if(r.getUrl().toString().equals(LOCAL+"index.html"))return false;if(r.hasGesture()&&r.isForMainFrame()&&"https".equals(r.getUrl().getScheme()))try{startActivity(new Intent(Intent.ACTION_VIEW,r.getUrl()).addCategory(Intent.CATEGORY_BROWSABLE));}catch(Exception ignored){}return true;}
   @Override public boolean shouldOverrideUrlLoading(WebView v,String url){return !url.equals(LOCAL+"index.html");}
   @Override public void onPageStarted(WebView v,String url,android.graphics.Bitmap icon){ready=false;if(!accountMode)mask("Preparing your clinical workspace…",true);}
   @Override public void onPageFinished(WebView v,String url){if(!url.equals(LOCAL+"index.html"))return;v.evaluateJavascript("(function wait(){if(document.getElementById('recoveryScreen')||(window.OR&&OR.state.repertory&&!OR.state.repertoryLoading)){openrepertoryNative.workspaceReady()}else setTimeout(wait,100)})()",null);}
   @Override public void onReceivedSslError(WebView v,SslErrorHandler h,SslError e){h.cancel();}
  });
  clinical.setWebChromeClient(new WebChromeClient(){@Override public boolean onShowFileChooser(WebView w,ValueCallback<Uri[]> callback,FileChooserParams params){if(!permitted()){callback.onReceiveValue(null);return true;}if(chooser!=null)chooser.onReceiveValue(null);chooser=callback;try{Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("*/*");startActivityForResult(i,92);}catch(Exception e){chooser.onReceiveValue(null);chooser=null;}return true;}});
  clinical.loadUrl(LOCAL+"index.html");ui.postDelayed(()->{if(!ready&&!destroyed&&!accountMode)mask("The workspace is taking longer than expected. Check your connection and retry, or reopen Similora.",false);},45000);
 }
 void reveal(){if(!permitted()||!foreground||accountMode)return;if(!cover.finished){cover.whenFinished(()->reveal());cover.bringToFront();return;}cover.active(false);cover.setVisibility(View.GONE);clinical.setVisibility(View.VISIBLE);clinical.bringToFront();if(updates!=null)updates.checkAutomatically();if(getPreferences(0).getBoolean("screenshots",true))getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);}
 void complete(String id,String value,String error,String owner){ui.post(()->{if(clinical==null||!owner.equals(uid)||cancelled.remove(id))return;clinical.evaluateJavascript("window.ORNativeComplete&&ORNativeComplete("+JSONObject.quote(id)+","+(value==null?"null":JSONObject.quote(value))+","+(error==null?"null":JSONObject.quote(error))+")",null);});}
 void cancelPending(){for(HttpsURLConnection c:aiConnections.values())c.disconnect();aiConnections.clear();if(chooser!=null){chooser.onReceiveValue(null);chooser=null;}exportId=null;exportText=null;exportName=null;exportUid=null;}
 String aiSettings(){requireAccess();String saved=vault.read("similora.secret.ai");if(saved!=null)return saved;String old=vault.read("similora.secret.gemini");if(old==null)return null;try{return new JSONObject().put("provider","gemini").put("model","gemini-2.5-flash").put("key",old).toString();}catch(Exception e){return null;}}
 void cancelAi(){for(HttpsURLConnection c:aiConnections.values())c.disconnect();aiConnections.clear();}
 public final class SecureBridge {
  @JavascriptInterface public String storageHealth(){return permitted()?(vault.healthy()?"ok":"recovery"):"locked";}
  @JavascriptInterface public String read(String key){requireAccess();if(key.startsWith("similora.secret."))return null;return vault.read(key);}
  @JavascriptInterface public String writeBatch(String json){try{requireAccess();JSONObject values=new JSONObject(json);for(Iterator<String> it=values.keys();it.hasNext();)if(it.next().startsWith("similora.secret."))throw new SecurityException();vault.batch(json);return "ok";}catch(Exception e){return "error";}}
  @JavascriptInterface public String writePatient(String record,String active){try{requireAccess();vault.patient(record,active);return "ok";}catch(Exception e){return "error";}}
  @JavascriptInterface public String restoreRecords(String values){try{requireAccess();vault.restore(values);return "ok";}catch(Exception e){return "error";}}
  @JavascriptInterface public void enterRecovery(){}
  @JavascriptInterface public void cancel(String id){cancelled.add(id);HttpsURLConnection c=aiConnections.remove(id);if(c!=null)c.disconnect();ui.post(()->{if(id.equals(exportId)){exportId=null;exportText=null;}});}
  @JavascriptInterface public String pendingExport(){return exportText==null?null:exportName;}
  @JavascriptInterface public void discardExport(){try{requireAccess();vault.batch(new JSONObject().put("similora.pending_export",JSONObject.NULL).toString());ui.post(()->cancelPending());}catch(Exception ignored){}}
  @JavascriptInterface public void saveDocument(String id,String name,String content){String owner=uid;try{requireAccess();if(content.length()>24*1024*1024)throw new IOException();ui.post(()->{if(!permitted()||!owner.equals(uid)){complete(id,null,"Approved session required",owner);return;}if(exportText!=null){complete(id,null,"Finish the pending export first",owner);return;}exportId=id;exportName=name.replaceAll("[^A-Za-z0-9._-]","_");exportText=content;exportUid=owner;try{vault.batch(new JSONObject().put("similora.pending_export",new JSONObject().put("name",exportName).put("text",exportText).toString()).toString());launchExport();}catch(Exception e){complete(id,null,"Could not retain encrypted export",owner);cancelPending();}});}catch(Exception e){complete(id,null,"Export could not start",owner);}}
  @JavascriptInterface public void resumeExport(String id){ui.post(()->{if(!permitted()||exportText==null||!uid.equals(exportUid)){complete(id,null,"No pending export",uid);return;}exportId=id;launchExport();});}
  @JavascriptInterface public void printDocument(){ui.post(()->{if(permitted())((PrintManager)getSystemService(PRINT_SERVICE)).print("Similora report",clinical.createPrintDocumentAdapter("Similora report"),null);});}
  @JavascriptInterface public void aiAsync(String id,String prompt,boolean test){String owner=uid;Vault captured=vault;network.execute(()->{String code="network_error";try{requireAccess();if(!owner.equals(uid)||captured!=vault)return;String snapshot=aiSettings();if(snapshot==null){code="not_configured";throw new IOException();}JSONObject config=UserAi.validate(snapshot);if(cancelled.contains(id))return;
   HttpsURLConnection c=(HttpsURLConnection)new URL(UserAi.endpoint(config)).openConnection();c.setInstanceFollowRedirects(false);c.setConnectTimeout(12000);c.setReadTimeout(45000);c.setRequestMethod("POST");c.setDoOutput(true);c.setRequestProperty("Content-Type","application/json");if(config.getString("provider").equals("openai"))c.setRequestProperty("Authorization","Bearer "+config.getString("key"));else c.setRequestProperty("x-goog-api-key",config.getString("key"));aiConnections.put(id,c);
   try{if(cancelled.contains(id)||!owner.equals(uid)||!snapshot.equals(aiSettings()))return;JSONObject body=UserAi.body(config,test?"Return JSON {\"ok\":true}":prompt);try(OutputStream out=c.getOutputStream()){out.write(body.toString().getBytes(StandardCharsets.UTF_8));}int status=c.getResponseCode();if(status!=200){code=UserAi.error(status);throw new IOException();}code="invalid_response";String value=UserAi.parse(config,readLimited(c.getInputStream(),1024*1024));if(test&&!new JSONObject(value).optBoolean("ok"))throw new IOException();requireAccess();if(!owner.equals(uid)||!snapshot.equals(aiSettings()))return;complete(id,value,null,owner);}finally{aiConnections.remove(id);c.disconnect();}
  }catch(Exception e){try{complete(id,new JSONObject().put("error",code).put("detail","AI request could not complete. Check provider, model, key, quota and connection. Offline search remains available.").toString(),null,owner);}catch(Exception ignored){}}});}
  @JavascriptInterface public void geminiAsync(String id,String prompt,boolean test){String owner=uid;Vault store=vault;network.execute(()->{try{requireAccess();if(!owner.equals(uid)||store!=vault||cancelled.contains(id))throw new SecurityException();String key=store.read("similora.secret.gemini");if(key==null)throw new IOException("Add a Gemini key first");if(prompt.length()>20000)throw new IOException("Request too long");
   HttpsURLConnection c=(HttpsURLConnection)new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent").openConnection();c.setInstanceFollowRedirects(false);c.setRequestMethod("POST");c.setDoOutput(true);c.setConnectTimeout(12000);c.setReadTimeout(60000);c.setRequestProperty("Content-Type","application/json");c.setRequestProperty("x-goog-api-key",key);aiConnections.put(id,c);
   JSONObject body=new JSONObject().put("contents",new JSONArray().put(new JSONObject().put("parts",new JSONArray().put(new JSONObject().put("text",test?"Return JSON {\"ok\":true}":prompt))))).put("generationConfig",new JSONObject().put("responseMimeType","application/json"));
   try{try(OutputStream out=c.getOutputStream()){out.write(body.toString().getBytes(StandardCharsets.UTF_8));}int status=c.getResponseCode();if(status!=200)throw new IOException(status==401||status==403?"Gemini key was rejected":status==429?"Gemini quota reached":"Gemini service unavailable");JSONObject response=new JSONObject(readLimited(c.getInputStream(),1024*1024));JSONArray parts=response.getJSONArray("candidates").getJSONObject(0).getJSONObject("content").getJSONArray("parts");StringBuilder text=new StringBuilder();for(int i=0;i<parts.length();i++)text.append(parts.getJSONObject(i).optString("text"));new JSONObject(text.toString());complete(id,text.toString(),null,owner);}finally{aiConnections.remove(id);c.disconnect();}
  }catch(Exception e){complete(id,null,e instanceof IOException?e.getMessage():"AI request could not complete",owner);}});}
 }
 public final class NativeBridge {
  @JavascriptInterface public void workspaceReady(){ui.post(()->{ready=true;reveal();});}
  @JavascriptInterface public void openAccount(){ui.post(()->showAccount());}
  @JavascriptInterface public void openUpdates(){ui.post(()->{if(permitted()&&updates!=null)updates.show();});}
  @JavascriptInterface public void setTextScale(int percent){} // Clinical CSS owns sizing; do not apply a second scale.
  @JavascriptInterface public void setSensitiveScreen(boolean sensitive){ui.post(()->{if(!foreground||!getPreferences(0).getBoolean("screenshots",true))getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);});}
  @JavascriptInterface public void openPrivacy(){ui.post(()->new AlertDialog.Builder(MainActivity.this).setTitle("Screen privacy").setMultiChoiceItems(new String[]{"Allow screenshots while using Similora"},new boolean[]{getPreferences(0).getBoolean("screenshots",true)},(dialog,which,on)->{getPreferences(0).edit().putBoolean("screenshots",on).apply();if(on&&foreground&&permitted())getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);else getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);}).setPositiveButton("Done",null).show());}
  @JavascriptInterface public String aiConfig(){try{String raw=aiSettings();if(raw==null)return "{}";JSONObject c=UserAi.validate(raw);return new JSONObject().put("provider",c.getString("provider")).put("model",c.getString("model")).put("connected",true).toString();}catch(Exception e){return "{}";}}
  @JavascriptInterface public String aiProviderName(){try{return new JSONObject(aiConfig()).optString("provider").equals("openai")?"OpenAI":"Gemini";}catch(Exception e){return "AI";}}
  @JavascriptInterface public boolean hasAiConfig(){try{return aiSettings()!=null;}catch(Exception e){return false;}}
  @JavascriptInterface public boolean saveAiConfig(String raw){try{requireAccess();JSONObject input=new JSONObject(raw);if(input.optString("key").isEmpty()){String old=aiSettings();if(old==null)return false;JSONObject saved=new JSONObject(old);if(!saved.getString("provider").equals(input.optString("provider")))return false;input.put("key",saved.getString("key"));}JSONObject c=UserAi.validate(input.toString());cancelAi();vault.batch(new JSONObject().put("similora.secret.ai",c.toString()).put("similora.secret.gemini",JSONObject.NULL).toString());return true;}catch(Exception e){return false;}}
  @JavascriptInterface public boolean clearAiConfig(){try{requireAccess();cancelAi();vault.batch(new JSONObject().put("similora.secret.ai",JSONObject.NULL).put("similora.secret.gemini",JSONObject.NULL).toString());return true;}catch(Exception e){return false;}}
  @JavascriptInterface public boolean hasGeminiKey(){return permitted()&&vault.healthy()&&vault.read("similora.secret.gemini")!=null;}
  @JavascriptInterface public boolean setGeminiKey(String key){try{requireAccess();if(key.length()<10||key.length()>512||!key.matches("[A-Za-z0-9_-]+"))return false;vault.batch(new JSONObject().put("similora.secret.gemini",key).toString());return true;}catch(Exception e){return false;}}
  @JavascriptInterface public boolean clearGeminiKey(){try{requireAccess();vault.batch(new JSONObject().put("similora.secret.gemini",JSONObject.NULL).toString());return true;}catch(Exception e){return false;}}
  @JavascriptInterface public String getGeminiSaveError(){return "Encrypted key could not be saved";}
 }
 void launchExport(){try{startActivityForResult(new Intent(Intent.ACTION_CREATE_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("application/octet-stream").putExtra(Intent.EXTRA_TITLE,exportName),91);}catch(Exception e){complete(exportId,null,"Document picker unavailable",uid);cancelPending();}}
 @Override protected void onActivityResult(int request,int result,Intent data){super.onActivityResult(request,result,data);if(request==92){if(chooser!=null){chooser.onReceiveValue(result==RESULT_OK&&data!=null?new Uri[]{data.getData()}:null);chooser=null;}return;}if(request==91&&exportId!=null){String id=exportId,owner=exportUid,content=exportText;exportId=null;if(result!=RESULT_OK||data==null){complete(id,null,"Export cancelled; you can resume from Backup & restore",owner);return;}Uri uri=data.getData();network.execute(()->{try{if(!owner.equals(uid))throw new SecurityException();try(OutputStream out=getContentResolver().openOutputStream(uri,"wt")){if(out==null)throw new IOException();out.write(content.getBytes(StandardCharsets.UTF_8));}ui.post(()->{if(owner.equals(exportUid)){try{vault.batch(new JSONObject().put("similora.pending_export",JSONObject.NULL).toString());}catch(Exception ignored){}exportText=null;exportName=null;exportUid=null;}});complete(id,"saved",null,owner);}catch(Exception e){complete(id,null,"File could not be saved. Retry the export.",owner);}});}}
 @Override protected void onResume(){super.onResume();foreground=true;cover.active(true);if(!accountMode){mask("Checking access…",true);verifySession();}else if(auth!=null){auth.onResume();if(!accountLoadFailed&&auth.getProgress()==100)cover.whenFinished(()->{if(foreground&&accountMode&&!accountLoadFailed){cover.setVisibility(View.GONE);auth.bringToFront();}});}if(clinical!=null)clinical.onResume();}
 @Override protected void onPause(){foreground=false;cover.active(false);getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);if(!accountMode)mask("Similora is paused",false);if(updates!=null)updates.pause();CookieManager.getInstance().flush();super.onPause();}
 @Override public void onBackPressed(){if(accountMode){accountMode=false;mask("Returning to your workspace…",true);verifySession();return;}if(clinical!=null&&permitted()){clinical.evaluateJavascript("window.OR&&OR.clinic&&OR.clinic.back?OR.clinic.back():OR.showPage('dashboard')",null);}else super.onBackPressed();}
 @Override protected void onDestroy(){destroyed=true;cover.dispose();approved=false;ui.removeCallbacksAndMessages(null);try{unregisterReceiver(lockReceiver);}catch(Exception ignored){}if(updates!=null)updates.dispose();network.shutdownNow();cancelPending();if(clinical!=null){clinical.removeJavascriptInterface("openrepertorySecure");clinical.removeJavascriptInterface("openrepertoryNative");clinical.destroy();}if(auth!=null)auth.destroy();super.onDestroy();}
}
