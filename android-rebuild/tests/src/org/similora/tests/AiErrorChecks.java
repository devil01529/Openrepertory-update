package org.similora.tests;
import android.app.*;import android.os.*;import org.json.*;import java.lang.reflect.*;
public final class AiErrorChecks extends Instrumentation{
 @Override public void onCreate(Bundle b){super.onCreate(b);start();}
 @Override public void onStart(){Bundle r=new Bundle();try{
  Class<?> c=Class.forName("org.similora.app.UserAi",true,getTargetContext().getClassLoader());Method classify=c.getMethod("error",int.class,String.class),detail=c.getMethod("detail",String.class),retry=c.getMethod("tryNext",int.class);
  for(String code:new String[]{"insufficient_quota","billing_hard_limit_reached","usage_limit_reached"}){String raw=new JSONObject().put("error",new JSONObject().put("code",code).put("message","DO_NOT_DISPLAY_PRIVATE_PROVIDER_MESSAGE")).toString();if(!classify.invoke(null,429,raw).equals("quota_exhausted"))throw new AssertionError(code);}
  String type=new JSONObject().put("error",new JSONObject().put("type","insufficient_quota")).toString();if(!classify.invoke(null,429,type).equals("quota_exhausted"))throw new AssertionError("type quota");
  for(String raw:new String[]{"{}","not JSON","{\"error\":{\"code\":\"rate_limit_exceeded\"}}"})if(!classify.invoke(null,429,raw).equals("rate_limited"))throw new AssertionError("temporary limit");
  if(!classify.invoke(null,402,"{}").equals("billing_required")||!classify.invoke(null,401,"{}").equals("invalid_key")||!classify.invoke(null,500,type).equals("provider_error"))throw new AssertionError("status mapping");
  String msg=detail.invoke(null,"quota_exhausted").toString();if(!msg.contains("ChatGPT subscriptions")||msg.contains("DO_NOT_DISPLAY_PRIVATE"))throw new AssertionError("safe actionable message");if((Boolean)retry.invoke(null,429))throw new AssertionError("Must not retry quota/rate errors");
  r.putString("result","PASS quota versus temporary rate limits, billing/auth/server errors, malformed body, safe messages and no 429 model retry");finish(Activity.RESULT_OK,r);
 }catch(Throwable e){r.putString("result","FAIL "+e);finish(Activity.RESULT_CANCELED,r);}}
}
