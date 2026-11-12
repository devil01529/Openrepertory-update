# Similora 2.0.8 API error classification

APK SHA-256: 7ec76e5138b44344c482ee6379bf8132cb07c6195033b1408c3bc0ab658f7298.
Package org.similora.app, code 9; original signing identity retained. Signature/signed-metadata checks and eight retention tests passed. All 580 clinical data files match 2.0.7.

Native targeted instrumentation passed for insufficient_quota, billing_hard_limit_reached, usage_limit_reached, quota in error.type, temporary 429, malformed response body, HTTP 402/401/500 distinctions, safe actionable messages and no 429 model retry. The transport reads only a bounded error body and maps known codes; raw provider message text is not returned.

Browser fixtures passed: personal provider setup, reviewed-text gate, local lookup with no automatic case additions, separate quota and rate-error fallback, cancellation, disconnect and no key in localStorage. In-place Android installation passed. This release targets error reporting; full native storage/startup regressions were previously passed in 2.0.7 and are not claimed as rerun here.

No real user key or API billing account was available. These tests cannot determine the user's balance or increase quota. Test saved connection after updating to see the provider's current response; resolve provider account restrictions with the provider. Emulator coverage is functional, not physical-device performance measurement.
