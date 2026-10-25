# Open Repertory — conversation and project context

Current state: **version 1.34-dev**, Android version code **35**, published and verified on 25 October 2026.

## Goal

Build a usable Android clinical repertory and Materia Medica workspace for homeopathic clinicians. Preserve clinical functions, source identity, remedy grades, patient records and upgrade compatibility while improving the interface and reference content.

## Decisions and work completed

- The original APK was reviewed and developed into Open Repertory, with patient files, visits, cases, rubric search/selection, analysis, Materia Medica, private remedy notes and encrypted backup/restore.
- Clarke's uploaded Clinical Repertory was converted into structured database content rather than added as a PDF reader. Chapter/rubric extraction and remedy typography/gradation were reviewed. Earlier reports document the extraction and remaining source limitations.
- English Kent from OOREP was added separately from legacy Repertorium Publicum. Its electronic records, source IDs, paths and remedy grades were audited against the electronic source. Cross-references and alternate remedy names were integrated. This electronic-source audit is not a printed-book certification.
- The owner asked that the current Kent database be preserved. Avoid replacing or changing Kent during future source improvements without a new instruction.
- Boericke, Boenninghausen–Boger (BBCR), and Allen's Therapeutic Pocket Book were added from owner-authorized OOREP sources. Their source anomalies and unscored/conflicting entries remain identifiable; do not invent grades or silently score excluded records. Earlier editions remain available for existing cases.
- The owner stated redistribution permission covers English Kent, Boericke, BBCR and TPB. Phatak, Synthetic and Complete have not been added from an authorized structured export. Do not describe those as installed additions.
- Materia Medica content was reviewed. Fresh imports supplied 78 monographs from proofread Mure/Anshutz digital editions, while 337 suspect OCR passages were withheld. Retained OCR and Boericke are not labelled fully proofread. Private notes remain saved.
- Major UI work introduced a compact clinical workspace, a glass-inspired interface with a solid-mode option, phone/tablet layouts, smoother startup, grouped menus, compact rubric/remedy spacing, searchable chapter/book selectors and an improved Materia Medica reader/section selector.
- Repertory reading controls have their own button at the top of the Repertory page, rather than living in the main menu. They include text sizing, remedy visibility, branch expansion, patient-word finder and reading/privacy preferences. Chapter pictures remain visible, and the drawer header scrolls with the chapter list. Selecting a chapter closes the drawer.
- A floating clipboard-only button opens the analysis page; it has no visible “Analysis” word. Analysis includes selected rubrics, ranking and matrix views. Source identity is preserved when selecting rubrics across books.
- Screenshots are allowed in the unlocked, approved workspace. Device locking closes clinical access. A surviving unlocked background session preserves its state and can return smoothly, subject to approval validation.

## Access system

Firebase was replaced by **Apper** and archived. Use Apper for current access management; old Firestore grants do not control this APK.

Portal: https://preview--openrepertory-eminent.apper.so

Members sign in/request access for their phone. The owner approves or revokes device access in the owner portal, also accessible through the APK owner account. New Member signup alone does not grant clinical access. Owner credentials are managed through the owner's Apper project dashboard, not stored in this document. Never share passwords, tokens, signing keys or patient data in a public repository.

The app stores clinical records locally with encryption. Native approval validation binds the signed-in account, request, phone and grant. Approved access can be cached for up to 24 hours in the same boot; offline revocation cannot be immediate.

## Latest update: 1.34

The owner approved all **137** new chapter illustrations:

- Boericke: **25** chapters, IDs B01–B25.
- BBCR: **61** chapters, IDs C01–C61.
- Allen TPB: **51** chapters, IDs T01–T51.

All approved images are integrated into their exact chapter drawer rows and selected-chapter headers. Kent artwork is retained. Repeated source paragraphs in the three new repertories are hidden from normal rubric rows; details, cross-references and grading warnings remain available through the information button.

Security hardening requires essential Apper request/grant fields, revalidates request ownership before decisions, rejects duplicate or malformed grants, discards stale checks after session changes, restricts login redirects, and tightens native receipt validation. Live row-level owner/member restrictions were reviewed and preserved. Independent agents reviewed access and release-cleanup changes. This was targeted security testing, not a guarantee that every possible vulnerability is eliminated.

## Distribution and release retention

Repository: https://github.com/devil01529/Openrepertory-update

The APK checks the GitHub update channel. Downloads require no GitHub account; Android still requires the user's installation confirmation. The existing package name is `com.openrepertory.app.dev`. Retain the same Android signing identity and metadata verification key for upgrades. Version 1.34 is code 35.

Only the **five latest published versions** are retained. At completion these are **1.30, 1.31, 1.32, 1.33 and 1.34**. Versions 1.19–1.29 were deleted oldest first, with their release tags and obsolete prepared files removed. Cleanup runs after publication, on release-publication events and daily. Drafts, staged future versions and the latest update endpoint are protected. Git history was not rewritten.

This public repository is a release distribution channel. Keep development source, internal audit archives, credentials and signing keys in private storage. Historical source/audit ZIP assets were removed from retained releases and current prepared files; earlier Git history and previously downloaded copies cannot be revoked by that removal.

## Verification

**603 counted checks passed**, covering artwork mappings, clinical UI/scoring, Materia Medica, source switching, source panels, portal security, live anonymous-access denial, package integrity, release retention and Android native behavior.

A real Android upgrade from 1.33 to 1.34 preserved five encrypted test records and the phone identity. Native tests used API 28 / WebView 66 and synthetic emulator-only fixtures, which are excluded from the APK. The publicly downloaded 1.34 APK matched the tested APK byte for byte, and its signed update metadata verified.

APK SHA-256:
`4ce5b2db9528754f281b0bdffd20ded3d1cc4531aa6453417a3dbd40da88bb26`

Validation report: https://github.com/devil01529/Openrepertory-update/blob/main/TEST_REPORT_1.34.md

## Preferences to carry forward

Work autonomously in phases; preserve clinical functions and stored records; test before publishing; use independent review for substantial changes. Make chapter art specific to the chapter. Ask for artwork approval when requested before integrating a new set. Keep on-screen source information available on demand. Avoid unnecessary login transitions, empty space, clutter and inaccessible system-style selectors. Continue using Apper rather than reintroducing Firebase.

The latest requested update is complete. No additional feature is currently awaiting implementation.
