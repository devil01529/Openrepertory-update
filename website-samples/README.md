# Open Repertory — unpublished website samples

Open index.html locally and switch among Clinical Glass, Editorial and Compact Workbench. Keep styles.css and app.js beside the HTML files. SVG previews are design illustrations, not browser screenshots.

Navigation, sample rubric selection, remedy visibility, filtering, clear selection and sample approval/revocation work locally. Data is illustrative. No production grants, patient data or full repertory database is included. Library and analysis are layout prototypes. Apper buttons link to the existing login portal; these samples do not establish a local authenticated session.

## Implementation after design approval

1. Apply the chosen design to the clinical interface, preserving source identity, grades, mixed-book analysis and reference reading.
2. Prefer the existing Apper project for shared account identities. Sessions cannot be assumed to transfer across origins. Preserve existing Android routes and native approval bridge.
3. Add explicit browser access requests using the authenticated identity and browser installation ID. Current service labels requests Android and binds approvals to device IDs. An Android grant must not silently authorize a browser. Browser IDs are not tamper-proof hardware identities.
4. Enforce owner approval/rejection/revocation and member ownership with backend policies. Validate authorization on private data operations. Browser storage and hidden buttons are not authorization. Fail closed on error/expiry/revocation.
5. Keep clinical records local by default. Web records do not automatically synchronize with Android encrypted records. Define secure import/export separately; web storage does not have Android Keystore protection.
6. Test owner/member roles, direct URLs, pending/approved/revoked access, session expiry, refresh and browser data reset, plus responsive clinical workflows.
7. Select an available hosted subdomain after approval, subject to service terms. A custom domain requires ownership/DNS setup and may cost money. No address is reserved or live yet.
8. Publish only after explicit design approval and passing access/clinical tests. GitHub sample source is not a deployed website. Preserve the APK's signed release channel.

## Verification and scope

Node JavaScript syntax and DOM-harness interaction checks passed. The harness tests navigation, selection, visibility, filtering and synthetic approval controls. Sites managed runtime has no cua_repl.js, so no preview server or alternative browser-control path was used for these samples. Responsive rendering, live credentials and end-to-end approval are untested.

Apper schema and relevant source were inspected read-only: Owner/Member profiles and device-request/grant/audit tables exist. No live accounts, grants, policies or deployed pages were modified.
