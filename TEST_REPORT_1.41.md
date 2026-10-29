# Validation — Open Repertory 1.41

Final validation completed 29 October 2026. Android package `com.openrepertory.app.dev`, versionCode 42, versionName 1.41-dev. Original signing identity retained.

## Passed checks

| Suite | Passed | Scope |
| --- | ---: | --- |
| APK/package isolation | 669 | Archive integrity; original signing identity and permissions; all existing web UI, repertory, grade, Materia Medica, artwork and legal assets identical to 1.40; only generated audit timestamp changes in existing clinical assets; no test fixtures or signing keys shipped. New bundled font and its licence are explicit additions. |
| Actual Android upgrade | 16 | Install 1.40, create guarded synthetic fixture, upgrade to final 1.41: encrypted records, remembered approval, imported source grades, all ranked remedies, evidence navigation, Android Back and native reload. |
| Native update UI and transfer | 32 | Final installed APK; light/dark saved themes; rounded controls; readable primary labels; percentage; duplicate-request prevention; Cancel fully visible while downloading in both themes; cancellation; long notes and return; background dismissal; solid surfaces; retained clinical bytes/approval; complete, truncated, oversized and cancelled bounded transfers. |
| Release retention | 8 | Existing safeguard tests for keeping only the latest five public releases. |

**725 checks passed.** Many are byte-for-byte asset comparisons, not independent clinical tests. Native tests ran on an Android API 28 emulator with WebView 66. Final light/dark screenshots were visually reviewed; hiding inactive download/check actions resolved the observed clipped Cancel control. The tested installed APK was pulled back and matched the release APK hash exactly.

## Safeguards retained

The existing manifest RSA verification, URL restrictions, APK SHA-256, package/version/certificate verification, bounded transfer and Android installer handoff code remain unchanged. Only native presentation and reading of existing visual preferences changed. No new Android permissions, database substitutions, analysis-score changes or approval bypass were introduced.

The update interface uses lightweight rounded gradient surfaces and bundled Noto Sans under its included SIL Open Font License. It does not implement Apple's optical renderer or capture clinical screenshots for blur. Android's own installer/permission dialogs remain system-controlled.

## Limits and source review

No physical Samsung-device performance, Android 15 gesture behavior or universal bug-free claim is made. No production login was required for the guarded emulator fixtures. Android installer handoff was not repeated in this release because its implementation is unchanged; it still requires Android confirmation.

No newly discovered repertory passed whole-book proofreading in this release. Existing clinical datasets were preserved exactly. The one-page Santee scan comparison is a sample assessment only, not a completed book audit or an independent clinician certification. See REPERTORY_SOURCE_REVIEW_1.41.md.

Final APK SHA-256: `9be52ef0524a509cf69a22cd7c09634b1946d166f25297988b1046d456710b59`
APK size: 45,077,740 bytes.
