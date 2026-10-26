# Open Repertory 1.36 validation

This update keeps book names while replacing routine provider labels with **Open Repertory source**. Original attribution, source records and full licences remain accessible through Legal credits. It adds encrypted remembered approval for up to 30 days, quiet online checks, and a workspace that becomes usable before the full reference book loads.

## Completed checks

| Area | Passed checks |
|---|---:|
| Repertory labels, book identity, raw provenance and legal-notice retry | 58 |
| Materia Medica labels, authors, original text and full licences | 32 |
| Early workspace, delayed/failing book loads, queued search and retry | 21 |
| Early Library access and delayed book/index races | 15 |
| Clinical workflow, patients, notes, rubric controls and responsive UI | 110 |
| Mixed-book selection identity and retained provenance | 24 |
| Scoring compatibility against published 1.23 | 16 |
| Repertory-only reading panel, text sizing, focus and motion | 200 |
| Glass UI, readable analysis, themes, solid fallback and privacy | 323 |
| Historical removed-book evidence, snapshots, exports and backups | 62 |
| Production remembered-approval and receipt rules on host JVM | 85 |
| Release preparation and signing failure handling | 8 |
| Final APK, retained assets, packaged code and update identity | 650 |
| Actual installed Android 1.35 → 1.36 upgrade and session behavior | 21 |
| Release-retention safeguards | 8 |
| **Total** | **1,633** |

## Content and package preservation

All seven remaining repertory databases, remedy dictionaries, Materia Medica passages, chapter art and legal notices match the published 1.35 assets byte for byte. The generated audit report differs only in its build timestamp. No clinical content, grades, source identities, image assets or legal notices were added or removed. Maintained JavaScript was compiled for Chrome 66; the tested browser code matches the signed APK.

The package remains `com.openrepertory.app.dev`, with version code **37**, version name **1.36-dev**, the original signing certificate and no new Android permissions. Firebase clients, test receipts, emulator authentication overrides, private keys, source archives and PDF scans are excluded from the production APK.

Tested APK SHA-256: `7018b792507e1cfe208058024488df12352c0e2d968f7465699cfbc03cd1e50a`.

## Startup measurement

Three isolated browser starts per CPU setting compared the exact published 1.35 web assets with the final 1.36 assets on the same host. Median time until Today and its consultation card were ready:

| CPU setting | 1.35 | 1.36 |
|---|---:|---:|
| Normal | 1.040 s | 0.838 s |
| 4× CPU slowdown | 2.312 s | 1.364 s |

The latter is about **41% faster** in this controlled browser check. It measures local workspace readiness, not device-unlock time or a real phone's hosted-service sign-in. Phone timings vary. Deliberately holding the 23 MB Kent response proved that Today, saved analysis and patient tools remain usable before reference loading completes; delayed and failed responses, retry and rapid source switching were tested separately.

## Android session checks and fixes

The exact final APK was installed over the actual published 1.35 APK on API 28 / WebView 66. Encrypted record bytes and the installation fingerprint were identical across the upgrade. Still-valid legacy approval migrated without a new remote receipt. Warm return preserved the page and document. Synthetic next-day/previous-boot approval, hosted-session expiry, service errors, account mismatch, revocation, fixed expiry, the device-lock event and explicit sign-out were exercised.

Review and testing found and fixed an invalid-cache resume path that needed to close native record reads synchronously. Sign-out also needed a bounded draft-flush wait so a busy clinical renderer could not hold it indefinitely. The final run confirmed that approval and persistent cookies are cleared while encrypted records remain intact. A byte-for-byte pull of the installed APK matched the prepared release.

These Android tests use synthetic approval receipts and an **emulator-only authenticated fixture**. They test production approval, storage, lifecycle and bridge code; they do not perform a real owner/member login or retest the normal Android PIN dialog. The fixture is absent from the release APK. The live account portal returned HTTP 200; this is a reachability check, not a live authenticated backend acceptance test. The server's approval model and permissions were not changed in this release.

Remembered approval is account/device bound, encrypted, fixed to its last successful verification and limited to 30 days. Local use and failed refreshes cannot renew it. Confirmed pending/rejected/revoked decisions close access when received. Offline or expired-hosted-session revocation cannot be observed immediately; access can remain valid until the fixed remembered-approval expiry. Owner-management writes continue to require the portal's authenticated server permissions.

The signed update manifest and public APK download are checked again after publication. The existing release workflows retain the latest five published versions and remove the oldest version first. No ZIP release asset is published.
