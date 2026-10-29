package com.openrepertory.app;

import android.app.*;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import org.json.JSONObject;

/** Lightweight native material: no screenshot capture, continuously animated blur or web bridge. */
final class UpdateGlass {
    private final Activity activity;
    private static Typeface regular;
    private static synchronized Typeface font(Activity a,boolean bold){
        if(regular==null)regular=Typeface.createFromAsset(a.getAssets(),"fonts/NotoSans-Regular.ttf");
        return bold?Typeface.create(regular,Typeface.BOLD):regular;
    }
    final boolean dark,glass;
    final int ink,muted,accent;
    UpdateGlass(Activity a,JSONObject preferences){
        activity=a;String theme=preferences.optString("theme","auto");
        dark="dark".equals(theme)||(!"light".equals(theme)&&(a.getResources().getConfiguration().uiMode&0x30)==0x20);
        glass=preferences.optBoolean("glassEffects",true);
        ink=dark?0xffeff5ff:0xff172c44;muted=dark?0xffbbcbe0:0xff52657a;accent=dark?0xffa8d1ff:0xff165eb7;
    }
    int dp(int n){return Math.round(n*activity.getResources().getDisplayMetrics().density);}
    GradientDrawable surface(boolean primary){
        int top=primary?0xff126ae4:dark?0xff22354b:0xfff7fcff;
        int bottom=primary?0xff2153ba:dark?0xff182638:0xffe6f0fb;
        GradientDrawable d=new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{top,glass?bottom:top});
        d.setCornerRadius(dp(primary?18:24));d.setStroke(dp(1),primary?0xff4d8fe9:dark?0xff50647b:0xffbfcede);return d;
    }
    void label(TextView v,boolean title){v.setTypeface(font(activity,title));v.setTextColor(ink);}
    void button(Button b,boolean primary){
        b.setAllCaps(false);b.setTypeface(font(activity,true));b.setTextSize(14);
        b.setMinHeight(dp(48));b.setMinimumHeight(dp(48));b.setPadding(dp(16),dp(10),dp(16),dp(10));
        b.setBackgroundTintList(null);b.setStateListAnimator(null);
        b.setBackground(new RippleDrawable(ColorStateList.valueOf(dark?0x334fafff:0x221467d0),surface(primary),null));
        b.setTextColor(new ColorStateList(new int[][]{new int[]{-android.R.attr.state_enabled},new int[]{}},new int[]{dark?0xff8798ad:0xff69798b,primary?0xffffffff:accent}));
        if(b.getLayoutParams() instanceof LinearLayout.LayoutParams){LinearLayout.LayoutParams p=(LinearLayout.LayoutParams)b.getLayoutParams();p.topMargin=dp(8);b.setLayoutParams(p);}
    }
    void show(AlertDialog d){
        d.show();Window w=d.getWindow();if(w==null)return;
        w.setBackgroundDrawable(surface(false));w.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams p=w.getAttributes();p.dimAmount=.38f;p.windowAnimations=0;w.setAttributes(p);
        w.setLayout(Math.min(activity.getResources().getDisplayMetrics().widthPixels-dp(32),dp(520)),WindowManager.LayoutParams.WRAP_CONTENT);
        recolor(w.getDecorView());
        for(int id:new int[]{AlertDialog.BUTTON_POSITIVE,AlertDialog.BUTTON_NEGATIVE}){Button b=d.getButton(id);if(b!=null)button(b,false);}
    }
    private void recolor(View v){
        if(v instanceof TextView)label((TextView)v,false);
        if(v instanceof ViewGroup){ViewGroup g=(ViewGroup)v;for(int i=0;i<g.getChildCount();i++)recolor(g.getChildAt(i));}
    }
}
