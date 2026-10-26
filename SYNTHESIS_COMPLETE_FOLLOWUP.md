# Synthesis and Complete Repertory: renewed source search

Reviewed 26 October 2026 against the app at version 1.37. This is an additional search beyond the earlier publisher review. **Neither repertory has been integrated.** No full structured database with permission for this APK's distribution and an edition-matched original reference was obtained. No full-book proofreading is claimed.

## What the additional search established

| Candidate | Evidence checked | Result for integration |
|---|---|---|
| Complete Repertory author's Curar project | [Author](https://www.curarproject.com/author), [downloads](https://www.curarproject.com/downloads), [software partners](https://www.curarproject.com/homeopathic-software) | A direct author source was found. The Complete 2026 download inspected is an alphabetical bibliography, not rubric/remedy/grade records. The partner list identifies reading/analysis products, not a full reusable export. |
| Complete Repertory 2026 bibliography | [Actual PDF](https://www.curarproject.com/_downloads/1fa3bd46fa165fe426765becc58be805) | Downloaded privately for format inspection: 197 pages, 2,418,864 bytes, SHA-256 `41f86a7b2b6ba8dad0a0fed90ea14148c34151cfe7e119868bf961de9abb5a59`. The first page contains author/publication citations and addition counts. It does not supply the rubric paths, remedy associations, hierarchy and grades required for import. The bibliography is not bundled or published with the app. |
| Complete Dynamics | [Edition comparison](https://www.completedynamics.com/pages/pricing_editions.php?lang=en), [book manual](https://www.completedynamics.com/help/cdhelp_book_module_m_osx_en.html) | A free Browser edition can read the repertory within that software. This is not an authorized database export for Open Repertory. The manual documents source grading, references and navigation; these descriptions cannot replace the full records. |
| Similia, a Complete Repertory software partner | [Terms](https://www.similia.io/terms), checked against the author's partner list | The terms restrict copying/extracting licensed content into another product without written permission. No scraping or extraction was performed. |
| Synthesis / RadarOpus | [Synthesis product information](https://www.radaropus.com/products/synthesis/features/adonis-features), [license terms](https://www.radaropus.us/terms-and-conditions/) | Product content is supplied under a personal license with transfer/copying restrictions. No full developer export authorized for our APK was found. Personal-additions tools do not supply the full Synthesis database. |
| Public code and dataset searches | [OOREP](https://github.com/nondeterministic/oorep), other homeopathy repository results | A software project's code license does not establish rights to unrelated modern repertories. Search results using the word “synthesis” for algorithms, notes or literature-review software were excluded. No verified full export of either named work was identified. |

The [download-verification record](docs/planning/2026-10-26/bibliography-verification.json) records the inspected file's metadata and the limits of the inspection. No bibliography PDF is included in this repository.

This does not prove that a publisher will never provide an export or permit integration. It establishes that the files and permission needed for a faithful import are currently missing. Synthesis by Schroyens, Complete by van Zandvoort and Barthel/Klunker's Synthetic remain separate titles.

## Integration route now identified

Use the Complete Repertory author's [contact form](https://www.curarproject.com/contact) or [RadarOpus support](https://helpdesk.zeus-soft.com/) to request an authorized export and intended-distribution terms. No message was sent on the owner's behalf.

Suggested request, ready for the owner to send:

> We develop Open Repertory, an Android application for approved users. Can you provide a licensed, edition-identified structured export of your repertory for integration? We need original chapter/rubric/remedy IDs, parent/path structure, original grade values and their legend, references, synonyms and source bibliography. Please specify whether the agreement permits offline storage and distribution in an APK hosted on public GitHub Releases, the required credits, and any per-user licensing or content-protection requirements. Please also identify the original reference dataset against which import completeness and accuracy can be checked.

After receiving these inputs, compare every ID, path, remedy association and grade; detect missing/extra rows, duplicates, orphans, cycles and unresolved references. Compare the same edition and distinguish electronic-record verification from printed-page proofreading. The documented grade scale must govern each import; values must not be guessed from typography, shifted automatically, or silently discarded. Preserve Kent and existing case identities. Benchmark the actual dataset before choosing storage and paging.

The detailed audit remains in [the earlier research report](SYNTHESIS_COMPLETE_RESEARCH_1.37.md). No reader, bibliography, sample, renamed existing book or unverified OCR import has been substituted for either requested database.
