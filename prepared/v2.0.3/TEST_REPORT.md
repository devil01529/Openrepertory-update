# Similora 2.0.3 startup correction

APK SHA-256: c444f778e39fb92986128e2a435905ed88cb3b461837d8ce1beeda9aa157a895.
Package org.similora.app, code 4, original Similora signing certificate retained.

The footage's outer edges are feathered into the exact screen background, and the inset layout/controls during playback are removed. A completion gate waits for both actual media completion and actual workspace readiness. Slow initialization shows loading controls after the one-shot intro. Authentication is checked independently. Background playback pauses; resume restores pending navigation.

Verified: source compilation, APK v1/v2/v3 signing, signed update metadata identity/hash/size, all 661 clinical/UI assets unchanged, browser full-duration gating on mobile and desktop with fast and slow initialization, reduced-motion fallback, authentication/case-policy source tests, live website signup/owner approval/revocation/navigation and eight retention tests. Live tests accept both 401 (signed-out session) and 403 (revoked signed-in account) as access denial. Synthetic test accounts were removed.

Android installation over 2.0.2 succeeded. Native tests passed: complete one-shot playback, pause/resume, loading controls after completion, media disposal, manifest protections, Keystore encryption, persistence, account isolation, tamper rejection, recoverable restore, remote bridge isolation, WebView policy and bundled clinical startup.

Software emulation verifies behavior, not physical-phone smoothness. Unsupported media playback and system reduced-motion use the fallback. Clinical data, account identities and encryption format are retained.
