# Open Repertory 1.36 — sign-in and source labels

Install this update through **Menu → App updates → Check → Download and install**. Accept Android's update prompt over the existing app. Keep the same installation; uninstalling or clearing app data can remove local records and their encryption keys. An encrypted backup remains available under Backup & restore.

## What changed

Book names such as Kent, Boericke and Therapeutic Pocket Book remain visible. Routine provider labels now say **Open Repertory source**. The source records, rubric identifiers, remedy grades, original text, chapter illustrations and legal notices have been retained. Open **Legal credits** in the chapter/source information or Materia Medica reader to see the original providers, authors and licences.

Today, saved-case analysis and patient tools open before the full repertory finishes loading. The Repertory screen shows a preparation message until its chapters are ready. A book-file failure offers Retry and leaves saved records available. Book switching preserves the most recent selection, private notes and the original evidence in saved cases.

## Remembered access

After an approved sign-in, the phone stores an encrypted, account-bound approval for **up to 30 days from the last successful verification**. It works across app restarts and phone reboots. Valid old approval is migrated on upgrade; an old approval that has already expired requires a successful sign-in first.

Opening the app normally restores the workspace first and checks access quietly after about three seconds. Successful online checks refresh approval without asking for credentials. Further checks normally run every five minutes while the app is in use. Temporary connection failures or an expired hosted sign-in session do not erase valid remembered approval. Failed checks back off and **never extend the original expiry**.

You may still need to sign in after explicit Sign out, remembered-approval expiry, an account mismatch, or a verified owner decision that closes access. Android device unlocking remains separate: locking the device closes clinical access; returning from the background on an unlocked device preserves the current page.

Owner approval and revocation remain available through **Account & access → Manage approvals**. A confirmed pending/rejected/revoked decision closes clinical access when received. **Revocation cannot be checked while offline or when the hosted account session needs to be restored**; in those conditions, previously verified access can remain usable until its fixed 30-day expiry. The owner portal always requires its normal authenticated server permissions.

Explicit Sign out clears the remembered approval and persistent login cookies while retaining encrypted records. The app attempts to save drafts first and bounds that wait so a busy clinical renderer cannot leave sign-out pending indefinitely.

## Verification scope

See TEST_REPORT_1.36.md for the exact browser, package, Android-upgrade and session checks, their results, and the distinction between synthetic approval fixtures and real hosted-service authentication.
