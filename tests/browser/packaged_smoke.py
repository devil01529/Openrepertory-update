"""Exercise packaged web assets. Does not emulate Android authentication or Gemini."""
import argparse,json
from pathlib import Path
from playwright.sync_api import sync_playwright
p=argparse.ArgumentParser();p.add_argument('--url',default='http://127.0.0.1:8765');p.add_argument('--output',required=True);a=p.parse_args()
out={'scope':'Packaged web assets in Chromium; native authentication, encryption, installer and live Gemini are not tested','books':{},'errors':[]}
with sync_playwright() as pw:
 b=pw.chromium.launch(executable_path='/usr/bin/chromium',headless=True,args=['--no-sandbox'])
 page=b.new_page(viewport={'width':412,'height':915});page.on('pageerror',lambda e:out['errors'].append(str(e)))
 page.goto(a.url);page.wait_for_function('window.OR && OR.workspaceReady === true',timeout=60000)
 for book in ['kent1897','kent','bbcr','tpb1846','boericke1906','bbcr1905_oorep','tpb1891','guernsey1892']:
  result=page.evaluate('''async id=>{
    await OR.setRepertory(id);OR.showPage('repertory');
    let rep=OR.state.repertory;
    function find(rows){for(let r of rows){if(r.remedies?.length)return r;let s=find(r.sub||[]);if(s)return s;}}
    let chapter=rep.chapters.find(c=>find(c.rubrics)),row=find(chapter.rubrics);
    OR.toggleRubric(row.id,chapter.name,row.text,row.remedies,1,'manual');
    let selected=OR.activeCase().analysis.some(r=>r.sourceId===id&&r.uid===row.id);
    let ranked=OR.rankCase(OR.activeCase());
    OR.showPage('analysis');
    return {loaded:OR.state.repertoryId===id,chapters:rep.chapters.length,selected,ranking_nonempty:ranked.length>0};
  }''',book)
  assert all(result[k] for k in ['loaded','selected','ranking_nonempty']),result
  out['books'][book]=result
 page.evaluate("OR.openMateriaRemedy('Acon.')")
 page.wait_for_timeout(1500)
 text=page.locator('#mmDetail').inner_text();out['materia_medica']={'profile_loaded':'Acon' in text,'text_characters':len(text)}
 assert out['materia_medica']['profile_loaded'] and len(text)>300
 page.evaluate("OR.setRepertory('kent1897')")
 page.wait_for_function("OR.state.repertoryId==='kent1897' && !OR.state.repertoryLoading")
 page.evaluate("OR.finder.open('fear of death', {run:true})")
 page.wait_for_function("document.querySelector('#aiGo').textContent !== 'Stop'",timeout=60000)
 text=page.locator('#aiPanel').inner_text();out['offline_finder']={'completed':True,'result_characters':len(text),'no_key':page.evaluate('OR.ai.gemini===null && OR.ai.sample===null')}
 assert len(text)>100
 page.reload();page.wait_for_function('window.OR && OR.workspaceReady === true',timeout=60000)
 out['case_reload']={'selected_count':page.evaluate('OR.activeCase().analysis.length')}
 assert out['case_reload']['selected_count']==8
 page.screenshot(path=str(Path(a.output).with_suffix('.png')),full_page=True)
 b.close()
Path(a.output).write_text(json.dumps(out,indent=2)+'\n')
assert not out['errors'],out['errors']
print(json.dumps(out,indent=2))
