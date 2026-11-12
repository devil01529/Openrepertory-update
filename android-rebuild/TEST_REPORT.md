For the current release see [2.0.4 verification](../prepared/v2.0.4/TEST_REPORT.md). The report below records the original 2.0.0 baseline.

# Similora 2.0.0 validation

## Artifact

- Package: `org.similora.app`, version 2.0.0 (code 1), Android API 23+ / target 35.
- APK SHA-256: `aef4d515ad121266e9086ea41cf28aeacdf6362c2051fb2b7211b763d5b35d4e`.
- Signing certificate SHA-256: `f115749b6b53edbf6921e1df856c8c8daf53042b2a16a1f25bc2d1727b222a7a`.
- Build completed from the committed Java/resources/scripts and recovered clinical assets. Android apksigner verified v1/v2/v3 signatures. Signed update metadata verified against its committed public key.
- No session fixture, private signing material or branding-backup files in the production APK.

## Passed checks

- All **580 clinical data files** match the verified v1.41 baseline byte-for-byte.
- Packaged clinical browser smoke: eight book workflows loaded, each accepted a selected rubric and produced nonempty ranking; eight selections survived reload. Aconitum Materia Medica profile loaded 9,887 characters. Offline symptom finder completed without an API key. No JavaScript errors.
- Branding, asset recovery and release-retention regression suites: 13 tests passed.
- Android 9 native instrumentation: release manifest disables debugging, backups and cleartext; actual Android Keystore encrypted persistence and per-account isolation; plaintext sentinel absent from vault; altered ciphertext rejected; ordinary writes cannot overwrite damaged storage; explicit restore retains the damaged encrypted file.
- Native remote account WebView has no clinical JavaScript bridge and disables file/content access and mixed content. Bundled clinical UI starts with Similora branding.
- Live native HTTPS test with a synthetic approved website account: server identity matched, member access to owner endpoint returned 403, and production MainActivity entered the bundled clinical workspace.
- Live native revocation: after the owner removed approval, the same website session was blocked from the clinical workspace. The synthetic native account was then removed.
- Live website regression: independent signup/login, owner approval, automatic workspace entry, Secure/HttpOnly cookie, 30-day remembered session, case form, repertory/library/analysis navigation, reduced motion, member owner-API denial and revocation. Synthetic website test account was removed; zero JavaScript errors.

## Test environment and limits

Android checks ran on an Android 9 x86 software emulator with its bundled older WebView, not on a physical phone. The cloud proxy CA was trusted **in the emulator only** to permit the environment's HTTPS inspection; production source does not bypass certificate validation or add that CA. Emulator networking needed repair before the live native test could run. These runs do not establish phone performance or compatibility across every supported Android version.

No live Gemini credential was supplied. Online Gemini, every printer/document provider, full old-native-to-new-native backup migration, accessibility across all font sizes, and every background/process-death transition are not exhaustively device-certified. Their interfaces are implemented and existing clinical workflows retained; owner should verify a non-sensitive sample backup on the intended phone before migrating real records. This is functional/security regression coverage, not a claim of zero vulnerabilities or a clinical-content audit.

This is a new signing lineage, not an in-place upgrade. Legacy v1.41 and its update manifest remain separate. Private signing keys must be backed up by the owner; public GitHub cannot hold them. Automated signing secrets are not configured because the connector denied that operation.
