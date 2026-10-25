# Open Repertory 1.34 validation

Owner-approved chapter artwork is integrated for all 137 chapters: Boericke 25, BBCR 61 and Allen TPB 51. Repeated source notes open through the information button. Clinical data and analysis scoring are unchanged.

Completed automated checks:

| Area | Passed checks |
|---|---:|
| Approved artwork mapping, packaged originals and navigation | 142 |
| Clinical UI and scoring compatibility | 110 |
| Materia Medica reader and notes | 190 |
| Mixed-source selection identity | 24 |
| Source information on demand | 5 |
| Portal access/redirect/query security fixtures | 34 |
| Live anonymous access and authentication UI | 11 |
| APK integrity, clinical assets, signing identity and permissions | 8 |
| Latest-five retention safeguards | 8 |

All six required-field changes were confirmed in the live Apper schema. Existing row-level policies were re-read and unchanged; anonymous reads of requests, grants and audit data were denied. An independent review covered access logic and release cleanup. Cleanup preserves drafts and future staged versions and deletes obsolete releases oldest first.

Android checks also passed: **39** native access checks, **16** classical content/artwork checks and **16** session checks on API 28 with WebView 66. The real install-over-update from published 1.33 to 1.34 preserved five encrypted records and installation identity. The installed APK matched the tested/prepared bytes exactly.

**603 counted checks passed.** Native fixtures are emulator-only and excluded from the APK.

Scope: authenticated personal accounts were not used for cross-account attacks. Security testing combines live anonymous checks, policy/code review, synthetic adversarial fixtures and native checks. It is not a guarantee of zero vulnerabilities. Existing offline approval lasts at most 24 hours in the same boot; immediate offline revocation is not possible. Patient records remain encrypted on-device.
