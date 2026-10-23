# Open Repertory 1.24

Install through **Tools → App updates → Check for updates**, or install the new APK over your current installation. Keep the app installed and retain its storage so your encrypted records and phone approval remain available. Android asks you to confirm installation.

## Repertory

Opening Repertory shows the chapter list. Choose a chapter to close the list and read its rubrics. The chapter button opens it again. Starting a search also closes the chapter list.

Remedy lists start hidden. **Show remedies** displays all remedy lists; **Hide remedies** collapses them again. This changes only the display: source grades, rubric selections and analysis scores remain intact. Your choice is saved on this phone. **Aa → Remedy lists** controls the same preference.

Rubric rows and remedy lines use closer spacing. The information icon opens the original source, recorded grades and available references. Tap a remedy abbreviation to open its Materia Medica entry.

Tap the book name to choose a repertory. The picker shows only repertory names. Kent is the English Kent dataset; Repertorium Publicum remains a separate book. Full source metadata is retained in the rubric information sheet.

## One case, multiple repertories

You can now add rubrics from different repertories to the same case. Each selection retains its original source ID, remedy list and recorded grades. A shared chapter/path in two books is treated as two separate selections. Removing or changing the importance of one does not alter the other.

Each selected entry contributes independently to the analysis. Choose related entries deliberately to avoid giving a symptom unintended extra influence. Clarke entries retain presence-only values; their printed italics are not converted into strength grades. Grade filtering is applied per entry, so a mixed case does not turn Kent grades into Clarke presence values.

Existing cases retain their original records and primary source key. Mixed selections survive reloads, encrypted backups and snapshots. Snapshots can be saved while viewing another repertory.

## Analysis

The clipboard icon opens analysis. **Matrix** is the default view: selected rubrics appear as rows, ranked remedies as columns. The heading for each remedy shows coverage and points. The first rubric column and column headings stay in view while scrolling. The source and importance appear beside each rubric.

**Selected** provides compact rubric rows with horizontal ×1/×2/×3 importance controls and an individual remove button. **Ranking** shows the existing remedy ranking and the “Why scored” action. On wider tablets, selected rubrics and results appear side by side.

Open the case summary to manage the active case/patient. Snapshots and comparison remain on the action strip; export, print and copy remain under More actions. Existing scoring methods are retained.

## Updates

The update summary shows the installed version, available version, download size and current status. Download/Install stays separate from the release notes. **What’s new** opens the complete notes in a separate scrollable reader; Back returns to the update controls. The native updater still verifies metadata signatures, HTTPS destinations, file size/hash, Android package and signing certificate.

## Design reference and verification

The compact rubric-row/remedy-column workspace takes inspiration from the public [RadarOpus analysis manual](https://www.radaropus.com/RadarOpus/docs/manual/3.1/en/files/basic-html/page61.html). Its software, branding and analysis methods are not copied.

See [the verification report](TEST_REPORT_1.24.md) for browser, Android, scoring, data-audit and upgrade evidence. The Kent data remain the audited 22 October electronic snapshot; this UI update does not claim a new printed-book audit or real-device performance certification.
