# Similora 2.0.6 — Your AI validation

APK SHA-256: 6271528068a8a4d83c9f00356750bab33e5f3acc8df9322b91c61de73ccbb3be.
Package org.similora.app, code 7; original signing identity retained. APK signature, signed metadata and eight retention tests passed. All 580 clinical data files match 2.0.5 byte-for-byte.

Browser fixture tests passed: own-provider configuration, review gate with zero requests before confirmation, concepts matched through local rubric lookup, no automatic case additions, quota failure fallback, visible Stop search cancellation, disconnect and no key in localStorage. UI and finder source parse checks passed. Fixtures never use a real provider key or send clinical text externally.

Native Android tests cover encrypted key configuration, public config key redaction, blocked generic key reads, retaining a saved key while changing model, disconnect, provider JSON request/response fixtures, fixed hosts, malformed model/response rejection and the existing storage/security/clinical startup regressions. Native execution passed all listed AI storage/provider fixtures plus manifest protections, adaptive icon, Keystore account isolation/tamper recovery, remote bridge isolation, WebView policy and clinical startup. Launch-state tests also passed. The WebView null-value compatibility check is backed by a direct native assertion that generic secret reads return null.

Live Gemini/OpenAI authentication, billing entitlement, model availability and generated clinical interpretations have not been tested with a real key. Users must use Test saved connection for their own account before use. Software emulation is functional validation, not physical-phone performance measurement. AI remains a clinician-reviewed search aid, not an automatic prescription or database importer.
