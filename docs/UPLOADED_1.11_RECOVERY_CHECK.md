# Uploaded v1.11 APK recovery check — 9 November 2026

The owner supplied `Open-Repertory-1.11.apk` as an older recovery reference. Current Similora changes and released v1.41 are retained; this upload was not published or installed over them.

## Identity and integrity

- Uploaded bytes: 23,910,491; SHA-256: `90a31599724140a8e2e7db772a5014ab54c2c46729263e59ab0d7f5e6e00138e`.
- ZIP entry integrity check passed (764 entries); recovered 751 asset files locally.
- Package: `com.openrepertory.app.dev`, version name `1.11-dev`, version code 12, launcher label `Open Repertory Dev`.
- Minimum Android SDK 23; target SDK 35. Manifest says debuggable=false and allowBackup=false.
- Certificate SHA-256: `cf7d95c0a728d1c03ffd71dff534eea378f8c7efd6f035b0724a8c66d9c4621a`, matching the certificate extracted from v1.41 and the current release configuration. Certificate comparison is not a fresh cryptographic verification of every APK signature scheme.
- Native classes include ImprovedActivity, MainActivity, RecordStore and GeminiClient. The later ApperAccess/AppUpdates classes present in v1.41 are absent, consistent with this predating the approval/updater additions.

## Browser checks on recovered web assets

Chromium tests at 412×915 passed with no JavaScript errors:

| Bundled ID | Chapters | Loaded / selected rubric / nonempty analysis |
|---|---:|---|
| kent (legacy Publicum) | 41 | Passed |
| clarke | 3 | Passed |
| bbcr | 57 | Passed |
| knerr1897 | 48 | Passed |
| tpb1846 | 6 | Passed |
| boger1915 | 1 | Passed |

Materia medica Aconitum profile opened (9,928 displayed characters). The selected rubric persisted after reload. A version-specific startup predicate was used because v1.11 does not expose the later workspaceReady flag.

These tests cover recovered HTML/JavaScript behavior, not installation or execution on an Android device. No emulator/ADB runtime is available here; native biometrics, encryption, permissions, Gemini connectivity and upgrade installation were not verified. The chapter counts are observations of bundled data, not completeness/proofreading claims.

## Recovery value and limits

The upload provides an additional matching-lineage binary and recoverable assets/compiled code. It contains no maintained Java/Gradle project or signing keystore. It cannot supply the original private signing key. Restoring a signed upgrade still needs the original signing environment; decompilation alone cannot recover that key.

Use v1.41 as the current behavior baseline, preserving its access control and updater. The uploaded pre-login APK is a recovery reference only; replacing the current app with it would regress those features. Similora website branding and Android branding/updater source changes on main remain intact. No private clinical datasets or uploaded APK bytes were added to GitHub by this check.
