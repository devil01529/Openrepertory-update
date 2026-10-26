# Open Repertory 1.38 validation

This release adds the complete repertory section of Guernsey's 1892 specialist volume and removes the 120-remedy analysis display cutoff. It does not add modern Synthesis or Complete Repertory. The [source audit](GUERNSEY_SOURCE_AUDIT_1.38.md) defines the twenty-page review scope and four printed remedy occurrences whose identities remain unresolved and unscored.

## Completed automated checks

| Area | Passed checks |
|---|---:|
| New source examples, coverage, grades, identities, references and retained clinical assets | 611 |
| New book UI, saved data, complete result paging/search, filters and responsive controls | 57 |
| Consultation, patient/visit workflows, reference review and scoring | 110 |
| Existing recoverable editing and original-source navigation | 38 |
| Mixed-book selection identity | 24 |
| Scoring compatibility | 16 |
| Current repertory-only reading panel | 200 |
| Materia Medica reader and private notes | 190 |
| Glass themes, accessibility, privacy and reduced motion | 323 |
| Grades 4/5, case normalization, snapshots and backup | 9 |
| Final signed APK, unchanged content, scoped native changes and installation identity | 660 |
| Actual 1.37 → 1.38 Android upgrade and installed clinical workflows | 16 |
| Large native encrypted records, compatibility, authentication and rollback | 16 |
| Legacy migration, patient fields/links/notes and collection integrity | 8 |
| Latest-five release-retention safeguards | 8 |

The original 1.27 menu-placement test describes a retired design. The current reading-panel suite checks the shipped repertory-only panel. The glass suite now checks all seven retained repertories plus the intentional Guernsey addition.

## What the new checks establish

The imported database accounts for 518 normalized source entries, 1,825 identified/scored remedy links, and four ambiguous occurrences with their printed grades retained unscored. Independent expected examples catch corrections to OCR font classifications and remedy identity mapping. All source reference targets exist; broad references offer explicit choices. These software checks validate the reviewed input and import; the source-page review was AI-assisted and is not independent clinician certification.

A case using twenty real Kent rows scores 294 remedies. All 294 were retrieved in exact rank order across result pages, and a remedy below rank 120 was found by name/abbreviation and opened for evidence review. Paging/search do not change the ranking. A consultation containing all 222 remedy-bearing Guernsey entries survives saving/reload without a selection-count cutoff. A stale page button cannot replace another case's results.

The UI checks cover 320px and 393px phones, 640px landscape and 1280px layouts, both themes, enlarged text, and 44px paging actions. The broader reading/glass/clinical suites also cover tablets, saved preferences, presentation mode, notes, source switching and visits. Recorded but filtered-out grade 1 remains distinguishable from an absent association without adding it to coverage or points. Entered search HTML is escaped.

## Package and record integrity

The actual signed APK was compared with published 1.37. Every existing repertory, Materia Medica passage, remedy dictionary and art asset is retained byte for byte. Only the new book is added under `assets/www/data`; the source registry, generated audit and legal notice acquire its metadata. The maintained scoring function is unchanged. The only production native source change is RecordStore: encryption and decryption collect all outputs from bounded cipher updates and reject unexpected byte lengths. Key alias, IV, AAD, AES-GCM authentication, SQLite transaction/recovery format and permission rules remain. Account approval, device lock, secure bridge and update protection sources match the frozen baseline exactly.

Package `com.openrepertory.app.dev`, version code **39**, version name **1.38-dev**. Same signing certificate and Android permissions. No PDFs, scan reader, standalone source archive, private key, emulator fixture or test authentication override is included.

APK size: **44,778,438 bytes**. SHA-256: `acd949aa8cafac9248319f17505243d10d9dc6b8a68daf924acb185ded7827cc`.

## Android validation

The large-case native test exposed an existing defect also reproduced using the unmodified 1.37 native code: large single-call Android Keystore cipher operations could lose output and leave an unreadable record. Independent small records and SQLite structural integrity remained valid. This finding led to the bounded-operation correction and dedicated large-record round-trip/authentication/rollback checks. Damaged synthetic test fixtures were archived and reseeded only under an emulator/known-synthetic-owner guard; no real user records were cleared. The production app continues to require verified backup recovery for already damaged ciphertext.


The actual signed 1.37 APK was replaced in place with final 1.38 on API 28 / WebView 66. All sixteen upgrade/workflow checks passed: ciphertext unchanged before first launch; stable installation and remembered approval; native encrypted workspace; three illustrated sections; source/grade-preserving selection; all 222 remedy-bearing entries saved and verified by a fresh encrypted reader; remedies 121–138 reachable; full-name search; exact source evidence; Android Back; reload retaining all entries and bold evidence; and the retained pre-update encrypted marker. The installed APK was pulled back and its hash matches the final prepared APK below.

Sixteen additional native storage checks passed, including exact round trips through a fresh reader for 16,384, 131,072, 262,144, 292,455 and 1,048,576-byte records, large Unicode content, ciphertext-length checks, failed-transaction rollback, altered-ciphertext rejection, record-identity/AAD binding and original-format compatibility. Eight migration checks passed for 200 synthetic legacy patients, linked cases, notes, unchanged peer ciphertext and missing-record detection.

The emulator uses synthetic account/case fixtures; normal PIN entry and live owner/member Apper sign-in are outside this release's test scope. No physical-phone performance or clinical-effectiveness claim is made.

## Publication

The update metadata uses the existing independent signature and verification key. Publication is followed by an unauthenticated download/hash/signature check and the existing latest-five-release retention check. No ZIP or standalone database asset is published.
