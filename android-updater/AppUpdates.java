package com.openrepertory.app;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.Uri;
import android.os.*;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.*;
import org.json.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.concurrent.*;

/** Native, signed, optional full-APK updates. No publishing credentials or medical data. */
public final class AppUpdates {
    public static final long MAX_APK=200L*1024*1024, CHECK_INTERVAL=24L*60*60*1000;
    public static final String MANIFEST_URL="https://github.com/"+UpdateConfig.REPOSITORY+"/releases/latest/download/update.json";
    public interface Host {boolean allowed(); void beforeInstall(Runnable action);}
    public interface TransferEvents {boolean cancelled(); void progress(int percent);}
    public static void transfer(InputStream in,File destination,long expected,TransferEvents events)throws Exception {
        if(expected<=0||expected>MAX_APK)throw new SecurityException("Invalid download size");
        long count=0,last=0;
        try(FileOutputStream out=new FileOutputStream(destination)){
            byte[] b=new byte[65536];int n;
            while((n=in.read(b))!=-1){
                if(events.cancelled())throw new InterruptedIOException("Cancelled");
                count+=n;if(count>expected)throw new SecurityException("Download too large");out.write(b,0,n);
                long now=SystemClock.elapsedRealtime();if(now-last>250){last=now;events.progress((int)(count*100/expected));}
            }
            if(events.cancelled())throw new InterruptedIOException("Cancelled");
            if(count!=expected)throw new SecurityException("Incomplete download");
            out.getFD().sync();
        }catch(Exception e){destination.delete();throw e;}
    }
    public static final class Release {
        public final int code,minSdk;
        public final long size;
        public final String name,url,sha256,notes,raw;
        Release(JSONObject p,String envelope)throws Exception {
            code=p.getInt("versionCode");minSdk=p.getInt("minSdk");size=p.getLong("size");
            name=p.getString("versionName");url=p.getString("apkUrl");sha256=p.getString("sha256");notes=p.getString("notes");raw=envelope;
        }
    }
    private final Activity activity;
    private final Host host;
    private final SharedPreferences prefs;
    private final File directory,apk,partial,manifest;
    private final ExecutorService worker=Executors.newSingleThreadExecutor();
    private final Handler main=new Handler(Looper.getMainLooper());
    private volatile boolean disposed,cancelled,working;
    private volatile HttpURLConnection connection;
    private volatile Release release;
    private AlertDialog dialog,notesDialog;
    private TextView text;
    private Button checkButton,downloadButton,cancelButton,notesButton;
    private ProgressBar progress;
    private String status="Check for a newer version. Your records stay on this phone.";
    private int percent=-1;
    private final int installedCode;
    private final String installedName;

