# Open Repertory 1.32 validation

425 counted checks passed: {"materia-content-audit": 23, "chapter-art-content-web": 58, "materia-reader": 190, "higher-grades": 9, "navigation": 115, "package": 16, "android-workflows": 13, "android-upgrade-workflow": 1}.

Content tests compare every retained classical record with its archive, verify each withheld record's hash and reason, check all fresh source/body hashes and citation anchors, preserve nested clinical subsections and referenced footnotes, reject duplicate imports, and retain all previous supplemental remedy/source pairings. All 330 chapter names map explicitly to existing bundled pictures. Browser checks actually load every chapter picture across all ten repertories, exercise drawer scrolling/selection and source switching, display quality status, open the offline license, and preserve private notes.

Reader and navigation regressions cover five viewport sizes, light/dark themes and enlarged text. Higher grades still survive saved cases, snapshots and encrypted backup. Every shipped web asset matches the tested file. Every repertory and remedy dictionary remains byte-identical to 1.31; native code and permissions are unchanged.

Android API 28 with WebView 66: 13 workflow checks pass, including new repertory loading, chapter pictures, selections, analysis, high grades, encrypted backup, fresh Mure text/footnote and offline license dialog. A real install-over-update from 1.31 to 1.32 retains seven encrypted records and installation identity. Only synthetic test records and test-only access receipts were used; no production approval was bypassed or changed.

Limits: no physical-device test or zero-bug guarantee; structural screening and upstream proofread editions do not certify the retained OCR or validate clinical claims. Full printed-page proofreading remains outstanding. See the content review for scope and source decisions.

APK SHA-256: `b9a3d4c880931bbdcefe4cebeee0742141e4953e0f705cafe8280514d0ecef6a`
