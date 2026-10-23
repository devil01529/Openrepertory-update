# Open Repertory 1.26 — illustrated chapters and reading/session controls

Install this update over the existing app. Do not uninstall or clear storage. Use **⋮ → App updates → Check for updates → Download update → Install update**, or install the APK from the public GitHub release.

## Chapters
The chapter pictures are restored in the drawer and chapter heading. Scroll anywhere inside the chapter drawer: its heading, book selector, source section and filter now move up with the chapters. There is one scroll surface instead of a fixed upper block and a separate chapter-list scroller. Filtering, Clear, current-chapter indication and automatic closing on selection remain available.

## Text size and repertory controls
Open **⋮ → Repertory & reading**.

- **A− / A+** make text smaller or larger in 10% steps, from 80% to 140%.
- **Reset** restores 100%. Your choice is saved on this phone. Android applies it using WebView text zoom so legacy screens and pixel-sized text scale too.
- **Show/Hide remedies** controls remedy lines throughout the current reading view.
- **Collapse/Expand branches** controls the rubric tree. It does not change selections.
- **Patient words** returns to Repertory and opens the existing symptom finder.
- **Reading density & privacy** retains the density and presentation settings.

These controls have moved out of the top repertory strip, leaving more room for reading. Back returns from this menu section to the main tools menu; Back again closes it.

## Analysis readability
The explanation below the matrix now uses the selected theme’s panel background and text colors. The Remedy ranking heading also uses the theme’s readable text color. Matrix values, grades and scoring formulas are unchanged.

## Background and device locking
Returning to the running app while the phone remains unlocked preserves its clinical document, page, reading position and active case. The old one-minute background relock is removed. Approval is refreshed quietly without placing a login/loading screen over the workspace. A revoked or expired approval still restricts access.

When Android reports that the device is securely locked, the app hides its clinical surface, rejects clinical bridge reads and protects the background window. Unlocking the device through Android can resume the previously authenticated in-memory session without a second app PIN. Owner approval is checked again before clinical access is restored. No password, PIN or authentication bypass is stored in the APK.

If Android kills the app process, you force-stop it, or you start it fresh, it must initialize and verify access again. The warm-session behavior applies while the app process survives; it does not promise that Android will retain a background process indefinitely. Saved patients, cases, drafts and text-size preferences remain stored on the phone.

[Verification report](TEST_REPORT_1.26.md) · [Implementation plan](UI_SESSION_PLAN_1.26.md)
