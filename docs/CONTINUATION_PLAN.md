# Open Repertory continuation — 5 November 2026

## Verified recovery
- Restored the public distribution repository locally.
- Five prepared APK versions remain: 1.37–1.41.
- Recovered 660 packaged assets from 1.41 locally; these are not the maintained build source.
- APK SHA-256: 9be52ef0524a509cf69a22cd7c09634b1946d166f25297988b1046d456710b59.
- The previous development workspace, unfinished Santee transcription, full native source, Android signing keystore and update-metadata private key are absent from this workspace. Public repository README explicitly excludes full app source and private keys.
- No new APK has been built, tested or published in this continuation.

## Phase 1 — restore build continuity
Restore the prior development archive and configure the existing signing identities securely. Do not generate replacement keys: existing installations and the updater trust the old identities. Retain the current release until upgrade testing passes. Keep source backups in an owner-controlled private repository and keys in secure separate storage.

## Phase 2 — verified repertory content
Resume Santee (1890), with all 40 content pages compared to transcription. Prior conversation records partial review, but the transcription files were not recovered. Recover those files or repeat the review; do not infer completion from conversation history. Retain original abbreviations, page references and typography. Without a documented grading key, use explicit presence-only semantics. Ambiguous remedy identities stay unscored. Preserve Kent and other existing books byte-for-byte where feasible.

Murphy: the publisher lists Homoeopathic Medical Repertory, third revised edition, English hardcover, 2,432 pages, SKU 9788131908587:
https://www.bjainbooks.com/products/homoeopathic-medical-repertory
This is a book product listing, not a reusable database export or redistribution licence. GitHub repository search for Murphy repertory returned no repositories. General web search was unreliable; two software vendor pages returned HTTP 403. No exhaustive absence claim is made. No verified licensed full database was obtained. Obtain an edition-specific export and bundling permission from the rightsholder before packaging it. Check full hierarchy, remedy identities, grade legend, cross-reference targets, duplicate paths and source provenance; no invented numeric grades or relabelled substitute books.

## Phase 3 — clinician navigation
Implement favourite chapters scoped to each book, retaining chapter illustrations and drawer behaviour. Save through existing encrypted preferences, with rollback and a clear message on write failure. Show original source typography on demand. Make presence-only evidence explicit in ranking, matrix, exports and Materia Medica case links, including mixed-book cases.

## Phase 4 — validation and release
Validate imported rows against reviewed source records; test ambiguity handling, original grades, source switching, favourites persistence, small-screen layouts, reduced motion and mixed-source analysis. Test an in-place Android upgrade from 1.41, saved cases, authentication, encryption and signed update verification. Publish only after tests pass using the existing identities. Retain latest five releases. Report measured results and remaining limitations; do not label machine review as independent clinical proofreading.
