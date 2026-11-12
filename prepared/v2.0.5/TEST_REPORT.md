# Similora 2.0.5 verification

APK SHA-256: 3cedea927848ddf75098f35fd4e1a921cb89a49e2c10952b5ff35188e2c6c515.
Package org.similora.app, code 6. Original signing identity retained; APK signature and signed-update checks passed. Installation over 2.0.4 succeeded.

Adds native rounded-square fallback PNG and API 26+ adaptive-icon resources. The launcher chooses the final adaptive mask. Matching tile is used for website favicons/touch icons. Transparent startup emblem is separate so no rectangular animation panel returns.

Browser tests passed for mobile/desktop and light/dark, full intro timing/no replay, reduced motion, no horizontal overflow, Show/Hide password and connection-error retry recovery. Source signup/owner approval/revocation checks and eight release-retention tests passed. All 661 bundled clinical/UI asset bytes match 2.0.4; clinical data and algorithms are untouched. Website deployment succeeded.

Native tests passed: adaptive icon resource loading, fresh-launch intro and same-task no replay, pause/resume, release protections, Keystore persistence/account isolation, tamper rejection, recoverable restore, remote-login bridge isolation, WebView policy and clinical startup. Live website signup/owner approval/revocation/navigation passed with zero JavaScript errors; synthetic accounts removed. Owner refresh/deletion overlap has a passing browser regression. Software emulation is functional coverage, not a measurement of physical-phone animation performance or the Samsung launcher's chosen mask.
