# Open Repertory 1.25 — verification report

23 October 2026. Package `com.openrepertory.app.dev`; versionCode **26**; versionName `1.25-dev`.

| Suite | Passed |
|---|---:|
| Today gestures, chapter filtering, picker and branch controls | 115 |
| Responsive clinical layouts and mixed-source workflows | 100 |
| Mixed-source identity, persistence and independent removal | 24 |
| Score compatibility against published 1.23 | 16 |
| Menu navigation, accessibility and responsive layouts | 81 |
| Storage recovery, failed saves, backups and print workflows | 14 |
| Snapshot persistence and search state | 3 |
| Retained electronic Kent source comparison | 18 |
| Final APK assets, clinical data, identity and updater safeguards | 17 |
| Release preparation and rejection safeguards | 8 |
| Packaged Android UI, real Today swipe, chapters, clipboard and Back | 42 |
| Android startup, late readiness and foreground transitions | 16 |
| Android approval and denial guards | 33 |
| **Total** | **487** |

## Today, book selection and rubric branches

The reported Today freeze was reproduced: its child expanded to 1,194 px in a 640 px viewport and scrolling stayed at zero. The bounded flex child now scrolls by wheel and touch. Tests reach the final action above bottom navigation, return from other pages and exercise enlarged text. Layouts: 320×640, 393×800, 640×360, 768×850 and 1280×900.

The remaining legacy native book select is hidden from interaction; both visible book buttons open the same short-name application picker. The chapter panel uses real keyboard-operable buttons, filters the current book, reports missing matches, restores the list on Clear and closes on selection, including reselecting the current chapter. Exactly one chapter is marked current. Decorative chapter pictures are removed.

The rubric list preserves actual parent/sub-rubric relationships. Controls show child counts, support individual expansion and one-tap Collapse/Expand branches. Re-rendering while collapsed creates no hidden child rubric DOM; expansion restores existing paged rendering. Case selections remain unchanged by these navigation controls. Remedy visibility remains separate.

## Native startup finding and fix

An initial API 28/WebView 66 test exposed a slow local-load timeout: the document eventually became ready after readiness polling had stopped, leaving the loading cover visible. The fixed timeout displays slow-start feedback and continues one-second polling while approved. Retry restarts correctly and foreground return resumes checks. The existing approval, foreground, attached-surface and generation guards remain in place. A native fixture deliberately ages a pending local load past the timeout, verifies polling continues, then restores readiness and checks that the cover clears automatically. Startup and approval/denial tests pass after this fix. No authentication bypass or fixture is shipped.

Native UI checks send real Android touch events to scroll Today and verify the final action, app-styled book picker, chapter filter/reselection and lazy branch expansion in the installed WebView. They also retain clipboard, analysis, mixed-source, patient-file, update-menu and Back coverage. Tests use an isolated software emulator and synthetic records/Apper receipts, with its PIN entered through Android’s normal credential UI.

## Upgrade, clinical data and release safeguards

Published 1.24 was installed as the baseline on the isolated emulator. Five synthetic encrypted records were staged; final 1.25 was installed over it with no uninstall or storage reset between prepare and verify. Raw encrypted records, decryptable patient/case data and phone identity were retained. Resetting the disposable emulator before establishing this baseline did not affect any user phone or live account.

All 689 retained clinical/text assets remain byte-identical to the earlier published app. The English Kent and relationship artifacts still match the retained electronic OOREP snapshot: all 68,742 original IDs and 624,010 remedy/grade links; no source wording or grade is changed. This is verification against the retained electronic source, not a new website crawl or printed-book certification. Both source identities and all four scoring methods retain their tested results.

The final APK contains the browser-tested web assets and native classes exercised by instrumentation. Android package/certificate are retained, with no new permissions. Update metadata signature, HTTPS, transfer bounds, download hash/size, package/certificate and installer safeguards are unchanged and checked. APK excludes private keys, source PDFs and native test fixtures.

## Artifact and limits

APK bytes: **26831340**. SHA-256: `5a52a3a999a9b8219527be9cec122a82cd7bb6ffa3ce2a9e21ac19caa739dfc2`.

Automated checks cover Chromium and an API 28 emulator with WebView 66. Physical-device performance and vendor-specific behavior may vary. The software emulator’s large-book startup timing is not a physical-phone benchmark. Existing clinical and access workflows are regression-tested; this is not a claim that every possible device or clinical scenario is bug-free.

[Usage guide](CLINICAL_UI_GUIDE_1.25.md) · [Detailed phased plan](REPERTORY_TREE_IMPROVEMENT_PLAN.md)

## Public release verification

GitHub Actions run 39898342714 independently verified the prepared artifacts and published normal Latest release v1.25. An unauthenticated download through the public Latest endpoint verified the metadata signature, versionCode 26, APK size and SHA-256; downloaded bytes exactly match the tested final APK. [Public verification record](PUBLIC_RELEASE_VERIFICATION_1.25.json). Released APK and signed metadata remain unchanged.
