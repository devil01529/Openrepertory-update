"""Apply reviewed BYOK UI changes without modifying clinical data."""
from pathlib import Path
import sys,shutil
root=Path(__file__).resolve().parent;web=Path(sys.argv[1]);p=web/'js/finder.js';s=p.read_text()
if 'id="yourAiSetup"' not in s:
 s=s.replace('nativeAi.hasGeminiKey','nativeAi.hasAiConfig').replace('"geminiAsync"','"aiAsync"').replace('OR.ai.provider = "Gemini";', 'OR.ai.provider = nativeAi.aiProviderName();')
 s=s.replace('Only the text below will be sent to Google Gemini.', 'The reviewed text below, current book/chapter names and search instructions will be sent to ${esc(OR.ai.provider)}.')
 s=s.replace('label: "Send to Gemini"','label: "Send to " + OR.ai.provider')
 s=s.replace('${run.note ?', '${run.loading ? `<button type="button" id="yourAiStop" class="btn">Stop search</button>` : ""}${run.note ?')
 s=s.replace('    $("aiClose").addEventListener("click", closePanel);','    $("aiClose").addEventListener("click", closePanel); if($("yourAiStop")) $("yourAiStop").addEventListener("click", stop);')
 s=s.replace('const list = data && data.concepts || [];','const list = Array.isArray(data && data.concepts) ? data.concepts.slice(0, 10).filter(c => c && typeof c === "object") : [];')
 s=s.replace('.map(stemTok).filter', '.slice(0, 4).map(stemTok).filter')
 s=s.replace('<h3>Describe it the way the patient did</h3>','<h3>Describe it the way the patient did</h3><button type="button" id="yourAiSetup" class="btn">Your AI settings</button>')
 s=s.replace('    if (e.target.closest("#aiIdleClose"))', '    if (e.target.closest("#yourAiSetup")) return OR.openOnlineAi();\n    if (e.target.closest("#aiIdleClose"))')
 s=s.replace('Gemini is not connected','Your AI is not connected').replace('Gemini rejected this API key','The provider rejected this API key').replace('This Gemini model','This AI model').replace('Gemini is rate-limited','The AI provider is rate-limited').replace('Gemini network request failed','The AI request failed')
p.write_text(s);shutil.copyfile(root/'your-ai.js',web/'js/your-ai.js')
p=web/'index.html';s=p.read_text().replace('<b>Online AI</b>','<b>Your AI</b>').replace('Connect your Gemini API key','Connect your own AI key');s=s.replace('<script src="js/start.js"></script>','<script src="js/your-ai.js"></script>\n<script src="js/start.js"></script>') if 'js/your-ai.js' not in s else s;p.write_text(s)
