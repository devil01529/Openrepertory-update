# Similora 2.0.2 — startup animation verification

12 November 2026. Package org.similora.app; versionCode 3; min API 23, target 35.

APK SHA-256: 34e5993e2317246d67f5bcf4f36c545788a47fe36ff91c83bd6a26e1639949ec.
Signing certificate unchanged: f115749b6b53edbf6921e1df856c8c8daf53042b2a16a1f25bc2d1727b222a7a.

## Verified

- Compiled from source; Android v1/v2/v3 signature verification and signed-update payload/hash/size/identity checks passed.
- In-place install over 2.0.1 succeeded on Android 9 x86 software emulation.
- Native regression checks passed: release protections, Keystore encryption, persistence, account isolation, tamper rejection, recoverable restore, remote bridge isolation, WebView policy and clinical startup.
- Native StartupChecks passed: bundled video playback, pause, fallback visibility, resume and disposal.
- All 661 bundled clinical/UI assets match 2.0.1 byte-for-byte. The added media are Android resources. No clinical data, scoring, permission or authentication-provider changes.
- Clip: H.264 baseline, 480 × 720, no audio stream, fast-start metadata, approximately 858 KiB. Watermark region cropped below retained artwork. APK/website use the same derivative.
- Browser tests passed at 390 × 844 and 1366 × 768: silent playback, viewport fit, Skip, reduced-motion static mode, video-request failure, privacy consent gate, readiness completion and media cleanup.
- Live production HTTPS media playback and Skip passed under the deployed Content Security Policy.
- Website source authentication, approval, revocation and case-policy regressions passed. Cloudflare deployment succeeded.
- Live website login/signup/owner approval/revocation/clinical-navigation regression passed with zero JavaScript errors observed; synthetic account removed afterward.
- Eight release-retention tests passed.

## Limits

The software emulator verifies behavior, not physical-phone animation performance. Unsupported media players use the still-image fallback. Slow network/device loading is not hidden indefinitely: existing retry/error paths remain. The animation neither bypasses approval nor forces a ten-second delay. No new clinical data audit or live Gemini API test is part of this release.
