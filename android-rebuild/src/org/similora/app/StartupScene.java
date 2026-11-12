package org.similora.app;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

/** Local, silent launch artwork. Never controls authentication or workspace readiness. */
final class StartupScene extends FrameLayout {
 final LinearLayout controls;
 final VideoView video;
 final ImageView poster;
 boolean active,prepared,failed,finished; Runnable afterIntro;
 StartupScene(Context context){
  super(context);setBackgroundColor(Color.rgb(39,61,65));
  FrameLayout artwork=new FrameLayout(context);
  LayoutParams art=new LayoutParams(-1,-1);addView(artwork,art);
  video=new VideoView(context);artwork.addView(video,new LayoutParams(-1,-1,Gravity.CENTER));
  poster=new ImageView(context);poster.setScaleType(ImageView.ScaleType.FIT_CENTER);poster.setImageResource(getResources().getIdentifier("similora_startup_poster","drawable",context.getPackageName()));poster.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);artwork.addView(poster,new LayoutParams(-1,-1));
  controls=new LinearLayout(context);controls.setOrientation(LinearLayout.VERTICAL);controls.setGravity(Gravity.CENTER);controls.setPadding(24,12,24,24);controls.setBackgroundColor(Color.rgb(39,61,65));controls.setVisibility(View.GONE);addView(controls,new LayoutParams(-1,-2,Gravity.BOTTOM));
  video.setOnPreparedListener(player->{prepared=true;player.setVolume(0,0);player.setLooping(false);if(active&&!reduced())video.start();else if(reduced())complete();});
  video.setOnInfoListener((player,what,extra)->{if(what==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START&&active&&!reduced())poster.setVisibility(View.GONE);return false;});
  video.setOnCompletionListener(player->complete());
  video.setOnErrorListener((player,what,extra)->{failed=true;poster.setVisibility(View.VISIBLE);complete();return true;});
  video.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/raw/similora_startup"));
 }
 boolean reduced(){return Settings.Global.getFloat(getContext().getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE,1f)==0f;}
 void complete(){if(finished)return;finished=true;controls.setVisibility(View.VISIBLE);video.setVisibility(View.GONE);poster.setVisibility(View.GONE);if(afterIntro!=null){Runnable action=afterIntro;afterIntro=null;action.run();}}
 void whenFinished(Runnable action){if(finished)action.run();else afterIntro=action;}
 void active(boolean value){active=value;if(finished)return;if(value&&prepared&&!failed&&!reduced()){video.start();}else{if(prepared)video.pause();poster.setVisibility(View.VISIBLE);}}
 void dispose(){active=false;video.stopPlayback();}
}
