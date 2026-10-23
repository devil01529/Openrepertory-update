# Open Repertory 1.24 source overlay

Apply over the retained full app source. Preserve the existing clinical assets and both signing keys privately. Package com.openrepertory.app.dev; versionCode 25; versionName 1.24-dev. Build with bash build.sh and the pinned toolchain.

The maintained UI is in web-src/js/clinical-ui.js, app.js and apk/assets/www/css/clinical.css. Selection identity is now source ID plus the retained legacy rubric UID; existing saved UIDs are not renamed. Scoring methods retain their formulas, with presence/grade filtering determined per selected entry. Snapshot comparisons also distinguish source identities.

Native AppUpdates.java changes presentation and optional note reading. Signature verification, transfer, HTTPS, package/certificate validation and installer logic are unchanged. ImprovedActivity.java retains the approval gate and provides a measured-surface, generation-checked reveal fallback for older WebViews that fail to deliver the first compositor callback under the native loading cover.

Current targeted checks: compact_workspace_web.py, source_switch_guard_web.py, scoring_compatibility_web.py, menu_web.py, reliability_web.py, snapshot_web.py, kent_oorep_audit.py, clinical_package.py and release_tools.py. Use OR_TEST_RESULTS=test-results/v1.24. Current web assets are served on 8765; scoring comparison requires the actual published 1.23 APK web assets served on 8766.

Native checks use an isolated API 28 emulator with PIN 1234 and synthetic Apper receipts: MenuChecks, ApperChecks, StartupChecks, OwnerManagementChecks and UpdatePresentationChecks. No synthetic receipt or test class enters the APK. UpgradeChecks stages five synthetic encrypted records on the published 1.23 APK, then checks the final 1.24 replacement without uninstalling between them.

The source overlay is not the complete historical application source. Retained clinical assets can be recovered from the existing APK/workspace. See CLINICAL_UI_GUIDE_1.24.md and TEST_REPORT_1.24.md before preparing a new signed release. Older UI test scripts describe their respective older releases; the targeted set above defines the changed 1.24 behaviours.
