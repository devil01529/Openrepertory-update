# Open Repertory 1.19 — signed free APK updates

Package `com.openrepertory.app.dev`; Android version code 20; version name `1.19-dev`.

APK SHA-256: `cab589618dbf904e1539104d88ea47eee374ac980d6bfec51f19309218671097`

APK size: 23,939,163 bytes. The existing Android signing identity is retained.

## Implemented

Optional native update screen; automatic checks after workspace readiness, at most once per day; manual checks; user-initiated downloads with progress, cancellation and retry; independently signed metadata; HTTPS destination restrictions; APK hash/size/package/version/certificate/Android checks; and a read-only scoped installer provider. Android retains installation confirmation. Updates cannot bypass approval or Android unlock.

The build uses one release configuration and stops if the existing Android signing key is missing. GitHub contains prepared public files, updater modules, a source overlay, owner instructions and a manual free publication workflow. Both private signing keys remain outside the repository/APK/source archive. The development-labelled Android certificate is retained for compatibility; independent metadata signing adds an updater verification boundary.

## Automated verification: 143 checks passed

| Suite | Passed | Coverage |
| --- | ---: | --- |
| Native updater | 36 | RSA signatures; altered metadata; wrong key/destination/identity; actual signed APK parsing; downgrade rejection; complete/truncated/oversized/cancelled/interrupted downloads; provider restrictions |
| Native update UI | 12 | Menu/version, retained document, verified cached download, actual Android installer handoff/cancellation/return, retry download retained, revoked bridge denied |
| Native Apper regression | 33 | Existing approval validation, encrypted cache, source typography/search/analysis, revocation/reapproval, account isolation |
| Native startup regression | 13 | Loading cover, first frame, warm return/re-unlock and denial recovery |
| Native owner management | 14 | Owner entry, retained document, refresh/return, Member denial, role loss and revocation |
| Package | 12 | Identity/version/certificate, installed/delivery hash, source isolation, installation permission, unchanged clinical data |
| Release preparation | 8 | Valid release/signature/URL, public-only output; incorrect signer/version/key/tag/notes rejected; missing Android key stops build |
| Browser UI | 15 | 320/393/768px menu, browser fallback, no overflow, Clarke source italics/search, add-to-analysis, no uncaught JavaScript errors |

An additional actual in-place installation from 1.18 to 1.19 retained all 31 encrypted emulator records and the complete installation fingerprint. All 690 clinical database/text assets retain identical bytes. Web changes are the update-menu entry/handler and regenerated audit timestamps.

The higher-version installer APK fixture is never installed or published. The final harness waits for Android's confirmation, clicks Cancel, verifies return and confirms the current app/download remain. An initial harness attempt returned before the installer transition completed; the final corrected suite passes. Test-only keys/receipts/classes are excluded from the delivered APK.

## Publication

Successful GitHub-hosted verification/upload runs:
- https://github.com/devil01529/Openrepertory-update/actions/runs/39746971770
- https://github.com/devil01529/Openrepertory-update/actions/runs/39747412637

The standard Linux workflow verifies the independent manifest signature and APK hash before upload. Direct uploads from this workspace returned HTTP 401, so GitHub's own short-lived repository token is used in the hosted workflow. No publishing credential is embedded in the app. The second upload also includes the owner guide and source overlay. The release is public at https://github.com/devil01529/Openrepertory-update/releases/tag/v1.19. Anonymous HTTPS checks passed for the latest manifest, its pinned RSA signature, the complete 23,939,163-byte APK, byte identity with the tested APK and the downloadable owner guide. These requests used no GitHub login/token. Details are in `test-results/v1.19/public-channel-tests.json`.

After the successful suites, the managed execution connection restarted. A supplementary Android live-channel attempt initially could not run because the restarted emulator lacked its WebView provider and its System UI reported missing Wi-Fi services. This did not overwrite or invalidate the completed 143-check results; public channel downloads were independently verified from the execution host. A network-only Android attempt then stopped before its first assertion with `UnknownHostException` resolving github.com: the restarted emulator has no working DNS/network service. This supplementary Android live-download check is ENVIRONMENT-BLOCKED and is not counted among the 143 passed checks. Native integrity/transfer/UI/installer paths passed before the environment restart, and anonymous live file delivery passed from the execution host. Live download and complete installation on a modern physical phone remain acceptance checks.

## Limits and phone acceptance

Installed tests use Android 9/API 28. Apper receipt fixtures test production validators, not a new private real-account login/approval decision on a physical phone. The user previously confirmed live login. This release changes no Apper backend code, tables or permissions.

A modern physical Android/Samsung phone must still confirm installation-permission/Play Protect prompts, real-account upgrade and device performance. Native installer handoff/cancellation and actual 1.18-to-1.19 encrypted-data retention are tested; complete future 1.19-to-newer installation on the user's phone remains an acceptance step.

Checks happen while the app is used, not as an instant push to a closed app. Downloads and installation are optional and user initiated. GitHub uses current free public-repository hosting/runner policies. Existing Apper service costs/limits remain separate. Secure owner-controlled backup of both signing keys outside the workspace is required before retiring it.
