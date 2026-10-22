# Open Repertory 1.20 — clearer menus

Package `com.openrepertory.app.dev`; version code 21; version name `1.20-dev`.

APK SHA-256: `902c24b66d964b2c7b707cdadbd9983fb6aa83c7d5b4b777b4d14a1a354b79c4`.

## Changes

The phone menu has seven actions in Clinical tools and App & account, with matching line icons and shorter labels. Primary destinations remain in the bottom bar; desktop has a compact navigation grid. Appearance and Online AI move into a Settings subview. Account access, backups and App updates remain directly available. Close/Escape/Android Back, focus containment/restoration, screen-reader background isolation, large text, scrolling, rotation and dark mode are covered.

All 690 clinical database/text assets retain identical bytes. Search, scoring, cases, patients, backups, source typography and existing Apper approval rules are retained. The signed updater implementation is byte-for-byte unchanged in maintained native source. No new Android permission, backend change or signing identity change.

## Pre-release verification: 204 checks passed

| Suite | Passed |
| --- | ---: |
| Menu layout, accessibility and actions in Chromium | 81 |
| Installed APK menu and Android Back in Android WebView | 14 |
| Installed Apper/access regression | 33 |
| Installed startup/foreground regression | 13 |
| Installed Owner/Member management regression | 14 |
| Native live HTTPS update-channel download/hash/signature checks | 3 |
| Packaged assets, identity, permissions and source checks | 12 |
| Release preparation/key/version safeguards | 8 |
| Browser source/scoring/snapshot/backup/materia regression | 9 |
| Browser reliability/recovery/search/report regression | 14 |
| Snapshot weights and search-state regression | 3 |

An additional actual in-place v1.19 → v1.20 installation retained the encrypted test record and installation fingerprint. The native live-channel check downloaded the current GitHub release without account credentials and verified its pinned signature, exact size and hash. The test emulator was configured to use the managed session proxy and its public CA; APK TLS verification/security policy was not weakened.

Browser sizes: 320×640, 360×780, 393×800, 412×915, 640×360, 768×850 and 1280×900; includes doubled menu text, long labels, rotation, both themes, keyboard focus and every action. Android installed tests use API 28 with WebView 66 and emulator-only synthetic approval receipts. They test the real native validators/bridges/UI without reading owner credentials or changing real grants.

The test environment initially restarted and an older emulator image had invalid cached system-package state. A fresh isolated emulator resolved that environment issue. Test harnesses were made sequential, and the existing asynchronous book-selection test now awaits completion before searching; the final runs above pass. These test-only fixes do not change clinical application logic.

## Update delivery and device limits

The release is delivered through the existing GitHub signed update channel and the verified hosted publication workflow. Version 1.19 users can select App updates → Check for updates → Download update → Install update. Keep the existing installation; never uninstall or clear storage. Android retains its permission/installation confirmation.

Post-publication discovery and installation from the shipped v1.19 client are recorded separately below when complete. Modern physical Samsung/Android installation prompts, real-account login and performance still require phone acceptance. No complete source-scan or medical-content certification is implied by this UI release.

## Post-publication end-to-end update: passed

The shipped v1.19 APK (version code 20) discovered v1.20 (code 21) through the actual public Latest endpoint using its existing native updater. It downloaded and verified the real release APK, then opened Android's installer. The test confirmed Install, and Android replaced v1.19 with v1.20. Encrypted clinical test records and the phone fingerprint were retained. Pulling the installed APK produced the exact tested/released SHA-256 above.

This was a real network download and Android package installation on the isolated emulator, using synthetic approval receipts and synthetic case records. No real owner credentials, backend grants or patient records were accessed. The VM alone trusts the configured session proxy CA; the shipped APK's TLS/security policy is unchanged. Physical phone vendor prompts and real-account login remain device acceptance checks.

Evidence: live-update-e2e.json, android-live-update-offer.txt and android-live-update-retention.txt in the workspace test-results/v1.20 directory. Public publication workflow: https://github.com/devil01529/Openrepertory-update/actions/runs/39774704876 . Anonymous public manifest/APK downloads and their pinned signature/hash also passed after publication.
