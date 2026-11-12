from pathlib import Path
from http.server import ThreadingHTTPServer,SimpleHTTPRequestHandler
from threading import Thread
from functools import partial
from playwright.sync_api import sync_playwright
import argparse
args=argparse.ArgumentParser();args.add_argument('--assets',type=Path,required=True);root=args.parse_args().assets.resolve()
class Quiet(SimpleHTTPRequestHandler):
 def log_message(self,*a):pass
server=ThreadingHTTPServer(('127.0.0.1',0),partial(Quiet,directory=str(root)));Thread(target=server.serve_forever,daemon=True).start()
with sync_playwright() as p:
 b=p.chromium.launch(executable_path='/usr/bin/chromium',args=['--no-sandbox']);page=b.new_page(viewport={'width':412,'height':915});errors=[];page.on('pageerror',lambda e:errors.append(str(e)));page.goto(f'http://127.0.0.1:{server.server_port}');page.wait_for_function('()=>window.OR?.workspaceReady===true',timeout=60000)
 page.evaluate('''()=>{let saved=null;window.openrepertoryNative={aiConfig:()=>JSON.stringify(saved?{provider:saved.provider,model:saved.model,connected:true}:{}),saveAiConfig:raw=>{saved=JSON.parse(raw);return true},clearAiConfig:()=>{saved=null;return true}};window.calls=[];window.fixture='ok';window.ORNativeRequest=(method,args,signal)=>{calls.push({method,prompt:args[0],test:args[1]});if(fixture==='pending')return new Promise((resolve,reject)=>signal.addEventListener('abort',()=>{const e=Error('Stopped');e.code='cancelled';reject(e)}));return Promise.resolve(JSON.stringify(args[1]?{ok:true}:fixture==='error'?{error:'rate_limited',detail:'fixture quota'}:{concepts:[{label:'Fear of death',meaning:'Fear expressed by the patient',chapter:'Mind',importance:1,queries:[['fear','death']]}]}))}}''')
 page.add_script_tag(url=page.url+'js/your-ai.js?fixture=1');page.evaluate('OR.hooks.start[OR.hooks.start.length-1]()');page.evaluate('OR.openOnlineAi()');page.locator('#yourAiProvider').select_option('openai');assert page.locator('#yourAiModel').input_value()=='gpt-4.1-mini';page.locator('#yourAiKey').fill('synthetic_ai_key_12345');page.get_by_role('button',name='Save connection',exact=True).click();assert page.evaluate('OR.ai.provider')=='OpenAI';assert not page.evaluate("JSON.stringify(localStorage).includes('synthetic_ai_key_12345')")
 page.evaluate("OR.finder.open('I fear death', {run:true})");page.get_by_role('button',name='Send to OpenAI',exact=True).wait_for();assert page.evaluate('calls.length')==0
 page.get_by_role('button',name='Send to OpenAI',exact=True).click();page.wait_for_function('()=>document.querySelector("#aiGo").textContent!=="Stop"',timeout=60000);assert page.evaluate('calls.length')==1;assert 'FEAR' in page.locator('#aiPanel').inner_text().upper();assert page.evaluate('OR.activeCase().analysis.length')==0
 page.evaluate("fixture='error';OR.finder.open('fear of death', {run:true})");page.get_by_role('button',name='Send to OpenAI',exact=True).click();page.wait_for_function('()=>document.querySelector("#aiGo").textContent!=="Stop"',timeout=60000);assert 'continued locally' in page.locator('#aiPanel').inner_text()
 page.evaluate("fixture='pending';OR.finder.open('fear of death', {run:true})");page.get_by_role('button',name='Send to OpenAI',exact=True).click();page.wait_for_timeout(100);page.locator('#yourAiStop').click();page.wait_for_function('()=>document.querySelector("#aiGo").textContent!=="Stop"')
 page.evaluate('OR.openOnlineAi()');page.get_by_role('button',name='Disconnect',exact=True).click();assert page.evaluate('OR.ai.gemini===null');assert not errors,errors
 b.close()
server.shutdown();print('PASS own-provider setup, reviewed-text gate, local rubric matching, no automatic case additions, quota fallback, cancellation, disconnect and no key in browser storage (mock provider; not live API)')
