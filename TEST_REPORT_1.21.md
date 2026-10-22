# Open Repertory 1.21 — verification

22 October 2026. Android versionCode 22; package `com.openrepertory.app.dev`.

Implemented: floating clipboard Analysis shortcut for the active case while browsing repertory rubrics. Delivered separately: major clinical UI redesign plan and illustrative visual concept. The full redesign is not implemented in this APK.

| Automated suite | Passed |
|---|---:|
| Floating shortcut browser workflows and responsive layout | 130 |
| Existing menu, navigation and accessibility regression | 81 |
| APK integrity, unchanged data, permissions and signing identity | 12 |
| Existing source locking, analysis, snapshots, backup and references | 9 |
| Existing reliability/recovery/export/accessibility workflows | 14 |
| Existing snapshot and search-state workflows | 3 |
| Release preparation and rejection safeguards | 8 |
| Actual Android WebView shortcut and menu | 24 |
| **Total** | **281** |

Browser coverage: 320×640, 393×800, 640×360 landscape, 768×850 and 1280×900 for the shortcut; additional widths in the retained menu suite. Real rubric controls, multiple selected rubrics, full analysis/results, unchanged case/ranking, case switching, saved reload, last-row scrolling, preserving the reading position through temporary menus, clearing selections, menu/modal/chapter drawer/text entry, large text, keyboard activation, dark theme and print exclusion pass.

Android: API 28 emulator with WebView 66. The packaged button opens full analysis, respects the current case and clears correctly; existing Android Back handling and native Updates action still work. Account receipts in instrumentation are emulator-only synthetic fixtures; these classes are not packaged in the app. This is not a fresh live owner/member sign-in test or a physical-phone usability certification.

In-place `adb install -r` upgrade from shipped v1.20 to v1.21 succeeded. Six encrypted record ciphertexts, a decryptable sentinel and the per-installation phone identity remained unchanged. No uninstall or app-data reset was used for this upgrade check.

All 690 clinical data/text assets match v1.20 byte-for-byte. No added Android permissions; same signing certificate. Production native updater/access implementation is retained. No private keys, source PDFs, Firebase configuration or test receipt fixtures are included in the APK. Generated audit timestamps are not clinical content changes.

The landscape scroll test initially sampled the last result before lazy loading finished. It was corrected to use a bounded search result set. An initial native test also searched the large Kent dataset before completion on the software emulator; the final packaged-control test awaits Clarke loading and a bounded search. Both corrected suites pass. Initial startup verification also caught an absent optional footer reference; the shortcut now handles layouts with or without that footer safely.

APK SHA-256: `e35ab589d7753acc56e6d30b7ee0dcfed2bdfa0f661d9b0d3c5e13bec891d4c0`.

The existing signed update channel is used for publication. Android will still require the user to confirm installation. Use the release's unchanged APK filename and signed update.json; do not uninstall or clear storage.

## Public release verification

The v1.21 publication workflow completed successfully: [GitHub Actions run](https://github.com/devil01529/Openrepertory-update/actions/runs/39779574356). Anonymous HTTPS access to the app's actual `/releases/latest/download/update.json` endpoint returned versionCode 22 / 1.21-dev. Its signature verified with the existing pinned public key. The public APK downloaded without GitHub authentication and its 23,943,259 bytes matched the tested build's SHA-256. See PUBLIC_RELEASE_VERIFICATION_1.21.json. This is a host-side live channel check, not a second full Android installer test.
