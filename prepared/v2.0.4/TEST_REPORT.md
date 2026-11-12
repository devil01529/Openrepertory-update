# Similora 2.0.4 verification

APK SHA-256: f5d94be9166c119b4130f755cf6c051f9cab09a9b01e00c76188202bc4a2d827.
Package org.similora.app; code 5; original signing identity retained.

The video has been removed from APK resources and website startup. A transparent PNG recreation of the reference emblem is animated using native Android properties/Web Animations on a full-screen gradient. There are no sparkle/particle layers or video panel edges. Launcher and app/account branding share the emblem.

Native launch-state tests passed: full intro/readiness gate, pause/resume, same-task no replay, new-task reset. Install over 2.0.3 passed. Browser tests passed at mobile/desktop sizes and light/dark themes: transparent logo, no video element, full duration, per-tab no replay, reduced-motion fallback, rounded glass login and no horizontal overflow.

Only assets/www/img/brandmark.png changed among the bundled clinical/UI assets. All database and clinical script bytes are retained. Native signature v1/v2/v3 and signed update metadata checks passed. Eight release-retention tests passed. Source authentication and approval tests passed.

Software emulation is functional coverage, not a physical-device performance measurement. A new Android task/process plays the intro; normal background return preserves completed state. No credentials, records, private signing keys or test fixtures are published. Release cleanup keeps five assets sets while reserving the legacy compatibility endpoint.

Final native regression passed: manifest protections, Keystore encryption, persistence/account isolation, tamper rejection, recoverable restore, remote-login bridge isolation, WebView policy and bundled clinical startup. Live website signup/owner approval/revocation/navigation passed with zero JavaScript errors observed; synthetic test accounts removed.
