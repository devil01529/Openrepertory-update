# 1.26 implementation and verification plan

1. Restore existing chapter artwork without changing source IDs, chapter order or rubric counts. Use compact square thumbnails and the existing chapter-hero image.
2. Make the whole drawer scroll, including book/source/filter controls; retain filtering and keyboard chapter selection. Avoid nested chapter-list scroll areas.
3. Add a dedicated Repertory & reading menu section. Move remedies, branches, patient words and density/privacy controls there. Add persistent 80–140% text sizing with smaller/larger/reset. Apply native WebView text zoom to all Android text and use root-font scaling in the browser preview.
4. Correct analysis explanation and heading colors using the actual light/dark theme variables. Check text contrast and ensure clinical values and grades remain untouched.
5. Preserve warm, unlocked-device sessions. Remove the elapsed-background-time relock, refresh owner approval quietly, and keep the same document/page/scroll/case. Observe actual Android device-lock state with screen/user-present broadcasts plus delayed checks while the screen is off. Lock the bridge and hide the clinical surface when keyguard locks; allow a previously authenticated surviving session to resume after Android device unlock and approval revalidation. Retain cold-start authentication and all denial/revocation checks.
6. Test responsive and large-text UI, full-drawer scrolling, source/scoring integrity, native text zoom, aged-background return, actual keyguard lock/PIN unlock, startup recovery, owner controls and encrypted-data retention. Build under the same package/certificate with a higher version code. Publish a new immutable signed release only after checks pass; verify its public updater metadata and APK bytes after publication.

This is a UI/session update. It does not change remedy grades, clinical source content or analysis formulas. Runtime state survives a warm background return; Android process termination still requires cold initialization.

Implementation and automated verification are complete. Public download verification is recorded separately after publication.
