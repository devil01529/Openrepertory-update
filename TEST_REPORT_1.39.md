# Open Repertory 1.39 validation

## Browser checks

| Area | Passed checks |
|---|---:|
| New navigation, motion, settings persistence/failure, focus and viewport behavior | 200 |
| Glass, themes, contrast, privacy, fallback and printing | 323 |
| Repertory-only reading panel | 200 |
| Materia Medica and private notes | 190 |
| Clinical workflows | 110 |
| Recoverable editing and source review | 38 |
| Guernsey, full results, references and saved grades | 57 |
| Mixed-book source identity | 24 |
| Scoring compatibility | 16 |
| Higher-grade preservation and backups | 9 |
| Actual signed APK isolation and identity | 669 |

Layouts cover 320/393px phones, short landscape, tablets and desktop, light/dark themes, enlarged text, reduced motion and solid materials. Motion tests inspect a controlled intermediate transition position, interruption and final alignment; these are not physical-device frame-rate measurements. Failed setting saves restore the previous preference. Saved case bytes and scores remain unchanged.

The reading-panel test now checks the finite visible entrance rather than requiring the retired CSS animation name. The historical scoring comparison uses the archived 1.23 build. Persisted-record comparisons verify unchanged saved bytes directly.

## Package isolation

Compared against the actual signed 1.38 APK. Every repertory, remedy dictionary, Materia Medica passage, chapter image and legal asset is retained. The generated audit changes only its build timestamp. Production classes.dex and the complete clinical engine are byte-for-byte unchanged, including the 1.38 large-record encryption correction and all native access/update protections.

Only the manifest version, index script loading, glass stylesheet, clinical presentation preferences and new local glass module change. No remote UI dependency, new Android permission, test authentication override, PDF, ZIP or standalone database is shipped.

Package: com.openrepertory.app.dev; version code 40; version name 1.39-dev. APK: 44,782,605 bytes. SHA-256: c6e2d49cba07e7d486c5d6d6902c4f2f840c43a3f808e77bc48161db4c4c823a.

## Android

The actual 1.38 → 1.39 upgrade passed sixteen source/storage workflow checks on API 28 / WebView 66: encrypted bytes and installation identity retained, remembered approval usable, all 222 Guernsey entries saved/reopened with original grades, results beyond 120 accessible, exact source navigation and Android Back preserved.

All 23 native visual checks passed: five-destination selection alignment, navigation clearance, reading scroll edges, approved sprite artwork, visual settings, encrypted preference saving, immediate Back, reading-switch mirroring, reload, exact saved cases/scores, warm background return, remembered access and restoration without errors. The installed APK was pulled back and its hash matches the prepared release. Native checks use isolated synthetic approval/case fixtures. Live owner/member sign-in, physical-phone frame rate and current-Android gesture navigation are outside this run's scope. Software checks do not guarantee absence of device-specific defects.

## Delivery

The existing independent metadata signature, APK certificate and latest-five release policy remain. Public manifest/APK availability and integrity are checked after publication. No standalone database or ZIP asset is published.
