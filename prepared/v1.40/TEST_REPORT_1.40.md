# Open Repertory 1.40 validation

## Completed browser checks

| Area | Checks passed |
|---|---:|
| Consultation tools, lazy remedies, scoped history, reader finding, privacy and save failures | 45 |
| History prefetch cancellation and case-switch isolation | 4 |
| Confirmed per-case history clearing and record preservation | 3 |
| Mixed-book Materia Medica source navigation and Back restoration | 6 |
| Glass, themes, contrast, privacy, printing and fallback | 323 |
| Repertory-only reading panel | 200 |
| Materia Medica and private notes | 190 |
| Clinical workflows and patient/visit navigation | 110 |
| Recoverable edits and source review | 38 |
| Guernsey and complete scored-result browsing | 57 |
| Mixed-book source identity | 24 |
| Scoring compatibility | 16 |
| Higher grades and backups | 9 |
| Signed APK isolation and identity | 672 |
| Actual 1.39 → 1.40 source/storage upgrade | 16 |
| Native clinician tools, encrypted history and preservation | 16 |
| Release retention safeguards | 8 |

The clinical alias-search check now shows remedies before inspecting their buttons, matching the intended lazy-rendering behavior. Underlying associations remain available for selection while hidden.

A measured first English Kent Mind chapter uses 2,239 descendants with remedies hidden versus 5,027 when shown: 2,788 remedy buttons avoided, approximately 55% fewer descendants. This is a DOM measurement, not a physical-device memory or frame-rate claim. It does not implement full list virtualization.

The workflow tests verify original associations/scoring when selecting hidden rubrics; case-specific history across reload and missing/unsupported history protection; linked-patient/visit routing; actual snapshot and backup entry points; source-only reader marks/clear, literal punctuation and edition-change cancellation; presentation protection; preference-write failure rollback; and 320px, landscape and tablet layouts. A held real book request proves that closing history/changing case prevents late navigation into that book. Mixed-book reader links identify each original book, navigate to exact source rubrics and return to the reader without changing selections or scores. The explicit related-remedy action resets chapter scope to whole book.

## Content and security isolation

All original repertory, remedy, Materia Medica, artwork and legal assets are compared with released 1.39. Only the generated audit's build timestamp may differ. Native classes.dex remains identical: encryption, approval, device lock, recovery, screenshot policy and updater code are unchanged. The deterministic rankCase implementation is compared directly with the frozen baseline. Patient, backup and lifecycle modules remain unchanged.

The only new bundled script is the local clinician-tools module. Source text and notes are never persisted with reader marks. Recent searches use a separate native encrypted record and are explicitly outside the clinical backup payload. No standalone database, PDF, ZIP, signing key or test authentication override is shipped.

## Signed APK and Android

Final package identity: com.openrepertory.app.dev, version code 41, version name 1.40-dev; 44,786,779 bytes. SHA-256: b07c088587a4d637317a4549fd8d4bdb2948ea63aa7123d6cb8957ef8387ae46. Package isolation checks passed: 672. All 32 native checks passed on the final signed APK: upgrade ciphertext/identity/approval retained; all 222 Guernsey entries saved/reloaded with original grades; analysis beyond 120 remedies and source navigation retained; lazy native remedy lists, actual review routing, encrypted scoped search history, source-text finding/clear, unchanged notes, preference rollback, presentation protection and reload preservation verified. The installed APK was pulled back and matches the prepared APK hash exactly. Android checks use isolated synthetic records/approval on API 28 / WebView 66. Live Apper backend authorization, physical-phone performance, independent clinical proofreading and modern gesture certification are outside this UI/workflow release's validation scope. Software checks cannot guarantee absence of all device-specific defects.

## Delivery

Signed metadata and APK identity are verified before publication; anonymous downloads are verified afterward. Existing latest-five release retention continues. Install over the existing app without clearing storage.
