# Open Repertory 1.28 validation

554 checks passed in this iteration. Details: {"source-switch-guard-tests.json": 24, "menu-web-tests.json": 81, "browser-snapshot-tests.json": 3, "navigation-tree-tests.json": 115, "browser-reliability.json": 14, "reading-panel.json": 200, "compact-workspace-tests.json": 100, "package-invariants": 17}.

The reading-panel suite covers five viewport sizes, light/dark themes, 140% text, scrolling to every control, saved preferences, repeated close/open, JavaScript Back routing, Escape/focus return, page changes, patient-word finder, density dialog and reduced-motion behavior. Menu, compact workspace, touch-scroll/tree, mixed-source identity, recovery and snapshot suites also passed.

694 bundled clinical data assets match 1.27 byte for byte. Native classes and all non-UI JavaScript modules are unchanged. APK build/signature verification passed with the retained signing certificate; tested web assets match the APK.

Limits: this iteration ran Chromium browser tests and APK/package checks, not Android instrumentation or a physical-phone frame-rate benchmark. Emulator startup was unavailable due to stale AVD ownership and software-emulation configuration. Earlier native test results are not counted as new passes. No claim of zero bugs or guaranteed frame rate is made.

APK SHA-256: `f24742abd9be0cfe590956b3b59a82bd96787a09d26dc9164b6cc0612cc29e47`

Public delivery verified: GitHub Actions run 39988420568 succeeded. Anonymous latest-channel metadata has a valid signature and resolves to version code 29 / 1.28-dev. The downloaded APK matches the tested local SHA-256 and size. See PUBLIC_RELEASE_VERIFICATION_1.28.json.
