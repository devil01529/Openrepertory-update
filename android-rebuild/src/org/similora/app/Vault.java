package org.similora.app;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.AtomicFile;
import org.json.JSONObject;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.KeyStore;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/** One atomic AES-GCM encrypted map per server-issued account ID. Never plaintext fallback. */
final class Vault {
 private final AtomicFile file; private final SecretKey key; private JSONObject values; private boolean healthy=true;
 Vault(Context context,String uid)throws Exception {
  if(!uid.matches("[a-f0-9]{32}"))throw new SecurityException("Invalid account identity");
  file=new AtomicFile(new File(context.getNoBackupFilesDir(),"records-"+uid+".vault"));
  KeyStore ks=KeyStore.getInstance("AndroidKeyStore");ks.load(null);String alias="similora.vault."+uid;
  if(!ks.containsAlias(alias)){
   if(file.getBaseFile().exists())healthy=false;
   KeyGenerator generator=KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
   generator.init(new KeyGenParameterSpec.Builder(alias,KeyProperties.PURPOSE_ENCRYPT|KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_GCM).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE).setKeySize(256).build());generator.generateKey();
  }
  key=(SecretKey)ks.getKey(alias,null);
  if(!file.getBaseFile().exists()&&!new File(file.getBaseFile()+".bak").exists()){values=new JSONObject();return;}
  try {
  if(file.getBaseFile().length()>64L*1024*1024)throw new IOException("Records exceed safe size");
  byte[] all=file.readFully();if(all.length<28)throw new IOException("Damaged records");
  Cipher cipher=Cipher.getInstance("AES/GCM/NoPadding");cipher.init(Cipher.DECRYPT_MODE,key,new GCMParameterSpec(128,all,0,12));
  values=new JSONObject(new String(cipher.doFinal(all,12,all.length-12),StandardCharsets.UTF_8));
  }catch(Exception damaged){healthy=false;values=new JSONObject();}
 }
 synchronized boolean healthy(){return healthy;}
 synchronized String read(String key){if(!healthy)throw new IllegalStateException("Recovery required");return values.isNull(key)?null:values.optString(key,null);}
 synchronized void batch(String json)throws Exception {
  if(!healthy)throw new IOException("Recovery required");
  if(json.length()>32*1024*1024)throw new IOException("Record update too large");
  JSONObject updates=new JSONObject(json),next=new JSONObject(values.toString());
  for(Iterator<String> it=updates.keys();it.hasNext();){String k=it.next();if(k.length()>200)throw new IOException("Invalid record key");if(updates.isNull(k))next.remove(k);else{Object v=updates.get(k);if(!(v instanceof String))throw new IOException("Record values must be strings");next.put(k,v);}}
  byte[] plain=next.toString().getBytes(StandardCharsets.UTF_8);if(plain.length>48*1024*1024)throw new IOException("Records too large");
  Cipher cipher=Cipher.getInstance("AES/GCM/NoPadding");cipher.init(Cipher.ENCRYPT_MODE,key);byte[] encrypted=cipher.doFinal(plain);
  FileOutputStream output=null;try{output=file.startWrite();output.write(cipher.getIV());output.write(encrypted);file.finishWrite(output);values=next;}catch(Exception error){if(output!=null)file.failWrite(output);throw error;}
 }
 synchronized void restore(String json)throws Exception {
  JSONObject updates=new JSONObject(json);
  for(Iterator<String> it=updates.keys();it.hasNext();){String key=it.next();if(!key.equals("openrepertory_patients")&&!key.equals("openrepertory_cases")&&!key.equals("openrepertory_mmnotes"))throw new IOException("Invalid recovery data");new JSONObject(updates.getString(key));}
  if(!updates.has("openrepertory_cases")||!updates.has("openrepertory_patients"))throw new IOException("Incomplete backup");
  if(file.getBaseFile().exists()){
   File backup=new File(file.getBaseFile()+".before-restore-"+System.currentTimeMillis());
   try(InputStream in=file.openRead();FileOutputStream out=new FileOutputStream(backup)){byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);out.getFD().sync();}
  }
  JSONObject old=values;boolean wasHealthy=healthy;values=new JSONObject();healthy=true;
  try{batch(json);}catch(Exception error){values=old;healthy=wasHealthy;throw error;}
 }
 synchronized void patient(String record,String active)throws Exception {
  JSONObject p=new JSONObject(record);String id=p.getString("id");if(id.isEmpty()||id.length()>200)throw new IOException("Invalid patient identity");
  String old=read("openrepertory_patients");JSONObject data=old==null?new JSONObject("{\"patients\":{},\"activeId\":null}"):new JSONObject(old);
  data.getJSONObject("patients").put(id,p);data.put("activeId",active);batch(new JSONObject().put("openrepertory_patients",data.toString()).toString());
 }
}
