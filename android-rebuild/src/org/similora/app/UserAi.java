package org.similora.app;
import org.json.*;
/** Fixed HTTPS providers only. No custom hosts, tools, file uploads or database writes. */
public final class UserAi {
 public static JSONObject validate(String raw)throws Exception{
  JSONObject c=new JSONObject(raw);String p=c.getString("provider"),m=c.optString("model",""),k=c.getString("key").trim();
  if(m.isEmpty())m=defaultModel(p);
  if(!p.equals("gemini")&&!p.equals("openai"))throw new IllegalArgumentException("Unsupported provider");
  if(!m.matches("[A-Za-z0-9._:-]{1,80}")||k.length()<10||k.length()>512||!k.matches("[A-Za-z0-9_\\-]+"))throw new IllegalArgumentException("Invalid model or key");
  return new JSONObject().put("provider",p).put("model",m).put("key",k);
 }
 public static String defaultModel(String provider){return provider.equals("openai")?"gpt-4.1-mini":"gemini-2.5-flash";}
 public static String[] models(JSONObject c)throws Exception{java.util.LinkedHashSet<String> names=new java.util.LinkedHashSet<>();names.add(c.getString("model"));if(c.getString("provider").equals("openai")){names.add("gpt-4.1-mini");names.add("gpt-4o-mini");}else{names.add("gemini-2.5-flash");names.add("gemini-2.5-flash-lite");}return names.toArray(new String[0]);}
 public static boolean tryNext(int status){return status==404;}
 public static String endpoint(JSONObject c)throws Exception{return c.getString("provider").equals("openai")?"https://api.openai.com/v1/chat/completions":"https://generativelanguage.googleapis.com/v1beta/models/"+c.getString("model")+":generateContent";}
 public static JSONObject body(JSONObject c,String prompt)throws Exception{
  if(prompt.length()>20000)throw new IllegalArgumentException("Text too long");
  if(c.getString("provider").equals("openai"))return new JSONObject().put("model",c.getString("model")).put("messages",new JSONArray().put(new JSONObject().put("role","system").put("content","Return only the requested JSON. Interpret symptoms for local rubric lookup; do not prescribe or invent database entries.")).put(new JSONObject().put("role","user").put("content",prompt))).put("max_completion_tokens",2048).put("response_format",new JSONObject().put("type","json_object"));
  return new JSONObject().put("contents",new JSONArray().put(new JSONObject().put("parts",new JSONArray().put(new JSONObject().put("text",prompt))))).put("generationConfig",new JSONObject().put("responseMimeType","application/json").put("maxOutputTokens",2048));
 }
 public static String parse(JSONObject c,String raw)throws Exception{
  JSONObject response=new JSONObject(raw);String text;
  if(c.getString("provider").equals("openai"))text=response.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
  else{JSONArray parts=response.getJSONArray("candidates").getJSONObject(0).getJSONObject("content").getJSONArray("parts");StringBuilder b=new StringBuilder();for(int i=0;i<parts.length();i++)b.append(parts.getJSONObject(i).optString("text"));text=b.toString();}
  if(text.length()>65536)throw new IllegalArgumentException("AI response too large");new JSONObject(text);return text;
 }
 public static String error(int status,String raw){
  if(status==402)return "billing_required";
  if(status==429)try{JSONObject e=new JSONObject(raw).optJSONObject("error");if(e!=null){String c=e.optString("code"),t=e.optString("type");for(String v:new String[]{c,t})if(v.equals("insufficient_quota")||v.equals("billing_hard_limit_reached")||v.equals("usage_limit_reached"))return "quota_exhausted";}}catch(Exception ignored){}
  return error(status);
 }
 public static String detail(String code){
  if(code.equals("quota_exhausted")||code.equals("billing_required"))return "Your provider API account has no usable quota or needs API billing. For OpenAI, check platform.openai.com API billing, credit balance and project limits. ChatGPT subscriptions do not include API credit. Repeated retries or replacing a valid key will not add quota. Offline search remains available.";
  if(code.equals("rate_limited"))return "The provider temporarily limited requests. Wait before trying again and check your API rate limits. Offline search remains available.";
  if(code.equals("invalid_key"))return "The provider rejected this API key or its permissions. Check the key and project access. Offline search remains available.";
  return "AI request could not complete. Check the provider key and connection. The app selects the model automatically. Offline search remains available.";
 }
 public static String error(int status){return status==401||status==403?"invalid_key":status==429?"rate_limited":status==404?"model_unavailable":"provider_error";}
}
