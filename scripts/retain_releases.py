"""Keep the five newest published app releases; delete older ones oldest first.

Dry-run by default. Drafts and unrelated tags are not release history.
The latest update endpoint is protected. Git history is never rewritten.
"""
import argparse,json,re,subprocess
from datetime import datetime
from pathlib import Path
TAG=re.compile(r'^v[0-9]+\.[0-9]+(?:\.[0-9]+)?$')

def plan(releases, latest, keep=5):
    published=[r for r in releases if not r.get('draft') and TAG.fullmatch(r['tag_name'])]
    if len({r['tag_name'] for r in published})!=len(published):
        raise ValueError('Duplicate release tags')
    # Missing or malformed publication dates must stop cleanup, not guess ordering.
    published.sort(key=lambda r:(datetime.fromisoformat(r['published_at'].replace('Z','+00:00')),r['id']))
    retained=published[-keep:]
    if latest not in {r['tag_name'] for r in retained}:
        raise ValueError('Latest update endpoint is not among the newest five; refusing deletion')
    return published[:-keep],retained

def prepared_to_prune(paths, active, retained, obsolete):
    oldest_version=min(tuple(map(int,r['tag_name'][1:].split('.'))) for r in retained)
    obsolete_tags={r['tag_name'] for r in obsolete}
    return [p for p in paths if p.is_dir() and TAG.fullmatch(p.name) and p.name not in active
            and (p.name in obsolete_tags or tuple(map(int,p.name[1:].split('.'))) < oldest_version)]

def api(repo,suffix):
    return json.loads(subprocess.check_output(['gh','api',f'repos/{repo}/{suffix}'],text=True))

def run(repo, apply=False, prune=False):
    releases=[];page=1
    while True:
        batch=api(repo,f'releases?per_page=100&page={page}');releases.extend(batch)
        if len(batch)<100:break
        page+=1
    latest=api(repo,'releases/latest')['tag_name']
    obsolete,retained=plan(releases,latest)
    print('Keep:',', '.join(r['tag_name'] for r in retained),flush=True)
    print('Delete oldest first:',', '.join(r['tag_name'] for r in obsolete) or '(none)',flush=True)
    for r in obsolete:
        if not apply:continue
        # Re-read before destructive work so changed publication/latest state fails closed.
        current=api(repo,f"releases/{r['id']}")
        if current['draft'] or current['tag_name']!=r['tag_name'] or api(repo,'releases/latest')['tag_name']!=latest:
            raise RuntimeError('Release state changed during cleanup')
        subprocess.run(['gh','release','delete',r['tag_name'],'--repo',repo,'--yes','--cleanup-tag'],check=True)
        print('Deleted:',r['tag_name'],flush=True)
    if apply and prune:
        root=Path(__file__).resolve().parents[1]
        # Recover obsolete files after interrupted cleanup, while protecting drafts and
        # staged future versions that do not have a GitHub release yet.
        active={r['tag_name'] for r in retained}|{r['tag_name'] for r in releases if r.get('draft')}
        candidates=prepared_to_prune((root/'prepared').iterdir(), active, retained, obsolete)
        for p in sorted(candidates,key=lambda p:tuple(map(int,p.name[1:].split('.')))):
            subprocess.run(['git','rm','-r','--ignore-unmatch','--',str(p.relative_to(root))],cwd=root,check=True,stdout=subprocess.DEVNULL)
            print('Removed prepared files:',p.name,flush=True)
    return {'keep':[r['tag_name'] for r in retained],'deleted':[r['tag_name'] for r in obsolete] if apply else [],'dryRun':not apply}

if __name__=='__main__':
    p=argparse.ArgumentParser(description=__doc__);p.add_argument('--repo',required=True);p.add_argument('--apply',action='store_true');p.add_argument('--prune-prepared',action='store_true');a=p.parse_args()
    print(json.dumps(run(a.repo,a.apply,a.prune_prepared)))
