# Open Repertory 1.23 — verification report

23 October 2026. Package `com.openrepertory.app.dev`, Android versionCode 24, versionName 1.23-dev.

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
| Actual Android clinical UI, clipboard, Back and menu | 34 |
| Android approval/access controls | 33 |
| Android startup/foreground transitions | 13 |
| Android owner approval management | 14 |
| Shared-path source switching regression | 18 |
| **Total** | **500** |

Browser layouts: 320×640, 393×800, 640×360 landscape, 768×850 and 1280×900, with additional widths in the menu suite. Tests cover patient/case context placement, consultation-first Today, icon-only action, phone/rail placement, 60% default phone reading-space target, collapsed finder, all analysis views, unchanged case/rankings, source details, density/full-remedy preferences, source-reference return navigation, alternate-name search, private remedy notes, patient Overview/Visits/Cases/Notes, saved visits and reload, tablet master/detail, presentation-mode masking and 200% text.

All four analysis methods, with grade 1 included and excluded, match the actual published 1.21 app on identical weighted fixtures. Existing six clinical datasets/text assets remain unchanged; only source-registry/audit metadata changes and the separate English Kent/relationship assets are added. Package checks verify **689 retained clinical data/text assets byte-for-byte**, including the legacy Publicum database.

## Kent audit

The independent exhaustive comparison checks **all 68,742 source IDs and paths, and all 624,010 remedy records/grades**, against the fully cached live API source. Missing or extra imported original IDs: **zero**. The original 37 chapter index rows, 68,705 rubric rows and 1,039 synthetic navigation headings are distinguished. All five explicit cross-reference occurrences resolve within the Kent source. All 103 alternate remedy names match the source. Two conflicting source versions of “clenching, fingers” retain their separate grades and cannot both be selected in one case.

This is an audit against OOREP's electronic Kent source, not a printed-book scan certification. See [the full audit](KENT_OOREP_AUDIT_1.23.md) and the clinical-only source snapshot ZIP.

## Android and upgrade

API 28 emulator with actual WebView 66: packaged icon-only control, rubric selection, analysis matrix, Android Back, patient Notes/Overview, menu, native Updates, approval denials/revocation, startup and owner management pass. Hosted account receipts in instrumentation are synthetic emulator-only fixtures; they are not included in the APK. Production Apper services and per-phone approval policy are unchanged.

An actual in-place installation from published **v1.21 → v1.23** succeeded. **Five encrypted records**, including a synthetic linked patient, legacy Publicum case and decryptable sentinel, retained identical encrypted bytes and the same phone identity immediately after replacement. The new UI also confirms that the saved case remains under source key `kent`; it is not reassigned to English Kent. No uninstall or data reset was used for this upgrade check.

Same Android signing certificate, same package, no added permissions. Native metadata-signature/download/hash/certificate/install logic is unchanged. Test fixture classes, private keys, source PDFs and archived Firebase configuration are excluded from the APK.

APK bytes: **26,831,340**. SHA-256: `c68cfc4194d8e2a3729e2286dddc888aed64d5c3503d6ced2ce684dde94017fc`.

## Issues found during verification

- Older Android WebViews could prepare the page but leave the loading cover waiting on its first visual callback. Attached-surface pre-rasterization and invalidation now prepare the covered frame before it is revealed.

- Layout testing caught inherited header flex/order rules after moving case context; the consultation strip now occupies its own stable row.
- Visual review caught inherited tablet rail positioning; it is fixed outside the page flow, and context width/adjacency checks were strengthened.
- Patient-to-analysis navigation now restores the actual active-case identity rather than leaving a viewed-file label.
- Short landscape tests caught a floating control covering Add actions; the icon now docks into a reserved tools slot.
- Duplicate source paths exposed a source-grade conflict; both records remain distinct instead of merging grades or counting both.
- Existing tests were adapted to deliberate new navigation: three Settings actions, the tablet rail, patient tabs and separate analysis views. Their persistence/recovery/scoring assertions were retained.

No physical-phone usability study, fresh live owner/member credential sign-in, or clinician timing study was performed. The 150ms real-device interaction target in the design plan is not certified by these tests. The emulator/browser evidence and exhaustive electronic-source comparison establish the tested scope.

## Source-switching correction

Version 1.22 was withdrawn from the stable Latest channel after final review found that legacy chapter/path UIDs can be shared by different books. The old toggle checked source ownership only when inserting an item; an identical UID in another book could enter the removal branch first.

Version 1.23 checks source ownership before both removal and insertion. Initial and refreshed Added states, including library/differential helpers, are source-aware. Existing saved UIDs and clinical data are not renamed. The new browser regression tests use actual ABRUPT entries in both Publicum → Kent and Kent → Publicum directions, verify byte-identical in-memory case/ranking content after rejected clicks/direct toggles, and verify independent new cases. The Android suite checks the same protection on an upgraded saved Publicum case.

## Published update verification

After GitHub Actions published v1.23, the public Latest endpoint served versionCode 24. Its RSA signature verified against the pinned public key. The downloaded 26,831,340-byte APK matched the tested final SHA-256 exactly. No GitHub account credentials were supplied by the verification client. See [the verification record](PUBLIC_RELEASE_VERIFICATION_1.23.json). The release is public, normal and Latest.