    public AppUpdates(Activity a,Host h)throws Exception {
        activity=a;host=h;prefs=a.getSharedPreferences("openrepertory.updates",0);
        directory=new File(a.getFilesDir(),"updates");if(!directory.exists()&&!directory.mkdirs())throw new IOException("Update storage unavailable");
        apk=new File(directory,"update.apk");partial=new File(directory,"update.part");manifest=new File(directory,"manifest.json");
        partial.delete();
        PackageInfo current=a.getPackageManager().getPackageInfo(a.getPackageName(),0);
        installedCode=current.versionCode;installedName=current.versionName;
    }
    public static boolean trustedUrl(URL u){
        if(!"https".equals(u.getProtocol())||u.getUserInfo()!=null||(u.getPort()!=-1&&u.getPort()!=443)||u.getRef()!=null)return false;
        return Arrays.asList("github.com","release-assets.githubusercontent.com","objects.githubusercontent.com","github-releases.githubusercontent.com").contains(u.getHost());
    }
    /** Signature covers exact payload bytes; no ambiguous JSON canonicalization. */
    public static Release parseSigned(String envelope,String publicKey)throws Exception {
        if(envelope.length()>65536)throw new SecurityException("Update information too large");
        JSONObject outer=new JSONObject(envelope);
        if(outer.getInt("schema")!=1)throw new SecurityException("Unknown update format");
        byte[] payload=Base64.decode(outer.getString("payload"),Base64.NO_WRAP);
        java.security.Signature verifier=java.security.Signature.getInstance("SHA256withRSA");
        verifier.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(publicKey,Base64.NO_WRAP))));
        verifier.update(payload);
        if(!verifier.verify(Base64.decode(outer.getString("signature"),Base64.NO_WRAP)))throw new SecurityException("Invalid update signature");
        JSONObject p=new JSONObject(new String(payload,"UTF-8"));
        if(p.getInt("schema")!=1||!"com.openrepertory.app.dev".equals(p.getString("packageName"))||!UpdateConfig.REPOSITORY.equals(p.getString("repository")))throw new SecurityException("Wrong update identity");
        Release r=new Release(p,envelope);URL url=new URL(r.url);
        String prefix="/"+UpdateConfig.REPOSITORY+"/releases/download/";
        if(!trustedUrl(url)||!"github.com".equals(url.getHost())||!url.getPath().startsWith(prefix)||url.getQuery()!=null||url.getPath().contains("..")
            ||!url.getPath().endsWith(".apk")||r.code<=0||r.minSdk<23||r.size<=0||r.size>MAX_APK||!r.sha256.matches("[a-f0-9]{64}")
            ||r.name.length()>80||r.notes.length()>12000||p.getString("publishedAt").length()>80)throw new SecurityException("Invalid update information");
        return r;
    }
    public static String hash(File f)throws Exception {
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        try(InputStream in=new FileInputStream(f)){byte[] b=new byte[65536];int n;while((n=in.read(b))!=-1)md.update(b,0,n);}
        StringBuilder out=new StringBuilder();for(byte b:md.digest())out.append(String.format(Locale.US,"%02x",b&255));return out.toString();
    }
    @SuppressWarnings("deprecation") public static void validateApk(Context c,File file,Release r)throws Exception {
        if(!file.isFile()||file.length()!=r.size||!r.sha256.equals(hash(file)))throw new SecurityException("Downloaded APK does not match the signed release");
        PackageManager pm=c.getPackageManager();PackageInfo candidate=pm.getPackageArchiveInfo(file.getAbsolutePath(),PackageManager.GET_SIGNATURES);
        PackageInfo current=pm.getPackageInfo(c.getPackageName(),PackageManager.GET_SIGNATURES);
        if(candidate==null||!c.getPackageName().equals(candidate.packageName)||candidate.versionCode!=r.code||candidate.versionCode<=current.versionCode
            ||!r.name.equals(candidate.versionName)||candidate.signatures==null||current.signatures==null||candidate.signatures.length!=1||current.signatures.length!=1
            ||!candidate.signatures[0].equals(current.signatures[0]))throw new SecurityException("APK identity, version or signing certificate does not match");
        byte[] digest=MessageDigest.getInstance("SHA-256").digest(candidate.signatures[0].toByteArray());
        StringBuilder hex=new StringBuilder();for(byte b:digest)hex.append(String.format(Locale.US,"%02x",b&255));
        if(!UpdateConfig.CERTIFICATE.equals(hex.toString()))throw new SecurityException("Unexpected signing identity");
        if(Build.VERSION.SDK_INT<r.minSdk||(Build.VERSION.SDK_INT>=24&&candidate.applicationInfo.minSdkVersion>Build.VERSION.SDK_INT))throw new SecurityException("This update needs a newer Android version");
    }
    private static String read(File file)throws Exception {
        if(file.length()>65536)throw new IOException("Update information too large");
        try(InputStream in=new FileInputStream(file);ByteArrayOutputStream out=new ByteArrayOutputStream()){
            byte[] b=new byte[4096];int n;while((n=in.read(b))!=-1)out.write(b,0,n);return out.toString("UTF-8");
        }
    }
    private void saveManifest(Release r)throws Exception {
        File temp=new File(directory,"manifest.tmp");
        try(FileOutputStream out=new FileOutputStream(temp)){out.write(r.raw.getBytes("UTF-8"));out.getFD().sync();}
        if(!temp.renameTo(manifest))throw new IOException("Cannot retain update information");
    }
    private HttpURLConnection open(URL url)throws Exception {
        for(int i=0;i<6;i++){
            if(disposed||cancelled)throw new InterruptedIOException("Cancelled");
            if(!trustedUrl(url))throw new SecurityException("Untrusted update destination");
            HttpURLConnection c=(HttpURLConnection)url.openConnection();connection=c;
            c.setInstanceFollowRedirects(false);c.setConnectTimeout(15000);c.setReadTimeout(30000);
            c.setRequestProperty("User-Agent","OpenRepertory-Android/"+installedName);c.setRequestProperty("Accept-Encoding","identity");
            int response=c.getResponseCode();
            if(response==301||response==302||response==303||response==307||response==308){String location=c.getHeaderField("Location");c.disconnect();if(location==null)throw new IOException("Missing download location");url=new URL(url,location);continue;}
            if(response==404){c.disconnect();throw new FileNotFoundException("No published update is available yet");}
            if(response!=200){c.disconnect();throw new IOException("Update server returned "+response);}
            return c;
        }
        throw new IOException("Too many update redirects");
    }
    private String fetchManifest()throws Exception {
        HttpURLConnection c=open(new URL(MANIFEST_URL));
        try(InputStream in=c.getInputStream();ByteArrayOutputStream out=new ByteArrayOutputStream()){
            byte[] b=new byte[4096];int n;while((n=in.read(b))!=-1){if(disposed||cancelled)throw new InterruptedIOException();if(out.size()+n>65536)throw new SecurityException("Update information too large");out.write(b,0,n);}return out.toString("UTF-8");
        }finally{c.disconnect();connection=null;}
    }
    private void post(Runnable action){main.post(()->{if(!disposed)action.run();});}
    private void redraw(){
        if(dialog==null||!dialog.isShowing()||text==null)return;
        String last=prefs.getLong("lastSuccess",0)>0?java.text.DateFormat.getDateTimeInstance().format(new Date(prefs.getLong("lastSuccess",0))):"Never";
        String available=release==null?"":("\nAvailable: "+release.name+" · "+String.format(Locale.US,"%.1f MB",release.size/1048576.0));
        text.setText("Installed: "+installedName+available+"\n\n"+status+"\nLast checked: "+last);
        if(notesButton!=null)notesButton.setVisibility(release==null?View.GONE:View.VISIBLE);
        progress.setVisibility(working?View.VISIBLE:View.GONE);progress.setIndeterminate(percent<0);if(percent>=0)progress.setProgress(percent);
        checkButton.setEnabled(!working);downloadButton.setVisibility(release==null?View.GONE:View.VISIBLE);downloadButton.setEnabled(!working);
        downloadButton.setText(apk.isFile()?"Install update":"Download update");cancelButton.setVisibility(working?View.VISIBLE:View.GONE);
    }
    public void show(){
        if(disposed||!host.allowed())return;
        if(dialog!=null&&dialog.isShowing())return;
        LinearLayout box=new LinearLayout(activity);box.setOrientation(LinearLayout.VERTICAL);int p=(int)(20*activity.getResources().getDisplayMetrics().density);box.setPadding(p,p,p,p);
        text=new TextView(activity);text.setTextIsSelectable(true);text.setTextSize(14);text.setTypeface(android.graphics.Typeface.create("sans-serif",0));text.setLineSpacing(4,1);box.addView(text);
        notesButton=new Button(activity);notesButton.setText("What’s new");notesButton.setAllCaps(false);notesButton.setTypeface(android.graphics.Typeface.create("sans-serif",0));notesButton.setOnClickListener(v->showReleaseNotes());box.addView(notesButton);
        progress=new ProgressBar(activity,null,android.R.attr.progressBarStyleHorizontal);progress.setMax(100);box.addView(progress,new LinearLayout.LayoutParams(-1,-2));
        checkButton=new Button(activity);checkButton.setText("Check for updates");checkButton.setOnClickListener(v->check(true));box.addView(checkButton);
        downloadButton=new Button(activity);downloadButton.setOnClickListener(v->{if(apk.isFile())install();else download();});box.addView(downloadButton);box.removeView(downloadButton);box.addView(downloadButton,box.indexOfChild(checkButton));downloadButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xff235fcf));downloadButton.setTextColor(0xffffffff);
        cancelButton=new Button(activity);cancelButton.setText("Cancel download / check");cancelButton.setOnClickListener(v->cancel());box.addView(cancelButton);
        ScrollView scroll=new ScrollView(activity);scroll.addView(box);
        TextView title=new TextView(activity);title.setText("App updates");title.setTextSize(21);title.setTypeface(android.graphics.Typeface.create("sans-serif-medium",0));title.setPadding(p,p,p,0);
        for(Button button:new Button[]{checkButton,downloadButton,cancelButton}){button.setAllCaps(false);button.setTypeface(android.graphics.Typeface.create("sans-serif-medium",0));button.setTextSize(14);button.setMinHeight((int)(44*activity.getResources().getDisplayMetrics().density));}
        dialog=new AlertDialog.Builder(activity).setCustomTitle(title).setView(scroll).setPositiveButton("Close",null).create();dialog.show();redraw();
        if(!working&&release==null){working=true;redraw();worker.execute(()->{
            Release cached=null;try{if(manifest.isFile()){Release r=parseSigned(read(manifest),UpdateConfig.PUBLIC_KEY);if(r.code>installedCode&&Build.VERSION.SDK_INT>=r.minSdk){cached=r;if(apk.isFile())validateApk(activity,apk,r);}else{apk.delete();manifest.delete();}}}catch(Exception e){apk.delete();manifest.delete();cached=null;}
            final Release result=cached;post(()->{release=result;working=false;if(result!=null)status=apk.isFile()?"Download verified. Tap Install update.":"Update available. Tap Download update.";redraw();});
        });}
    }
    private void showReleaseNotes(){
        if(release==null||disposed||!host.allowed())return;
        TextView body=new TextView(activity);body.setText(release.notes);body.setTextSize(14);body.setTypeface(android.graphics.Typeface.create("sans-serif",0));body.setTextIsSelectable(true);body.setLineSpacing(4,1);
        int p=(int)(20*activity.getResources().getDisplayMetrics().density);body.setPadding(p,p,p,p);ScrollView scroll=new ScrollView(activity);scroll.addView(body);
        notesDialog=new AlertDialog.Builder(activity).setTitle("What’s new · "+release.name).setView(scroll).setPositiveButton("Back",null).create();
        notesDialog.setOnDismissListener(d->notesDialog=null);notesDialog.show();
    }
    public void checkAutomatically(){
        if(disposed||working||!host.allowed())return;
        long now=System.currentTimeMillis(),last=prefs.getLong("lastAttempt",0);
        if(last!=0&&now>=last&&now-last<CHECK_INTERVAL)return;
        main.postDelayed(()->{if(host.allowed()&&!working)check(false);},2000);
    }
    private void check(boolean manual){
        if(disposed||working||!host.allowed())return;
        if(!manual){long last=prefs.getLong("lastAttempt",0),now=System.currentTimeMillis();if(last!=0&&now>=last&&now-last<CHECK_INTERVAL)return;}
        working=true;cancelled=false;percent=-1;status="Checking for updates…";prefs.edit().putLong("lastAttempt",System.currentTimeMillis()).apply();redraw();
        worker.execute(()->{
            try{
                Release candidate=parseSigned(fetchManifest(),UpdateConfig.PUBLIC_KEY);
                if(cancelled||disposed)throw new InterruptedIOException();
                if(candidate.code>installedCode&&Build.VERSION.SDK_INT>=candidate.minSdk){
                    boolean same=release!=null&&release.sha256.equals(candidate.sha256);
                    if(!same)apk.delete();saveManifest(candidate);
                }else{apk.delete();manifest.delete();}
                post(()->{
                    prefs.edit().putLong("lastSuccess",System.currentTimeMillis()).apply();working=false;
                    if(candidate.code<=installedCode){release=null;status="You have the latest published version.";}
                    else if(Build.VERSION.SDK_INT<candidate.minSdk){release=null;status="A newer version requires Android API "+candidate.minSdk+" or later. Your current app remains usable.";}
                    else{release=candidate;status="Update available. Downloads start only when you choose.";}
                    redraw();
                    if(!manual&&release!=null&&host.allowed()&&(dialog==null||!dialog.isShowing())&&prefs.getInt("lastNotified",0)!=release.code){
                        prefs.edit().putInt("lastNotified",release.code).apply();
                        dialog=new AlertDialog.Builder(activity).setTitle("Update available · "+release.name).setMessage("An update to Open Repertory is ready. Your records will stay on this phone.")
                            .setPositiveButton("View update",(d,w)->{dialog=null;show();}).setNegativeButton("Later",(d,w)->dialog=null).create();dialog.show();
                    }
                });
            }catch(Exception e){post(()->{working=false;status=cancelled?"Check cancelled.":e instanceof FileNotFoundException?"No published update is available yet. Try again later.":e instanceof SecurityException?"Update verification failed. Nothing was installed.":"Could not check for updates. Check your connection and retry. Your current app remains usable.";redraw();});}
            finally{HttpURLConnection c=connection;if(c!=null)c.disconnect();connection=null;}
        });
    }
    private void download(){
        if(disposed||working||!host.allowed()||release==null)return;
        final Release selected=release;working=true;cancelled=false;percent=0;status="Downloading update…";redraw();
        worker.execute(()->{
            try{
                if(directory.getUsableSpace()<selected.size+16L*1024*1024)throw new IOException("Not enough storage");
                HttpURLConnection c=open(new URL(selected.url));String lengthHeader=c.getHeaderField("Content-Length");long length=lengthHeader==null?-1:Long.parseLong(lengthHeader);
                if(length>0&&length!=selected.size)throw new SecurityException("Unexpected download size");
                try(InputStream in=c.getInputStream()){
                    transfer(in,partial,selected.size,new TransferEvents(){
                        public boolean cancelled(){return cancelled||disposed;}
                        public void progress(int value){post(()->{percent=value;redraw();});}
                    });
                }finally{c.disconnect();connection=null;}
                if(cancelled||disposed)throw new InterruptedIOException();
                validateApk(activity,partial,selected);
                if(cancelled||disposed)throw new InterruptedIOException();
                if(!partial.renameTo(apk))throw new IOException("Cannot retain download");
                post(()->{working=false;percent=100;status="Download verified. Tap Install update. Android will ask you to confirm.";redraw();});
            }catch(Exception e){partial.delete();post(()->{working=false;status=cancelled?"Download cancelled. Tap Download update to start again.":e instanceof SecurityException?"Download verification failed. The file was rejected.":"Download failed. Check your connection and free storage, then retry.";redraw();});}
            finally{HttpURLConnection c=connection;if(c!=null)c.disconnect();connection=null;}
        });
    }
    private void install(){
        if(disposed||working||!host.allowed()||release==null)return;
        final Release selected=release;working=true;status="Verifying download before installation…";percent=-1;redraw();
        worker.execute(()->{
            try{Release pinned=parseSigned(read(manifest),UpdateConfig.PUBLIC_KEY);if(!pinned.sha256.equals(selected.sha256))throw new SecurityException();validateApk(activity,apk,pinned);
                post(()->{working=false;if(!host.allowed())return;
                    if(Build.VERSION.SDK_INT>=26&&!activity.getPackageManager().canRequestPackageInstalls()){
                        status="Allow installation from Open Repertory in Android settings, then return here and tap Install update again.";redraw();
                        try{activity.startActivity(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,Uri.parse("package:"+activity.getPackageName())));}catch(Exception e){status="Open Android Settings → Apps → Special access → Install unknown apps, then allow Open Repertory.";redraw();}return;
                    }
                    host.beforeInstall(()->{
                        if(!host.allowed())return;
                        try{
                            Uri uri=Uri.parse("content://"+activity.getPackageName()+".updates/verified.apk");
                            Intent intent=new Intent(Intent.ACTION_VIEW).setDataAndType(uri,"application/vnd.android.package-archive").addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.setClipData(ClipData.newRawUri("Verified app update",uri));
                            activity.startActivity(intent);status="Finish installation in Android. If cancelled, tap Install update to retry.";redraw();
                        }catch(Exception e){status="Android could not open the installer. You can retry without deleting your records.";redraw();}
                    });
                });
            }catch(Exception e){apk.delete();post(()->{working=false;status="The saved update could not be verified. Download it again.";redraw();});}
        });
    }
    private void cancel(){cancelled=true;HttpURLConnection c=connection;if(c!=null)c.disconnect();status="Cancelling…";redraw();}
    public void pause(){if(notesDialog!=null){notesDialog.dismiss();notesDialog=null;}if(dialog!=null){dialog.dismiss();dialog=null;}text=null;}
    public void dispose(){disposed=true;cancel();pause();main.removeCallbacksAndMessages(null);worker.shutdownNow();}
}
