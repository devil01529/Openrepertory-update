# Open Repertory 1.27 source overlay

Apply this maintained overlay over the retained full application source. Package `com.openrepertory.app.dev`, versionCode 28, versionName 1.27-dev. Keep the existing Android keystore and metadata signing key private; they are not included.

## Changed production surfaces
- `apk/assets/www/css/liquid-glass.css`: an additive visual layer after the unchanged clinical/base styles, with light/dark/auto materials, opaque fallback, progressive blur on only three finite chrome surfaces, solid mode, reduced-motion/transparency/contrast and print handling. Includes long-name wrapping and short-screen context compaction; full identity strings remain stored and available.
- `apk/assets/www/index.html`: loads that stylesheet and adds the `liquid-glass` body class.
- `web-src/js/clinical-ui.js`: persists the Glass effects checkbox in the existing encrypted UI preferences and refreshes stale patient/case context labels immediately after either exit from Presentation mode.
- `release-config.json` and the packaged Android manifest: version increment only. All production native classes are byte-identical to 1.26. Native authentication, storage and updater implementation are retained.

The original clinical styles, grade typography, data assets and every clinical/search/patient/backup/lifecycle JS module outside clinical-ui.js remain byte-identical to 1.26. The generated build audit report differs only in its timestamp. No new permission or remote dependency. This overlay is not the complete historical application source; existing clinical/text/art assets remain in the retained workspace/APK.

## Rebuild and verify
Use `bash build.sh` with the pinned toolchain in `TOOLS.lock.json` and the retained signing identity. Serve `apk/assets/www` on 8765. Scoring compatibility uses actual published 1.23 assets on 8766. Set `OR_TEST_RESULTS=test-results/v1.27` for the current browser/package suites.

New independent tests: `tests/liquid_glass_web.py`, `tests/liquid_glass_review.py`, `tests/liquid_glass_long_names.py` and emulator-only `GlassChecks` / `ShortContextChecks`. Existing reading/navigation/compact/source/scoring/menu/recovery/snapshot/Kent/package/release-tool suites remain relevant. Native SessionChecks, StartupChecks, MenuChecks, ApperChecks and OwnerManagementChecks exercise the final APK on isolated Android API 28 / WebView 66 with synthetic receipts. UpgradeChecks prepares retained encrypted data on published 1.26, installs final 1.27 without clearing storage, then verifies exact encrypted bytes and device identity. Test fixtures do not ship in the app.

See `LIQUID_GLASS_PLAN_1.27.md`, `CLINICAL_UI_GUIDE_1.27.md` and `TEST_REPORT_1.27.md` for scope and actual evidence. The separate electronic Kent audit is retained from the prior release; this UI update does not claim a new live scrape or printed-book certification.
