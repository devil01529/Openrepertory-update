# Clinical UI and English Kent source overlay

Apply this overlay over the retained full clinical application source. It is not a standalone app/database distribution. Preserve existing clinical assets, encrypted storage behaviour and both private signing keys outside the repository.

Implemented UI: `web-src/js/clinical-ui.js`, maintained app/patient/materia modules, `apk/assets/www/index.html`, `css/style.css` and `css/clinical.css`. The presentation layer reuses existing case storage, scores, visits, drafts and backups. JavaScript is compiled for Chrome 66 by build.sh. Native Back routing and account/header/theme styling are included without changes to approval policy or native signed update validation.

Added data: `kent_english_oorep.json`, `kent_oorep_audit.json`, `oorep_relationships.json`, and source-registry metadata. Key `kent1897` is OOREP's English Kent; old key `kent` still means Publicum for existing cases. Legacy Publicum clinical bytes and UID wording are retained. The source overlay includes the added datasets; the APK contains the retained old datasets.

Rebuild with `bash build.sh` and the pinned toolchain. Use `release-config.json` for version/package/channel. Restore the existing Android keystore if absent; do not generate a replacement for installed users. Prepare update metadata with the separately retained metadata key. Neither key is in this overlay.

Current tests are clinical_ui_web.py, kent_oorep_audit.py, clinical_package.py, menu_web.py, analysis_shortcut_web.py, advanced_web.py, reliability_web.py, snapshot_web.py and release_tools.py. Select `OR_TEST_RESULTS=test-results/v1.22`. Clinical UI scoring comparison uses the published 1.21 web assets served on port 8766; current assets use 8765. Native instrumentation uses only an isolated emulator with a synthetic receipt and PIN. UpgradeChecks stages synthetic old patient/case records on 1.21 before replacing the APK.

To reproduce the source audit offline, extract the Kent audit snapshot ZIP under `/workspace/scratch/oorep-live-kent` for the default test paths. The import script also uses `/workspace/scratch/oorep-live-catalogue.json` and the clinical-only GPL table extraction for Publicum aliases; these must be obtained from the official OOREP API/GPL dump if regenerating the added data. Alternatively use the added verified JSON assets directly. Never put an authenticated browser export, member table, real patient records or credentials into an audit archive.

Read CLINICAL_UI_GUIDE_1.22.md, KENT_OOREP_AUDIT_1.22.md and TEST_REPORT_1.22.md before publishing. The source audit's permission metadata records the owner's confirmation; it does not relabel the separate hosted Kent dataset as GPL.
