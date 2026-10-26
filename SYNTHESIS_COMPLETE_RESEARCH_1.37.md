# Synthesis and Complete Repertory — source and integration review

Reviewed 26 October 2026. **Neither new repertory is bundled in 1.37.** No authorized full structured export or edition-matched reference dataset was obtained, so no full content comparison or proofreading claim is made. The existing seven repertories, including Kent, remain unchanged.

## Source findings

| Work | Primary sources checked | What was available | Integration status |
|---|---|---|---|
| Synthesis, Frederik Schroyens, including Adonis | [Official product information](https://www.radaropus.com/products/synthesis/features/adonis-features), [RadarOpus](https://www.radaropus.com/), [manual: personal additions](https://www.radaropus.com/RadarOpus/docs/manual/3.2/en/files/basic-html/page123.html) | Product descriptions, demonstrations, structure documentation and tools for sharing personal repertory additions | No verified full database export or permission to redistribute it in this APK was found |
| Complete Repertory, Roger van Zandvoort | [Official catalogue](https://www.completedynamics.com/pages/repertories.php?lang=en&prices=n), [editions](https://www.completedynamics.com/pages/pricing_editions.php?lang=en), [downloads](https://www.completedynamics.com/pages/downloads.php?lang=en), [repertory editor](https://www.completedynamics.com/help/cdhelp_repertory_editor_m_osx_en.html) | Complete Dynamics software, a free Browser edition for reading, paid analysis editions and personal-additions files | A free reader and an additions file do not establish access to a redistributable full structured database |

Synthesis and Complete are different edited works. Barthel/Klunker's *Synthetic Repertory* is another separate title; it must not be substituted or relabelled as Synthesis. Publisher descriptions and advertised totals are not measured database audits.

The owner confirmed that no licensed export is currently held. Permission from the app owner to develop this feature does not supply the publishers' database files or establish their redistribution terms. The existing permission for specific OOREP editions is not generalized to these products. No mirrored PDF, cracked application database, paywall extraction or incomplete sample was used as a substitute.

## Concrete route to integration

Request an edition-identified export and distribution agreement from the relevant publisher/rightsholder. The agreement needs to cover the intended delivery: approved app users, offline database storage and APK distribution through the public GitHub update channel. A license only to read another application does not establish those permissions. Use a documented export/API or publisher-supplied JSON, CSV, XML or SQLite data rather than guessing an encrypted proprietary format. No credentials or private licence keys belong in a public APK or repository.

The export should include original chapter/rubric/remedy IDs, full paths, parent links and ordering, unmodified grade values and their documented meaning, remedy names/abbreviations, cross-references, synonyms and author/edition/bibliography metadata. Obtain the original electronic reference for the same edition and, where permitted, page references or printed facsimile samples for checking wording and typography.

## Required audit before adding a book

1. Freeze hashes of the received export and independent reference; retain edition, language, rights and release identifiers.
2. Measure chapter count, navigation headings, remedy-bearing rubric count, distinct remedies, links, grade distributions, hierarchy depth, references and aliases separately. Reconcile publisher counting units before comparing totals.
3. Compare every original ID, full rubric path, remedy association and grade against the authorized reference. Detect missing/extra records, duplicates, cycles, orphan parents, mismatched remedy identities and edition differences. Do not silently merge identical paths from different books.
4. Resolve references by source ID, retaining direction and type. Preserve unresolved references visibly; never invent synonym equivalences or grade values. Exclude unsupported/conflicting grade associations from scoring until resolved while retaining their source evidence.
5. Proofread source samples covering chapters, deep paths, punctuation, abbreviations, high/low grades and known conflicts. An exhaustive electronic-record match and sampled printed-page checks must be reported as separate scopes. Correct discrepancies and rerun the comparison; do not label the entire printed book proofread after sampling.
6. Benchmark actual source size on the oldest supported Android/WebView. Choose indexed, paged storage if the full source cannot be queried responsively in memory. Preserve saved selections by source ID and retain exact grading; keep Kent unchanged.
7. Run import, search, chapter navigation, cross-reference, mixed-book analysis, snapshot, backup and installed-upgrade checks before publishing. Keep the book out of the selector until its data and rights pass review.

## Implemented meanwhile

The 1.37 clinician workflow work fixes confirmed wrong-book evidence navigation and stale Clear all confirmation. It adds exact chapter selection, a direct return to analysis, recoverable rubric removal/clearing, clearer save-failure feedback and a saved analysis-view preference. These changes improve the current databases without claiming that either requested new work has been integrated.
