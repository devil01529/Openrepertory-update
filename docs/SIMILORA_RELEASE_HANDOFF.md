# Similora Android release handoff — 9 November 2026

> Historical handoff: the original in-place signing blocker below still applies to the legacy package. The user subsequently authorized a new independent APK with website accounts. See [the completed rebuild](../android-rebuild/README.md) and its [test report](../android-rebuild/TEST_REPORT.md) for the current implementation.

## Historical release status

**New signed APK: blocked, not published.** Latest installable version remains v1.41 (versionCode 42). Similora web branding, logo and available native updater text have been implemented and committed. Do not label v1.41 as a new Similora APK or publish a differently signed substitute as an in-place update.

Canonical repositories:
- Android: https://github.com/devil01529/similora-update
- Website: https://github.com/devil01529/similora-website

The independent website is live at https://open-repertory-production.up.railway.app. Changing its origin would require a deliberate migration of browser-local cases.

## Required feature baseline

Use **v1.41**, not uploaded v1.11, as the functional baseline. The older APK lacks Apper access and the native updater.

| Capability | Current evidence | Required before new APK release |
|---|---|---|
| Apper login, approvals, owner controls | v1.41 compiled classes retained; no native auth replacement in branding work | Real member/owner test, pending/approved/revoked states, unauthorized owner actions denied server-side |
| Session persistence and device lock | Existing native implementation remains the baseline | Background/foreground, process restart, lock/unlock, offline and revoked-session tests |
| Encrypted records and AI key storage | Static references to AndroidKeyStore and AES/GCM/NoPadding | Verify ciphertext, wrong-key/corrupt-file handling, no plaintext logs/backups, upgrade persistence |
| Repertory browsing and analysis | Rebranded recovered web assets passed all eight bundled book workflows | Repeat on supported Android WebViews and after upgrade |
| Materia medica, offline finder | Browser smoke tests passed | Repeat on device; live Gemini needs its configured API credential and a separate test |
| Saved cases and patient records | Browser reload selection test passed; underlying data/storage keys unchanged | Encrypted native upgrade, patient/case editing, backup and restore tests |
| Glass UI, reading controls, chapters | Existing clinical assets retained; Similora name/logo applied | Check light/dark, text scaling, rotation, keyboard, accessibility and reduced motion on device |
| Android launcher and native login logo/name | Master logo and integration instructions prepared | Apply to full native project; check adaptive icon masks, launcher, login and update dialogs |
| Signed updater and installation | Current v1.41 signed metadata/hash verification passed; old URL still resolves after repo rename | Invalid signature/hash/certificate rejected; interrupted download recovery; in-place signed upgrade |
| Website approvals inside APK | Website owner console exists; APK bridge remains a separate unfinished task | Do not claim unified owner management until secure native integration and authorization tests pass |

The feature matrix distinguishes present behavior from requested but unfinished cross-platform work. It is not a blanket claim of feature parity or security certification.

## Static mobile security inspection

`security/STATIC_SECURITY_1.41.json` records manifest and relevant compiled-code evidence from the current released APK:
- debuggable=false; allowBackup=false; fullBackupContent=false; usesCleartextTraffic=false.
- One exported launcher activity; updater content provider explicitly non-exported with temporary URI grants.
- Clinical WebView setup disables web debugging, file/content access and cross-file access; calls mixed-content restriction and Safe Browsing settings.
- AndroidKeyStore/AES-GCM usage appears in record/access/key storage. Clinical blocking removes native JavaScript interfaces.
- Screen-capture protection has configurable enable/disable paths; preserve the user's existing screenshot preference rather than force a new blanket restriction.

This inspection does not establish hardware-backed key strength, runtime authorization correctness, absence of all vulnerabilities, or backend security. Native runtime checks remain unexecuted because the complete build/signing environment and Android runtime are unavailable here.

## Preserved artifacts and reproduction

- `integration/similora/similora-mark-v1.png`: generated product logo master.
- `integration/similora/apply_branding.py`: repeatable presentation-only patch; original backups preserved. Move generated before-similora backups outside packaged assets.
- `tests/test_similora_branding.py`: idempotence, logo, source-data and storage-format safeguards.
- `android-updater/`: maintained partial native updater sources with Similora presentation text; package and verification identity preserved.
- `scripts/recover_app_assets.py`: recover locally from verified prepared v1.41; never add private clinical datasets to public GitHub.
- `tests/browser/packaged_smoke.py`: clinical browser coverage (not native Android security).
- `docs/SIMILORA_ANDROID_STATUS.md`: completed rebrand scope and test evidence.
- `docs/UPLOADED_1.11_RECOVERY_CHECK.md`: old APK comparison and matching certificate evidence.
- `release-config.json`, `release-public-key.pem`: current public verification identities, not private signing keys.

All 580 clinical data files in the rebranded working assets remain byte-identical to v1.41. No historical data or required author/license notice was overwritten by branding.

## Exact release blocker and next steps

The original complete native project/build scripts, Android signing keystore access, and update-metadata signing setup must be restored through an owner-controlled private build environment. The uploaded v1.11 and released v1.41 share certificate SHA-256 `cf7d95c0a728d1c03ffd71dff534eea378f8c7efd6f035b0724a8c66d9c4621a`, but neither APK contains the private key. No replacement production key has been generated. Repository Actions-secret listing was denied (403), so their presence cannot be established from this session.

Once restored: apply branding to current full source, keep package `com.openrepertory.app.dev` and all storage identities, update native app label/icons, use a versionCode greater than 42, execute the matrix above, sign with the original key, generate signed update metadata and verify independently, then publish and retain only the latest five releases. Preserve the legacy signed repository identity/URLs until a tested signed updater migration supports the renamed repository.

Do not upload keys/passwords to public GitHub or paste them in chat. Provide the private build project or existing signing-environment location instead.
