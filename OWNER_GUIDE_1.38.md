# Open Repertory 1.38: using the new book and complete result browsing

## Install

Open **⋮ → App updates → Check for updates**, download 1.38 and confirm Android's installation prompt. Install over the existing app. Do not uninstall or clear storage: patient records, cases, notes, account approval and installation identity belong to the existing installation.

This release uses the same Android package and signing certificate. It adds no permissions and changes no account-approval rules. Remembered access and device-lock behavior remain as before. It also corrects large-record encryption on older Android Keystore providers while retaining the existing key and record format. If a record was already damaged before updating, the app retains its recovery screen and requires a verified backup; the update does not reconstruct lost ciphertext.

## Open the imported repertory

1. Open **Repertory** and tap the current book name.
2. Choose **Guernsey — Haemorrhoids**.
3. Choose **Subjective symptoms**, **Objective symptoms**, or **Aggravation and amelioration** in the illustrated chapter drawer.
4. Browse, or search by rubric wording, remedy name or abbreviation. Use **Repertory & reading** to show/hide remedies, change text size or adjust density.
5. Tap **+ Add** on a remedy-bearing rubric. The floating clipboard opens the existing analysis workspace.

The database contains the entire repertory section of William Jefferson Guernsey's **second edition, 1892**, printed pages **121–140**. It contains **518 entries** including cross-reference entries and an original grouping heading, **222 remedy-bearing entries**, **138 identified remedies**, and **1,825 scored associations**. There are **38 additional navigation headings**, so the application's structural rubric-node total is **556**. The separate remedy-first Materia Medica portion of the scanned volume is outside this import's scope.

This is a specialist historical repertory. It is a separate title from Schroyens' Synthesis and van Zandvoort's Complete Repertory. Those modern databases have not been obtained or added. Kent and the other installed books remain intact.

## Read grades and references

- Plain/Roman remedy type is **grade 1**, italic is **grade 2**, and bold Roman is **grade 3**. The values retain this book's scale.
- Open a rubric's **ⓘ** button for its printed page, source ID, grade legend and retained source issues. Original bibliographic information remains under **Legal credits**.
- A **See** link opens its target. If a reference points to several conditions, choose the intended entry; **<** means aggravation/worse and **>** means amelioration/better. Reference navigation does not add a rubric to the case.
- Four printed remedy occurrences have unresolved identities: **Angus.** twice, **Chem.** once and **Mang.** once. Their printed grades and explanations are retained in source details. They are excluded from calculations; the application does not invent a remedy identity or silently substitute another.

The import was compared page by page with all twenty repertory pages using AI-assisted review and retained correction records. This is a transcription review, not independent clinician certification or evidence of treatment effectiveness.

## Browse every scored remedy

**Ranking** and **Matrix** now include **Find scored remedy**. Search the full computed result set by name or abbreviation, including remedies below rank 120.

Use Previous/Next, First/Last, or type a page number and press Enter. Each page displays at most **24 remedy columns/cards** to keep the interface manageable; every scored remedy remains available. A search retains each result's original overall rank. Paging and searching do not change selections or scores.

The app imposes no arbitrary rubric-selection count limit. Practical capacity still depends on the phone's memory and storage.

When **include grade 1** is unchecked, a recorded grade excluded by that filter appears in the matrix with **×**. It is not counted in coverage or points. A dash means no recorded association. Open **Grades & scoring** for the explanation; **Why scored** in Ranking opens the existing evidence list.

No separate database download or PDF reader is required. The structured book is bundled inside the APK for offline clinical browsing after approved access.
