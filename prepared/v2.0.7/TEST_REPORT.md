# Similora 2.0.7 automatic AI setup

APK SHA-256: dc37540641ce6fd4c869830e5007844d3625ddfe89f127036decc20519de39f9.
Package org.similora.app, code 8; original certificate retained. APK signature and signed-update checks passed. All 580 clinical data files match 2.0.6.

Browser fixture tests passed with the model-name field absent: provider/key setup, consent before sending, local rubric search, no automatic case additions, quota fallback, cancellation and disconnect. Eight release-retention tests passed.

Native tests verify automatic defaults when no model is supplied, fallback candidates, retry-on-404 only, key storage/redaction and the existing provider JSON/security/startup checks. Native execution passed: automatic defaults, fallback candidate list, retry-on-404-only policy, encrypted config/key redaction/retention/disconnect, provider fixtures, fixed-host/model validation, manifest protections, adaptive icon, Keystore isolation/tamper recovery, remote bridge isolation, WebView policy and clinical startup. Intro launch-state tests passed.

Model selection is handled internally; provider APIs still require model IDs in requests. This release uses maintained default/fallback candidates, not arbitrary model discovery. No real user key was available for live API validation. The in-app connection test verifies actual account access, quota and availability. Software-emulator coverage does not measure physical-device performance.
