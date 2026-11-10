# Hosting investigation

User requirement: hosting must remain completely free; owner should manage APK and website access from the APK, without repeated copy/paste administration.

## Railway

Official documentation: https://docs.railway.com/pricing/free-trial
The trial is $5 for up to 30 days, followed by a Free plan with $1 monthly credit. Railway itself is not limited to 30 days, but that allowance does not guarantee continuous operation of this service. Trial volume-retention warnings also apply. The user's actual billing entitlement was not established. The current service has not been deleted or disabled.

## Apper

Official pricing: https://apper.io/pricing
The published Free plan lists one app user, 5,000 records and 100 MB file storage. Paid plans list multi-user access. The page contains inconsistent profile-count details, so no unsupported profile quota is assumed. Existing project 54be7eef4ac24f7c907d0576b168ccff is connected to a live database with Owner_c and Member_c profiles and device requests/grants. Apper can host the frontend, but its published free offering does not meet the requested multi-user production setup. No paid plan or new database storage was provisioned.

## Free-tier migration candidate

Cloudflare Workers + D1, using a free workers.dev subdomain.
- Workers Free: 100,000 dynamic requests/day and 10 ms CPU/invocation; static asset requests are free and unlimited.
- D1 Free: 5 million rows read/day, 100,000 rows written/day, 5 GB total storage, 10 databases.
- Free limits must be respected; no claim of unlimited service or immutable future pricing. Password hashing and asset authorization must be measured against runtime limits before a cutover.
- Sources: https://developers.cloudflare.com/workers/platform/pricing/ and https://developers.cloudflare.com/d1/platform/pricing/

No connected Cloudflare plugin was found. Account authorization is still needed. Do not publish an APK pointing to an unprovisioned backend. Migration must preserve account identifiers and existing local encrypted records, secure owner-only actions, test approved/pending/revoked states and validate the public signed update path before enabling it.
