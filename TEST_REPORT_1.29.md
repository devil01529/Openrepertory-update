# Open Repertory 1.29 validation

678 automated browser/package checks passed in this iteration.

Suite counts: {"source-switch-guard-tests.json": 24, "menu-web-tests.json": 81, "navigation-tree-tests.json": 115, "materia-reader.json": 210, "browser-reliability.json": 14, "compact-workspace-tests.json": 100, "clinical-ui-web-tests.json": 110, "workspace-banner.json": 6, "package-invariants": 18}

Materia Medica tests cover five viewports (320×640, 393×800, 640×360, 768×850, 1280×900), both themes and enlarged text; actual remedy search, clearing, source-only filtering, source text retention, author selection, section jumps, keyboard tabs, notes across refresh/reload, Back navigation and search preservation. Workspace tests verify removal of the reminder and top strip while retaining case switching, backups, draft recovery and interrupted imports. Existing clinical, menu, scrolling, source-identity and record-reliability checks are listed above.

694 clinical data assets match 1.28 byte for byte. Native classes are identical. Packaged changed assets match those tested in the browser. APK build and retained signing-identity verification passed.

Limits: Android instrumentation and physical-device frame-time measurements were not rerun in this iteration. Older native test results are not counted here. Browser checks do not guarantee zero bugs on every device.

APK SHA-256: `cd4fb1a5b02492d37d31f3bfb7a9fbf12d95104992bb71135531c3b591e841b4`

Public release verified: GitHub Actions run 39990279902 succeeded. Anonymous latest-channel metadata has a valid signature for version code 30 / 1.29-dev. The public APK matches the tested local SHA-256 and size. See PUBLIC_RELEASE_VERIFICATION_1.29.json.
