# Open Repertory 1.22 — verification report

22 October 2026. Package `com.openrepertory.app.dev`, Android versionCode 23, versionName 1.22-dev.

## Automated results

| Suite | Passed |
|---|---:|
| Major clinical UI workflows, adaptive layouts and scoring comparison with published 1.21 | 110 |
| Icon-only shortcut, selection/case lifecycle and responsive layout | 130 |
| Grouped tools/settings, navigation and accessibility | 81 |
| Exhaustive OOREP Kent source comparison | 18 aggregate assertions |
| Final APK integrity, retained data, identity and permissions | 15 |
| Existing source locking, analysis, backups, reference loading and CSP | 9 |
| Existing failed-save/recovery/draft/print/search workflows | 14 |
| Existing snapshot persistence and search-state workflows | 3 |
| Release preparation and rejection safeguards | 8 |
| Actual Android clinical UI, clipboard, Back and menu | 30 |
| Android approval/access controls | 33 |
| Android startup/foreground transitions | 13 |
| Android owner approval management | 14 |
| **Total** | **478** |

Browser layouts: 320×640, 393×800, 640×360 landscape, 768×850 and 1280×900, with additional widths in the menu suite. Tests cover patient/case context placement, consultation-first Today, icon-only action, phone/rail placement, 60% default phone reading-space target, collapsed finder, all analysis views, unchanged case/rankings, source details, density/full-remedy preferences, source-reference return navigation, alternate-name search, private remedy notes, patient Overview/Visits/Cases/Notes, saved visits and reload, tablet master/detail, presentation-mode masking and 200% text.

All four analysis methods, with grade 1 included and excluded, match the actual published 1.21 app on identical weighted fixtures. Existing six clinical datasets/text assets remain unchanged; only source-registry/audit metadata changes and the separate English Kent/relationship assets are added. Package checks verify **689 retained clinical data/text assets byte-for-byte**, including the legacy Publicum database.

## Kent audit

The independent exhaustive comparison checks **all 68,742 source IDs and paths, and all 624,010 remedy records/grades**, against the fully cached live API source. Missing or extra imported original IDs: **zero**. The original 37 chapter index rows, 68,705 rubric rows and 1,039 synthetic navigation headings are distinguished. All five explicit cross-reference occurrences resolve within the Kent source. All 103 alternate remedy names match the source. Two conflicting source versions of “clenching, fingers” retain their separate grades and cannot both be selected in one case.

This is an audit against OOREP's electronic Kent source, not a printed-book scan certification. See [the full audit](KENT_OOREP_AUDIT_1.22.md) and the clinical-only source snapshot ZIP.

## Android and upgrade

API 28 emulator with actual WebView 66: packaged icon-only control, rubric selection, analysis matrix, Android Back, patient Notes/Overview, menu, native Updates, approval denials/revocation, startup and owner management pass. Hosted account receipts in instrumentation are synthetic emulator-only fixtures; they are not included in the APK. Production Apper services and per-phone approval policy are unchanged.

An actual in-place installation from published **v1.21 → v1.22** succeeded. **Five encrypted records**, including a synthetic linked patient, legacy Publicum case and decryptable sentinel, retained identical encrypted bytes and the same phone identity immediately after replacement. The new UI also confirms that the saved case remains under source key `kent`; it is not reassigned to English Kent. No uninstall or data reset was used for this upgrade check.

Same Android signing certificate, same package, no added permissions. Native metadata-signature/download/hash/certificate/install logic is unchanged. Test fixture classes, private keys, source PDFs and archived Firebase configuration are excluded from the APK.

APK bytes: **26,831,340**. SHA-256: `b52d0d632a0364e048550f5d7503dfab9518f30b4eb35d63428f0c508d4ea771`.

## Issues found during verification

- Layout testing caught inherited header flex/order rules after moving case context; the consultation strip now occupies its own stable row.
- Visual review caught inherited tablet rail positioning; it is fixed outside the page flow, and context width/adjacency checks were strengthened.
- Patient-to-analysis navigation now restores the actual active-case identity rather than leaving a viewed-file label.
- Short landscape tests caught a floating control covering Add actions; the icon now docks into a reserved tools slot.
- Duplicate source paths exposed a source-grade conflict; both records remain distinct instead of merging grades or counting both.
- Existing tests were adapted to deliberate new navigation: three Settings actions, the tablet rail, patient tabs and separate analysis views. Their persistence/recovery/scoring assertions were retained.

No physical-phone usability study, fresh live owner/member credential sign-in, or clinician timing study was performed. The 150ms real-device interaction target in the design plan is not certified by these tests. The emulator/browser evidence and exhaustive electronic-source comparison establish the tested scope.
