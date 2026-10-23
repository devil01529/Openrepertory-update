# Open Repertory 1.27 — verification report

23 October 2026. Package `com.openrepertory.app.dev`; versionCode **28**; versionName `1.27-dev`.

| Suite | Passed |
|---|---:|
| Full long-name retention, wrapping and Today reachability at three text sizes | 130 |
| Independent glass UI workflows, fallback, text sizing and clinical preservation | 323 |
| Independent screenshot contrast, themes, privacy and presentation-exit review | 225 |
| Illustrated drawer scrolling, reading menu and text sizing | 124 |
| Today gestures, chapter filtering, picker and branch navigation | 115 |
| Responsive layouts and mixed-source workflows | 100 |
| Mixed-source identity and persistence | 24 |
| Four-method score compatibility with published 1.23 | 16 |
| Menu navigation, accessibility and responsive layouts | 81 |
| Recovery, failed saves, backups and print workflows | 14 |
| Snapshot persistence and search state | 3 |
| Retained electronic Kent source comparison | 18 |
| Final APK assets, preceding-release clinical integrity, identity and updater safeguards | 23 |
| Release preparation and rejection safeguards | 8 |
| Android warm-state retention, native text zoom and actual keyguard/PIN unlock | 16 |
| Android startup recovery and quiet foreground return | 17 |
| Packaged Android chapters, menu, clipboard, analysis and Back | 42 |
| Android approval, denial, revocation and account isolation | 33 |
| Android owner approval-management workflows | 14 |
| Actual WebView 66 glass fallback, text zoom, source grades and privacy | 20 |
| Actual WebView compact context and usable reading space | 8 |
| **Total** | **1354** |

## Independent review and corrections

The implementation followed four phases: material foundations; application to navigation/cards/controls; independent review and regression testing; signed delivery. Two independent subagents wrote and ran browser suites. A third exercised the final APK on Android. The UI reviewers found low-contrast menu subtitles and selected chapter counts, fixed-navy chapter headings in dark mode, and stale patient/case labels after leaving Presentation mode. A final specificity check also restored the selected rubric’s green/mint contrast and the active importance control’s white/blue contrast. The final screenshot review also corrected the Selected-view heading and count-badge contrast and made menu-dot centering explicit for the older WebView. A final content stress test also corrected long unbroken-name wrapping and excessive context height on short screens. All were fixed before the final Android build; the label fix covers both the shield exit and settings Apply paths. See [independent review](LIQUID_GLASS_REVIEW_1.27.md).

Actual screenshot-composited contrast sampling meets at least 4.5:1 for sampled navigation/menu/chapter text across light, dark and automatic dark themes. Examples: chapter count 5.36:1 light/4.89:1 dark; menu subtitles approximately 5.85:1 light/7.57:1 dark. Responsive tests cover 320×640,393×800,640×360,768×850 and 1280×900; 80–140% text; Today/drawer scrolling; enlarged menu and dialog reachability; keyboard/focus/navigation; source picking; real rubric selection and clipboard; Selected/Ranking/Matrix; saved controls; privacy; reduced motion and print. A further 130 checks cover long unbroken patient/case names at 80%, 100% and 140% across all five layouts: full strings remain intact, cards do not overflow, and the final Today action remains reachable. Short screens compact the top context switch with ellipsis while retaining full DOM text, Today labels and context-dialog names.

## Glass rendering and preserved clinical functions

Opaque materials are the baseline. A feature query enables a small static backdrop blur on only the header, navigation and menu; no scrolling rubric list or matrix is blurred. The saved Glass effects switch forces solid surfaces. Unsupported filters are tested by removing enhancement rules in Chromium and by the actual older Android WebView 66. Supported reduced-transparency/high-contrast preferences use solid materials. Clinical values, forms and the presentation shield remain opaque.

Original base/clinical styles and clinical/search/patient/backup/lifecycle JS modules outside clinical-ui.js are byte-identical to published 1.26. All preceding-release clinical assets, registry and reference metadata remain byte-identical. The generated build audit report differs only in its generation timestamp, with identical audit content. The earlier 689 retained clinical/text assets still match their original baseline. Source remedy grades retain their colors and italic/bold/case distinctions. All four scoring methods agree with published 1.23 under both grade 1-filter settings; mixed-source identity, grades and snapshots remain stable.

The retained electronic Kent audit checks 68,742 original source IDs and 624,010 remedy/grade links against the saved OOREP snapshot. This is a retained-source integrity check; no new live scrape or printed-book certification is claimed.

## Android, access and upgrade

The final frozen APK was installed over published 1.26 on an isolated API 28 emulator with WebView 66, without uninstalling or clearing storage between preparation and replacement. **7 encrypted records** remained byte-identical/decryptable, and phone identity was unchanged. Test records/receipts are synthetic and are excluded from the APK.

Native suites exercise text zoom, actual Android screen locking/PIN unlock, in-memory warm state, quiet foreground approval, delayed startup recovery, packaged navigation/analysis, owner management, denial/revocation, sign-out and account isolation. Session/Startup ran before the selected-button color adjustment. Menu/approval/owner suites ran after that adjustment and before the final heading, count-badge and menu-centering fixes. Their native classes and all JavaScript are byte-identical to the final APK. The Glass suite then verified heading, count, menu-centering and name-wrapping fixes. The final APK additionally verifies compact short-screen context and the actual 1.26 upgrade. All retained suite artifacts and the final package have directly compared byte-identical native classes and JavaScript; only the recorded CSS changes and build timestamp differ. This avoids repeating unrelated passed workflow scenarios while preserving explicit artifact provenance. The saved native-suite-provenance.json and exact CSS diffs are included in the source overlay; the public root also provides NATIVE_TEST_PROVENANCE_1.27.json. Existing security behavior is retained: a surviving unlocked background session can resume quietly; secure device lock blocks access; cold starts reinitialize and verify approval. All production native classes.dex bytes are identical to published 1.26; no native authentication/storage/updater implementation was changed for this visual release.

Package name and Android signing certificate are retained; no additional permissions. Packaged CSS/JS match the tested workspace, and final classes.dex matches the native test record. Update signatures, HTTPS transfer/download bounds, APK hash/package/certificate checks and installer safeguards remain unchanged and pass their checks.

APK size: **26839609 bytes**. SHA-256: `7c52fcba9d38de398bf5a4edc18106c3c254c11491b74085c7a501005d96605a`.

Automated Chromium/Android emulator testing found no outstanding failures in these scenarios. It cannot guarantee zero bugs on every device; physical-device performance and vendor-specific rendering remain user-device validation limits. Public download verification is recorded after publication without changing released assets.

[Usage guide](CLINICAL_UI_GUIDE_1.27.md) · [Phased plan](LIQUID_GLASS_PLAN_1.27.md)
