# Open Repertory 1.37 validation

The release improves clinical source review and recoverable case editing. **Synthesis and Complete Repertory are not bundled.** Their official products and documentation were researched, but no authorized full structured export or edition-matched original reference dataset was obtained. No full proofreading, coverage audit or successful integration of either work is claimed. See SYNTHESIS_COMPLETE_RESEARCH_1.37.md for the findings and required source audit.

## Results

| Area | Passed checks |
|---|---:|
| Exact source navigation, Undo, save failures, source/page races and preferred views | 38 |
| Composited evidence contrast, enlarged text, touch targets and private recovery notices | 87 |
| Clinical UI, patient records, rubric controls and scoring compatibility | 110 |
| Glass UI, themes, readable analysis, solid fallback, privacy and motion | 323 |
| Repertory-only reading panel, text size, focus and navigation | 200 |
| Source labels, book identities, raw provenance and legal credits | 58 |
| Mixed-book selection identity | 24 |
| Materia Medica text, author labels, original licences and notes | 32 |
| Historical removed-book evidence, snapshots, exports and backups | 62 |
| Production approval/receipt rules on the host JVM | 85 |
| Final APK, retained assets, unchanged scoring/access code and package identity | 652 |
| Actual installed Android 1.36 → 1.37 upgrade and clinical workflows | 17 |
| Release-retention safeguards | 8 |
| **Total** | **1,696** |

## Findings and changes

The published 1.36 app was used to reproduce two defects: a Clear all confirmation remained armed after switching cases, and the evidence Source action could search the currently open book rather than the saved rubric's original source. The original-record importance-retry behavior was also investigated and found to preserve its data; this release improves save-error feedback and refreshes controls without claiming that the old retry lost the record.

Evidence now names each book, shows the recorded grade separately from case importance, opens the exact saved path, selects the actual chapter and closes the phone drawer. Back to analysis and Android Back restore the prior analysis view. A failed or superseded source request leaves the saved case available and respects a newer book/page choice. Reduced-motion source navigation avoids forced animated scrolling.

Undo covers the most recent removal or confirmed clear for ten seconds. Checks cover original order, full provenance, grade/weight preservation, multiple books sharing one path, a case switch, a deleted case, explicit re-addition and failed saves with retry. Clear confirmation resets with case/selection rerendering. The preferred analysis view persists without resetting text size, density, remedy visibility or glass preferences.

Readability was measured from rendered screenshots, including grade badges 1–5 in both themes and at 140% text. Source buttons and Undo have at least 44px targets; recovery notices fit above phone navigation. Tests used 320px and 393px phones, 640px landscape and 1280px desktop layouts. The general clinical/glass regression additionally covers tablet layouts. Presentation mode exposes a generic recovery action while retaining the opaque patient shield and hiding ordinary potentially private notices.

## Content and package integrity

The seven repertory databases, Materia Medica content, remedy dictionaries, chapter art and legal notices match published 1.36 assets byte for byte. The generated audit report differs only in its build timestamp. The scoring implementation matches the maintained 1.36 source exactly. The production remembered-approval implementation also matches the source verified for 1.36; no backend permission or login-policy change was made.

The package remains `com.openrepertory.app.dev`, version code **38**, version name **1.37-dev**, with the existing signing certificate and Android permissions. The tested maintained JavaScript matches the packaged Chrome-66-compatible code. No source archives, private keys, PDF readers/scans, test credentials or emulator authentication override are in the APK.

Tested APK SHA-256: `03b368e183755e76118c078d4f6eee245a6ee4376290786938f991b1262e0db9`.

## Android test scope

The exact final APK was installed over the actual published 1.36 APK on API 28 / WebView 66. Encrypted record bytes were identical before first launch; the installation fingerprint and previously remembered approval were retained. The installed source review, chapter/drawer state, Undo across case switches, native encrypted writes, Android Back and saved-view reload all passed. A pulled installed APK matched the prepared release byte for byte.

These tests use synthetic approval and case fixtures with an emulator-only authenticated test harness. The harness is absent from the release APK. The normal Android PIN dialog and a real owner/member Apper sign-in were not retested in this UI release. Existing remembered-access limits and the server's permission checks are unchanged. No claim of complete printed-book proofreading or real clinical validation follows from these software checks.

The signed public update and downloaded APK are verified again after publication. The existing workflow retains the latest five published releases, removing the oldest first. No ZIP release asset is published.
