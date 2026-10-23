# Open Repertory 1.24 — verification report

23 October 2026. Package `com.openrepertory.app.dev`; Android versionCode 25.

## Results

| Suite | Passed |
|---|---:|
| Compact layouts, chapter lifecycle, remedy controls and mixed-source workflows | 100 |
| Source identity and persistence in both book directions | 24 |
| Scoring comparison with published 1.23 (four methods, both grade filters, Kent/Clarke) | 16 |
| Menu navigation, accessibility and responsive layouts | 81 |
| Persistence, failed-save recovery, backups and print workflows | 14 |
| Snapshot persistence and search state | 3 |
| Exhaustive retained Kent source comparison | 18 |
| Final APK, clinical assets, tested web/native modules, identity and updater safeguards | 17 |
| Release preparation and rejection safeguards | 8 |
| Android clinical UI, clipboard, source identity, Back and menu | 34 |
| Android approval/access controls | 33 |
| Android startup and foreground transitions | 13 |
| Android owner approval management | 14 |
| Android update summary, long notes, Back and background lifecycle | 9 |
| **Total** | **384** |

Browser layouts: 320×640, 393×800, 640×360 landscape, 768×850 and 1280×900, plus menu rotation and large-text recovery checks. Real click paths verify chapter opening/closing, both visibility controls, inline remedy spacing, compact selected rows, horizontal importance controls, available-width analysis, tablet Selected access, matrix visibility, plain book names and long-name containment.

## Clinical data and mixed sources

All 689 retained clinical/text assets remain byte-identical to the earlier published app. The English Kent, relationship and audit JSON in the APK match the exhaustively verified local artifacts. The electronic Kent snapshot retains all 68,742 original IDs and 624,010 remedy/grade records; zero imported original IDs are missing. This update changes presentation and selection identity, not the source datasets.

Both Publicum → Kent and Kent → Publicum allow the same chapter/path to be selected independently. Tests verify source IDs, untouched original grades/provenance, individual importance/removal, snapshots, reload and ranking. Clarke presence links are handled per selected entry in mixed cases. All four analysis methods reproduce the published 1.23 scores on identical single-book fixtures, with grade 1 included and excluded.

## Android and upgrade

Native checks use an isolated API 28 emulator with WebView 66 and synthetic Apper receipts. They verify the actual packaged UI, existing approval/denial/revocation and owner controls. Device credentials are entered through Android’s normal credential UI using the isolated emulator’s test PIN; authentication is not bypassed in the APK. Test waits account for software-emulator rendering and large book parsing.

A final installation of published v1.23 followed by the final v1.24 replacement retained five encrypted records byte-for-byte and the same phone identity. The synthetic baseline includes a linked patient, case, source identity and decryptable sentinel. There was no uninstall or storage reset between the old-version baseline and replacement.

The long-note test uses a deliberately large release-note payload: the main update card excludes it, the complete Download action remains visible, full notes open in a separate scrollable reader, Back returns to the controls, and backgrounding closes the notes window while retaining the clinical document. No fixture or synthetic account enters the APK.

Native tests exposed an older-WebView compositor callback stall under the startup cover. The cover now permits preparation of the underlying approved surface; a bounded, measured-surface reveal uses the existing generation/access guards. Approval/revocation checks continue to close the clinical bridge.

Package checks verify the browser-tested modules/styles and the native classes exercised by instrumentation. Metadata signatures, HTTPS/redirect checks, transfer bounds, hash/size, package/certificate checks and installer logic remain unchanged. No extra Android permissions are added.

## Artifact and limits

APK bytes: **26831340**. SHA-256: `191149e13daf1349ce6d28ab79b745da121e081cc5ff13d3cfa19b9cee50e1ec`.

Physical-phone frame timing, Samsung-specific system-font settings, a fresh live Apper credential login and clinician usability timing were not certified. The web layouts and Android emulator establish the tested scope. The Kent comparison is against the retained OOREP electronic snapshot, not a new printed-book scan audit. Older UI acceptance scripts describe their release-specific behaviours; the targeted 1.24 suite is listed in INTEGRATION_1.24.md.

## Published download verification

The public Latest endpoint now serves versionCode 25 (1.24-dev). Its RSA metadata signature verifies against the pinned key. The public 26,831,340-byte APK download matches the final tested artifact exactly: `191149e13daf1349ce6d28ab79b745da121e081cc5ff13d3cfa19b9cee50e1ec`. No GitHub credentials were supplied by the verification client. See [the verification record](PUBLIC_RELEASE_VERIFICATION_1.24.json).
