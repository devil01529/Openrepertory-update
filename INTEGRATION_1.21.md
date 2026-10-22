# Integrating the 1.21 source overlay

This archive is an overlay for the existing Open Repertory Android application, not the complete clinical database or a standalone build. Retain the existing data assets, Android signing key, metadata signing key and approved-access configuration.

The implemented UI files are `apk/assets/www/index.html`, `apk/assets/www/css/style.css` and maintained `web-src/js/app.js`. The custom build compiles maintained JavaScript into `apk/assets/www/js`. The floating Analysis button uses the existing active case, renderAnalysis lifecycle and showPage("analysis") route. Case changes, removals and reloads reuse existing persistence and scoring; no database migration is needed.

`release-config.json` sets Android versionCode 22 and versionName 1.21-dev. Build using `bash build.sh` with the existing toolchain and signing key. Then run the shortcut, menu, package, existing regression and release-tool tests. Tests may select `OR_TEST_RESULTS=test-results/v1.21`; native instrumentation is restricted to a disposable emulator and is not shipped in the APK.

The detailed future redesign and visual concept are design deliverables, not app screens in this release. They do not import Synthesis content.

Prepare release metadata using scripts/prepare_release.py with the separately held existing metadata key. Publish through the prepared-release GitHub Actions workflow after verification. Do not include either private key in the archive or repository.
