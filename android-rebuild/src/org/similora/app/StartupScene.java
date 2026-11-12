package org.similora.app;
import android.app.Activity;
import android.animation.*;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

/** Transparent emblem on the screen itself: no movie surface, panel or particles. */
final class StartupScene extends FrameLayout {
 static int launchTask=-1;static boolean launchFinished;
 final LinearLayout controls,artwork;final ImageView logo;
 AnimatorSet animation;boolean finished,disposed;Runnable afterIntro;
 StartupScene(Context c){
  super(c);int task=((Activity)c).getTaskId();if(task!=launchTask){launchTask=task;launchFinished=false;}
  setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{Color.rgb(13,35,45),Color.rgb(27,62,67),Color.rgb(13,35,45)}));
  artwork=new LinearLayout(c);artwork.setOrientation(LinearLayout.VERTICAL);artwork.setGravity(Gravity.CENTER);addView(artwork,new LayoutParams(-1,-1));
  logo=new ImageView(c);logo.setImageResource(getResources().getIdentifier("similora","drawable",c.getPackageName()));logo.setScaleType(ImageView.ScaleType.FIT_CENTER);logo.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
  android.util.DisplayMetrics dm=getResources().getDisplayMetrics();int size=(int)Math.min(240*dm.density,Math.min(dm.widthPixels*.72f,dm.heightPixels*.43f));artwork.addView(logo,new LinearLayout.LayoutParams(size,size));
  TextView name=new TextView(c);name.setText("Similora");name.setTextSize(38);name.setTypeface(android.graphics.Typeface.create("serif",0));name.setTextColor(Color.rgb(231,245,242));name.setGravity(Gravity.CENTER);artwork.addView(name);
  TextView caption=new TextView(c);caption.setText("Symptom to Totality");caption.setTextSize(15);caption.setTextColor(Color.rgb(174,207,204));caption.setPadding(0,12,0,0);caption.setGravity(Gravity.CENTER);artwork.addView(caption);
  controls=new LinearLayout(c);controls.setOrientation(LinearLayout.VERTICAL);controls.setGravity(Gravity.CENTER);controls.setPadding(24,12,24,24);controls.setVisibility(View.GONE);addView(controls,new LayoutParams(-1,-2,Gravity.CENTER));
  ObjectAnimator fade=ObjectAnimator.ofFloat(logo,"alpha",0,1);fade.setDuration(1600);
  ObjectAnimator x=ObjectAnimator.ofFloat(logo,"scaleX",.86f,1);x.setDuration(3200);ObjectAnimator y=ObjectAnimator.ofFloat(logo,"scaleY",.86f,1);y.setDuration(3200);
  ObjectAnimator title=ObjectAnimator.ofFloat(name,"alpha",0,1);title.setStartDelay(900);title.setDuration(1000);
  ObjectAnimator sub=ObjectAnimator.ofFloat(caption,"alpha",0,1);sub.setStartDelay(1500);sub.setDuration(1000);
  ValueAnimator hold=ValueAnimator.ofFloat(0,1);hold.setDuration(10000);
  animation=new AnimatorSet();animation.playTogether(fade,x,y,title,sub,hold);animation.addListener(new AnimatorListenerAdapter(){public void onAnimationEnd(Animator a){if(!disposed)complete();}});
  if(launchFinished||reduced())complete();
 }
 boolean reduced(){return Settings.Global.getFloat(getContext().getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE,1f)==0f;}
 void complete(){if(finished)return;finished=true;launchFinished=true;artwork.setVisibility(View.GONE);controls.setVisibility(View.VISIBLE);if(afterIntro!=null){Runnable action=afterIntro;afterIntro=null;action.run();}}
 void whenFinished(Runnable action){if(finished)action.run();else afterIntro=action;}
 void active(boolean value){if(finished||disposed)return;if(value){if(!animation.isStarted())animation.start();else if(animation.isPaused())animation.resume();}else if(animation.isStarted())animation.pause();}
 void dispose(){disposed=true;afterIntro=null;animation.cancel();}
}
