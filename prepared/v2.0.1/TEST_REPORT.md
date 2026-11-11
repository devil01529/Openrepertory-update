# Similora 2.0.1 verification — 11 November 2026

## Release identity

- Package org.similora.app; versionCode 2; versionName 2.0.1; min API 23, target 35.
- APK SHA-256: 1d44dc14e6db4b54c2cda5158aa3f1f48fb2cf61be3217602335ee37b205193d.
- Same certificate as 2.0.0: f115749b6b53edbf6921e1df856c8c8daf53042b2a16a1f25bc2d1727b222a7a.
- Android apksigner v1/v2/v3 verification and signed metadata public-key/hash/size/identity verification passed.

## Completed checks

- 2.0.0 → 2.0.1 in-place install succeeded on Android 9 x86 software emulation. All five pre-existing synthetic encrypted vault files had identical SHA-256 checksums after installation, before test-account activity.
- All 661 bundled assets match 2.0.0 byte-for-byte: no clinical content, grades, bundled UI, icons or clinical algorithms changed.
- Native instrumentation: release manifest protections, Android Keystore encryption, persistence, per-account isolation, tamper rejection, recoverable restore, remote-login bridge isolation, WebView policy and bundled UI startup passed.
- Live Android HTTPS session: migrated account identity matched, approved member entered the bundled clinical workspace, member access to owner API denied; revoked member blocked in a separate live native check.
- Live Cloudflare browser: existing owner login, synthetic signup/pending gate, owner approval, automatic workspace entry, secure HttpOnly 30-day cookie, initials-only case form, repertory/library/analysis navigation, reduced-motion mode, revocation, deleted test session invalidation; zero JavaScript errors observed.
- Anonymous owner API denied (403); anonymous clinical JavaScript denied (401); sealed migration endpoint returned 410.
- Older APK compatibility API uses Cloudflare account IDs/status. Old-origin browser workspace remains accessible with new authority checks. Temporary encrypted account export is disabled (404).
- Source tests: Cloudflare authentication/approval/CSRF/rate-limit/password/session tests; case-policy tests; standalone authentication tests; signed/encrypted export rejection tests; compatibility relay origin/body/redirect/failure tests.
- Branding check and eight retention-policy tests passed.
- Cloudflare production deployment workflow succeeded and public /healthz reports ready.

## Limits

Android 9 software emulation is functional validation, not physical-phone performance testing. Its system UI can stall under software graphics; no smoothness claim is made from this emulator. A physical-device installer interaction remains owner-controlled. Online Gemini inference was not exercised with a real API key; its native code and clinical assets are unchanged. No new clinical proofreading or repertory content import is included. Tests reduce risk; they do not prove an absence of every bug.

Website cases remain browser-local and do not automatically move between origins. Native records remain account-scoped and encrypted locally. The free service is subject to Cloudflare quotas. Private signing remains outside CI; website deployment and prepared-release publication are automated.
