package org.similora.tests;
import android.app.*;import android.content.*;import android.os.*;import android.view.*;import java.lang.reflect.*;
public final class StartupChecks extends Instrumentation{
 @Override public void onCreate(Bundle b){super.onCreate(b);start();}
 Field field(Class<?> c,String n)throws Exception{Field f=c.getDeclaredField(n);f.setAccessible(true);return f;}
 @Override public void onStart(){Bundle result=new Bundle();Activity app=null;try{
  app=startActivitySync(new Intent(Intent.ACTION_MAIN).setClassName(getTargetContext(),"org.similora.app.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));final Activity a=app;Thread.sleep(15000);
  Class<?> c=Class.forName("org.similora.app.StartupScene",true,getTargetContext().getClassLoader());field(c,"launchFinished").setBoolean(null,false);
  Constructor<?> ctor=c.getDeclaredConstructor(Context.class);ctor.setAccessible(true);Method active=c.getDeclaredMethod("active",boolean.class);active.setAccessible(true);Method finish=c.getDeclaredMethod("whenFinished",Runnable.class);finish.setAccessible(true);Object[] scene={null};boolean[] revealed={false};
  runOnMainSync(()->{try{scene[0]=ctor.newInstance(a);a.setContentView((View)scene[0]);finish.invoke(scene[0],(Runnable)()->revealed[0]=true);active.invoke(scene[0],true);}catch(Exception e){throw new RuntimeException(e);}});
  Thread.sleep(1500);if(revealed[0])throw new AssertionError("Early reveal");runOnMainSync(()->{try{active.invoke(scene[0],false);}catch(Exception e){throw new RuntimeException(e);}});Thread.sleep(1500);if(revealed[0])throw new AssertionError("Paused intro completed");runOnMainSync(()->{try{active.invoke(scene[0],true);}catch(Exception e){throw new RuntimeException(e);}});
  Thread.sleep(11500);if(!revealed[0]||!field(c,"finished").getBoolean(scene[0]))throw new AssertionError("Intro completion missing");
  runOnMainSync(()->{try{Object next=ctor.newInstance(a);if(!field(c,"finished").getBoolean(next))throw new AssertionError("Intro replayed in same task");field(c,"launchTask").setInt(null,-1);Object fresh=ctor.newInstance(a);if(field(c,"finished").getBoolean(fresh))throw new AssertionError("Fresh launch skipped intro");Method dispose=c.getDeclaredMethod("dispose");dispose.setAccessible(true);dispose.invoke(fresh);}catch(Exception e){throw new RuntimeException(e);}});
  result.putString("result","PASS logo-only full intro, readiness gate, pause/resume, no replay for same task and fresh-task reset");finish(Activity.RESULT_OK,result);
 }catch(Throwable e){result.putString("result","FAIL "+e);finish(Activity.RESULT_CANCELED,result);}finally{if(app!=null){Activity a=app;runOnMainSync(a::finish);}}}
}
