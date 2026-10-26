# Open Repertory 1.35 validation

The three requested repertories—Clarke, Knerr and Boger—are removed from the catalog, static selector, provenance registry and packaged database assets. Existing historical case evidence is retained. C. W. Wolf’s complete 1858 Apis monograph is added as a separately attributed, sectioned Materia Medica source.

Completed browser/data/package checks:

| Area | Passed checks |
|---|---:|
| Removed-source startup, cases, grades, scoring, source details, snapshots, exports and encrypted backups | 62 |
| Remaining repertories and remedy identity files unchanged | 12 |
| Wolf extraction completeness, identity, source hashes and reproducibility | 20 |
| Wolf full rendered text, sections, attribution, licence and private notes | 34 |
| Clinical UI, records, source links and scoring compatibility | 110 |
| Materia Medica reader regression | 190 |
| Mixed-source selection identity | 24 |
| Final APK integrity and unchanged retained assets | 586 |

The original seven remaining repertory files, remedy dictionary and historical Clarke remedy-name dictionary match published 1.34 exactly. Existing Materia Medica passages and classical shards are preserved. Full retired datasets and raw source scans/HTML are outside the APK. The APK retains its signing certificate, package name and permissions, with a higher version code (36).

Online sources were researched separately. No unreviewed replacement repertory was relabelled as verified. Wolf’s upstream transcription credits Distributed Proofreading; the import checks extraction/identity and does not claim new scan-by-scan proofreading or clinical validation.

**22 Android checks passed** on API28 / WebView66 after installing1.35 over published1.34. The encrypted record bytes and phone identity were identical before first launch. Historical Clarke, Knerr and Boger cases, source details, snapshots, selector removal, Wolf text, author switching and the offline licence all passed in the installed APK. The installed APK matched the prepared release byte for byte.

**1,068 counted checks passed**, including eight release-retention safeguards.

Android content checks used synthetic cases/approval receipts and an emulator-only authenticated test fixture. The unchanged Android PIN gate was not retested in1.35: the disposable emulator's SystemUI stalled its normal PIN dialog. The content fixture and test classes are excluded from the release APK. The normal PIN/session and access implementation was tested in1.34; no authentication behavior was changed for this content update.

The public download and signed update channel are verified after publication.
