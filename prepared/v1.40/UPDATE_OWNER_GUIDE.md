# Open Repertory 1.40 — clinician workflow guide

## Install and retain records

Use **⋮ → App updates → Check for updates** and install over the existing app. Keep the current installation and storage. Package/signing identity, native encryption, remembered approval, device locking, screenshot protections and signed updates are unchanged. No new permission or remote UI service is required.

## Review a consultation

Open **Review case** on Today, or **⋮ → Clinical tools → Consultation review**. This shows the active case, its linked patient, rubric/book counts, scoring method and grade filter. It does not recommend a prescription or modify source grades.

Use its actions to switch case, open the linked patient, create a visit, select rubrics, review analysis, read Materia Medica, save a snapshot or open backup/source information. Patient actions are disabled when there is no valid linked patient. If the active case changes while a review is open, reopen it before using an action.

The review stays on demand: the removed patient strip and backup banner have not returned.

## Find rubrics and repeat searches

Hidden remedies now create no remedy-button elements. Adding a rubric still uses its complete original associations. **Repertory & reading → Show remedies** builds the original lists; hiding them releases the buttons again. Source data and scoring are retained.

Chapter scope now stays with the chosen chapter during continued queries. Select a chapter before choosing chapter-only search; the app no longer silently widens a continued chapter query to the whole book.

Open **Recent searches · this case** in Repertory & reading, or Recent searches in Consultation review. A history entry restores its recorded book and chapter scope, without adding a rubric. A missing chapter is reported rather than widening the search. Closing history or changing case cancels late navigation.

Recent searches are a local convenience: up to twelve per case, with at most 120 entries on the phone. Progressive versions of a query are consolidated. Queries over 160 characters are not added to history; the search engine itself is not capped by this history rule. Clear this history requires confirmation and clears only the active case's history, retaining cases, notes and other histories.

In the APK this history uses the existing encrypted record store. It is not included in the clinical patient/case/note backup. Unsupported/unreadable history is retained without replacing it and does not prevent the clinical workspace from opening.

## Find words in Materia Medica

Open a remedy's **Read** tab and use **Find in this source**. Enter at least two characters. Matches are highlighted; Previous/Next and Enter/Shift+Enter move between them. The find controls remain visible as the source scrolls. Clear removes the highlighting and restores normal reading.

Finding searches only the selected source passage. Switching edition resets the find field and cancels pending work from the old source. Private notes are excluded and retained. To keep long passages responsive, the interface highlights the first 1,000 matches and labels that limit explicitly; the full source remains readable.

Case links label each original book and open its exact selected rubric, including mixed-book cases. Back returns to the Library reader. The related-rubrics action searches the whole current repertory, independently of a previously selected chapter scope.

## Reliability and privacy

Text size, remedy visibility, reading presets and presentation preferences now retain their previous saved value if saving fails. The reading-preferences dialog stays open for retry. Existing case-edit Undo and draft handling remain.

Presentation mode closes consultation/history sheets and prevents them from showing identities or clinical queries. Glass, reduced-motion preferences, enlarged text and the icon-only clipboard remain. Backup/restore uses the existing encrypted workflow: opening its screen alone is not a completed backup.
