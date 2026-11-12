package org.similora.tests;
import android.app.*;import android.content.*;import android.os.*;import android.view.*;import android.widget.*;import java.lang.reflect.*;
/** Tests local artwork without granting any clinical access. */
public final class StartupChecks extends Instrumentation{
 @Override public void onCreate(Bundle args){super.onCreate(args);start();}
 Field field(Class<?> c,String n)throws Exception{Field f=c.getDeclaredField(n);f.setAccessible(true);return f;}
 @Override public void onStart(){Bundle result=new Bundle();Activity app=null;Object[] scene={null};try{
  app=startActivitySync(new Intent(Intent.ACTION_MAIN).setClassName(getTargetContext(),"org.similora.app.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));final Activity a=app;
  // Let the asynchronous account check settle before mounting the independent scene.
  Thread.sleep(16000);
  Class<?> cls=Class.forName("org.similora.app.StartupScene",true,getTargetContext().getClassLoader());Constructor<?> ctor=cls.getDeclaredConstructor(Context.class);ctor.setAccessible(true);Method active=cls.getDeclaredMethod("active",boolean.class);active.setAccessible(true);
  runOnMainSync(()->{try{scene[0]=ctor.newInstance(a);a.setContentView((View)scene[0]);active.invoke(scene[0],true);}catch(Exception e){throw new RuntimeException(e);}});
  VideoView video=(VideoView)field(cls,"video").get(scene[0]);boolean playing=false;
  for(int i=0;i<60;i++){Thread.sleep(1000);if(video.getCurrentPosition()>200){playing=true;break;}}
  if(!playing)throw new AssertionError("Local startup video did not play");
  runOnMainSync(()->{try{active.invoke(scene[0],false);}catch(Exception e){throw new RuntimeException(e);}});Thread.sleep(500);int before=video.getCurrentPosition();Thread.sleep(1200);if(Math.abs(video.getCurrentPosition()-before)>100)throw new AssertionError("Video kept running after pause");
  if(((View)field(cls,"poster").get(scene[0])).getVisibility()!=View.VISIBLE)throw new AssertionError("Paused fallback hidden");
  runOnMainSync(()->{try{active.invoke(scene[0],true);}catch(Exception e){throw new RuntimeException(e);}});Thread.sleep(1600);if(video.getCurrentPosition()==before)throw new AssertionError("Video failed to resume");
  Thread.sleep(12000);if(!field(cls,"finished").getBoolean(scene[0]))throw new AssertionError("Intro did not finish");if(((View)field(cls,"controls").get(scene[0])).getVisibility()!=View.VISIBLE)throw new AssertionError("Loading controls did not follow intro");
  Method dispose=cls.getDeclaredMethod("dispose");dispose.setAccessible(true);runOnMainSync(()->{try{dispose.invoke(scene[0]);}catch(Exception e){throw new RuntimeException(e);}});
  result.putString("result","PASS bundled startup video playback, pause, static fallback, resume, complete one-shot playback, loading controls and disposal");finish(Activity.RESULT_OK,result);
 }catch(Throwable e){result.putString("result","FAIL "+e);finish(Activity.RESULT_CANCELED,result);}finally{if(app!=null){Activity a=app;runOnMainSync(a::finish);}}}
}
