# Shared responsive UI overlay — prepared, NOT a released APK

This stylesheet is the same tested layout overlay used by the independent website. It fixes desktop rail overlap, header wrapping, tablet navigation clearance and compact form layout. Clinical grading styles and analysis algorithms are untouched.

Apply to the retained full Android application's web assets after liquid-glass.css. Rebuild using the original Android package/signing identity and the update-metadata signing key. Test at phone/tablet sizes, landscape, large text, dark/light, chapter selection, analysis, patient workflows, native login/lock/resume and in-place upgrade without clearing data. The website's browser tests do not constitute native APK validation.

Do NOT copy the website's initials-only storage adapter, browser privacy notice, patient-module removal, or server login into the APK: the APK retains its existing encrypted patient storage and Apper authorization. This overlay alone does not add owner web approvals to the native application.

## Owner console integration required in the Android project

The independent web owner console is https://open-repertory-production.up.railway.app/account. Its account and session are separate from the APK's Apper session. Website approvals must never be authorized solely by a client flag such as isOwner, by an email supplied by JavaScript, or by an APK approval receipt intended for a different service.

Implement an owner-only entry under the APK's existing Account & access, with two clearly labelled destinations: APK access (existing native/Apper flow) and Website access (new HTTPS console). Prefer a system Custom Tab using the shared browser session and the console's 30-day private-device option. An embedded WebView needs an explicit review of navigation allowlists, cookie persistence, SSL failure handling, JavaScript bridge exposure and back behavior. Never expose the Android storage bridge to remote website content or put owner passwords in the APK.

This first integration can retain a one-time website owner sign-in; it cannot truthfully claim automatic single sign-on. If full in-APK unified controls are required, implement a short-lived, audience-bound authorization handoff verified server-side against Apper owner identity, with replay protection, revocation, audit logging and an explicit device registration. Do not broaden member RLS or grant all APK users website owner access.

## Blockers verified 2026-11-09

The supplied Openrepertory-update repository contains APKs, update manifests, partial overlays and updater classes, but not the complete Android project or the private signing material. Its REBUILD_1.27.md also says the full retained application source and signing identity are required. The private Repertoryx repository contained only README.md at review time. The workspace search found no Gradle project or signing keystore. Apper's tool returned DAILY_LIMIT_REACHED for this turn; no Apper backend changes were made.

No APK version, update.json, prepared release or live APK approval path was changed. v1.41 remains the latest APK release. Do not promote this integration directory as an installable or verified native update.
