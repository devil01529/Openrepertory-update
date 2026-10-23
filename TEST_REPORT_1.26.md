# Open Repertory 1.26 — verification report

23 October 2026. Package `com.openrepertory.app.dev`; versionCode **27**; versionName `1.26-dev`.

| Suite | Passed |
|---|---:|
| Illustrated drawer scrolling, menu controls, text sizing and light/dark contrast | 124 |
| Today gestures, chapter filtering, picker and branch navigation | 115 |
| Responsive clinical layouts and mixed-source workflows | 100 |
| Mixed-source identity and persistence | 24 |
| Four-method score compatibility with published 1.23 | 16 |
| Menu navigation, accessibility and responsive layouts | 81 |
| Recovery, failed saves, backups and print workflows | 14 |
| Snapshot persistence and search state | 3 |
| Retained electronic Kent source comparison | 18 |
| Final APK assets, clinical data, identity and updater safeguards | 17 |
| Release preparation and rejection safeguards | 8 |
| Native background preservation, text zoom and actual keyguard/PIN unlock | 16 |
| Native startup recovery and quiet foreground return | 17 |
| Packaged Android UI, chapters, clipboard, analysis and Back | 42 |
| Native approval, denial and revocation | 33 |
| Native owner approval-management workflows | 14 |
| **Total** | **642** |

## UI and text sizing

Restored chapter artwork in compact square thumbnails and the chapter heading. The entire drawer scrolls, including book/source controls and the chapter filter; the list has no separate scroll container. Real wheel/touch checks retain Today scrolling, filtering, keyboard selection, current-chapter reselection and branch behavior.

Former top-strip controls live in the dedicated Repertory & reading menu section. Tests click the section, change text size, check persistence/reload, boundaries and Reset, toggle remedies and branches, open the patient-word finder and return with Back/focus restoration. Native checks verify actual WebView text zoom, including bounds and Reset. The APK uses text zoom so legacy pixel-sized text also scales. The browser preview scales its root font.

The analysis explanation and ranking heading use theme text/panel colors. The explanation meets at least 4.5:1 text contrast in light and dark across all five tested layouts: 320×640, 393×800, 640×360, 768×850 and 1280×900. Large-text menu actions remain reachable. No source grades, rubric wording or scoring formula changed.

## Session and device locking

The old elapsed-background-time relock is removed. A test ages the background timestamp beyond two minutes and verifies that authentication, the current clinical document/page, reading position and active case survive resume without a loading/login cover. Approval is refreshed quietly; denial and revocation still close the clinical bridge.

A real Android screen-off/keyguard test verifies device lock, invalidated in-memory authentication and blocked clinical bridge reads. The test wakes the device and enters its isolated test PIN through Android’s normal lock-screen UI; it checks that Android unlock resumes the surviving session without a second app PIN and preserves the original document and case. System screen/user-present notifications are registered only while the activity lives. No app password/PIN or authentication bypass is shipped.

Device locking hides the clinical surface and preserves screenshot protection in the background. Cold process starts still require initialization/authentication. Warm session retention does not imply that Android must keep a background process alive indefinitely. Owner approval, encrypted cache binding, offline expiry, account isolation and owner-management regressions pass.

## Upgrade and package integrity

The final 1.26 APK was installed over published 1.25 on the isolated emulator without uninstalling or clearing storage between the prepared baseline and replacement. **5 encrypted records** remained byte-identical and decryptable, and the phone identity was unchanged. Test records and receipts are synthetic. A previous emulator stopped during the session; final Android suites run on a fresh isolated API 28 emulator with WebView 66. Device credentials are entered through Android UI, not bypassed in app code.

All 689 retained clinical/text assets remain byte-identical to the earlier app. The separate English Kent retains all 68,742 source IDs and 624,010 remedy/grade records against the retained electronic OOREP snapshot; no new live crawl or printed-book certification is claimed. Mixed-source selections and all four scoring methods preserve their verified behavior.

The APK contains the tested web assets and the native classes exercised by instrumentation, with the same Android package/certificate and no added permissions. Test fixtures and private keys are excluded. Update metadata signatures, HTTPS/download bounds, hashes, package/certificate checks and installer safeguards are unchanged and verified.

APK bytes: **26835436**. SHA-256: `a65c30a9b181adb84bf9acd80d8a95a40120141995d9a1390e8084f307a40962`.

Automated verification covers Chromium and an API 28 software emulator. Physical-device speed and vendor-specific behavior can vary.

[Usage guide](CLINICAL_UI_GUIDE_1.26.md) · [Implementation plan](UI_SESSION_PLAN_1.26.md)

## Public release verification

[GitHub release job](https://github.com/devil01529/Openrepertory-update/actions/runs/39916709385) published version 1.26. An anonymous download from the Latest channel verified the metadata signature and downloaded APK against the tested build: versionCode 27, 26,835,436 bytes, SHA-256 `a65c30a9b181adb84bf9acd80d8a95a40120141995d9a1390e8084f307a40962`. See [verification record](PUBLIC_RELEASE_VERIFICATION_1.26.json).
