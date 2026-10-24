# Repertory comparison and integration — 1.31

Kent's English and Publicum databases and relationship sidecar are byte-identical to the protected baseline. No Kent data replacement or new Kent import was performed.

## Measured comparison

Counts are measured from the actual files, not publishers' marketing totals. Source rows include headings/reference entries; they are not all remedy-bearing clinical rubrics. Old counts are navigation nodes from OCR imports, so this is coverage evidence, not a one-for-one equivalence claim. Distinct remedies count source abbreviations actually used. Scored links count each unambiguous positive remedy association once; raw links include duplicates and unsupported values.

| Imported source | Earlier bundled source | Source rows | Distinct remedies | Scored links | Explicit references (directly resolved) |
|---|---|---:|---:|---:|---:|
| boericke1906 | Not previously bundled | 8,211 | 1,055 | 66,201 | 708 (306 resolved) |
| bbcr1905_oorep | 10,769 OCR nodes / 140 remedies / 115,582 links | 30,709 | 497 | 421,484 | 0 (0 resolved) |
| tpb1891 | 1,641 OCR nodes / 120 remedies / 48,090 links | 2,802 | 359 | 127,315 | 0 (0 resolved) |

## Grading, hierarchy and source anomalies

| Source | Positive grade scale | Navigation chapter groups | Recorded parent links / depth | Alternate remedy names | Unsupported weights / conflicting remedy grades |
|---|---|---:|---:|---:|---:|
| boericke1906 | 1, 2 | 25 | 8,186 / depth 7 | 198 | 5 / 33 |
| tpb1891 | 1, 2, 3, 4 | 51 | 0 / depth 0 | 52 | 0 / 0 |
| bbcr1905_oorep | 1, 2, 3, 4 | 61 | 0 / depth 0 | 75 | 626 / 0 |

Boericke has an explicit parent hierarchy with no missing parents or detected cycles. BBCR and TPB provide full paths but no explicit parent links in this API snapshot; zero in that column means the field is absent, not that the printed work has no hierarchy. Navigation groups use the first source-path component. Some BBCR sections share the source chapter ID −1; unique application navigation IDs prevent those sections from opening the wrong chapter. Original source IDs remain intact.

## Decisions

- **Boericke:** added as a new searchable repertory. Recorded alternate remedy names participate in search. Explicit cross-references open a uniquely resolved rubric, otherwise a clearly labelled search. No rubric synonyms were invented.
- **BBCR:** the structured source is the preferred picker entry because the earlier OCR dataset explicitly contained only the first printed remedy-list line and provisional case-derived grades. The earlier edition remains under “Earlier editions” and keeps its original identifier so saved cases are not silently rewritten.
- **TPB:** Allen's 1891 edition is added separately. It has recorded grades 1–4 instead of the earlier 1846 OCR import's presence-only grade 1. These are different editions, and OOREP marks Allen's edition under development; it is not represented as a certified complete replacement. The 1846 source remains accessible for saved cases.
- **Phatak:** bibliographic sources found, but no verified redistributable structured export. Not bundled.
- **Synthetic Repertory (Barthel/Klunker):** identified separately from Schroyens' Synthesis. No authorized structured export available. Not bundled.
- **Complete Repertory:** licensed editions identified through Complete Dynamics; no authorized structured export available. Not bundled.

User-confirmed OOREP redistribution permission covers Boericke, BBCR and TPB (2026-10-24). It is not generalized to unrelated proprietary repertories, and these datasets are not labelled GPL merely because OOREP software is GPL.

## Integrity and grading

Every imported source ID, full path, parent identifier, remedy abbreviation, original remedy ID and raw weight was compared with the complete cached API pages. Page hashes and source counts are retained. All raw associations remain in the packaged source records; the scoring list is deliberately stricter:

- Exact duplicate remedy links count once.
- Conflicting grades for the same remedy/rubric remain visible for review and are excluded from scoring. No automatic highest-grade selection.
- Unsupported values, including BBCR weight zero and Boericke weight 30, remain visible and unscored. An independent Boericke transcription shows parenthetical “(30)” beside those five entries, supporting their exclusion as strength grades; this does not certify the original edition's typography.
- Grades 4 and 5 survive case saving, snapshots and encrypted backup validation. Grade 4/5 display has distinct numeric markers and matrix styles. Different books' grade scales are not claimed equivalent. Existing scoring formulas are unchanged.

Navigation groups by chapter and first heading. Full deeper comma paths remain visible in sub-rubric labels, and original source hierarchy/parent IDs are preserved. This is not an arbitrary-depth visual tree rebuild. TPB/BBCR may lack explicit parent IDs even where their full paths encode hierarchy. Only explicitly recorded references and alternate remedy names are added; zero references means none extracted from the provided records, not proof the printed book has none.

Complete source-response comparison is **not** complete printed-book scan verification. Remaining source anomalies are disclosed rather than silently repaired. Exact counts, grade distributions, duplicate paths, excluded values and hierarchy measurements are in the bundled import audit and validation JSON.

## Source research

- OOREP catalogue/edition status and licensing notes: https://www.oorep.com/faq
- Boericke independent transcription: https://www.homeoint.org/books4/boerirep/index.htm
- Weight-30 sample review: https://www.homeoint.org/books4/boerirep/general.htm
- Phatak bibliographic record: https://openlibrary.org/books/OL19437481M
- Synthetic bibliographic record: https://www.ncbi.nlm.nih.gov/nlmcatalog/8908003
- Synthetic publisher listing: https://ibpsbook.com/product/synthetic-repertory-3-vols/
- Complete source catalogue: https://www.completedynamics.com/pages/repertories.php?lang=en&prices=n
- Complete licensing: https://www.completedynamics.com/pages/pricing_licenses.php?lang=en

For the three unavailable modern sources, the next step is a rights-holder-authorized structured export with edition metadata and redistribution rights. It can then undergo the same ID, hierarchy, grade, reference and duplicate audits; download availability alone is not a license or a quality guarantee.
