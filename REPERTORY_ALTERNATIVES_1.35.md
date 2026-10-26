# Alternative repertory source research — 1.35

Research date: 25 October 2026. The owner requested removal of the Clarke, Knerr and Boger repertories shown in the picker. This report evaluates possible future replacements; it does **not** certify or bundle a replacement dataset. Kent was not changed.

## Measured baseline being retired

These figures were computed directly from the packaged JSON files before removal. Rubric nodes include navigation headings where present; remedy totals count distinct abbreviations actually used, not the entire application remedy dictionary.

| Retired dataset | Chapters | Rubric nodes | Distinct remedies | Remedy links | Tree depth | Grade distribution | Explicit extracted references |
|---|---:|---:|---:|---:|---:|---|---:|
| Clarke 1904 (`clarke`) | 3 | 5,022 | 996 | 19,775 | 2 | Grade 1: 19,775 | 0 |
| Knerr OCR (`knerr1897`) | 48 | 11,807 | 492 | 46,244 | 1 | Grade 1: 42,421; grade 2: 3,822; grade 3: 1 | 0 |
| Boger inverted OCR (`boger1915`) | 1 | 1,511 | 215 | 7,090 | 1 | Grade 1: 7,090 | 0 |

Clarke's presence-only associations were deliberate: italics in its clinical portion can identify Prescriber references rather than increased remedy strength. They must not be converted into invented strength grades. The Knerr import explicitly reduced four printed levels to three app levels and lacked a reconstructed hierarchy. The Boger import inverted remedy-indexed OCR into symptom rows and did not recover printed grading. These limitations support retiring those imports rather than relabelling them as newly verified sources.

## Candidate sources

### Clarke: a fresh public-domain original is available

[Wellcome Collection: Clinical Repertory, 1904](https://wellcomecollection.org/works/qb85y2ct) provides the original volume under a **Public Domain Mark**. It is xii + 347 pages and is suitable as a primary scan for a new independent transcription. It is not a ready-to-integrate symptom/remedy database and has no claim of a proofread structured export.

[Open Library's 1904 record](https://openlibrary.org/books/OL23750168M/A_clinical_repertory_to_the_Dictionary_of_materia_medica) points to the separate [Internet Archive digitization](https://archive.org/details/aclinicalrepert00clargoog), including plain text and page images. Plain text is OCR, so its existence does not resolve spelling, remedy identity, hierarchy or typography.

[Mittelbild's Clarke page](https://mittelbild.com/repertorien/clarke) advertises 4,820 rubrics from the 1904 book, with modernized remedy abbreviations. This is an advertised total, not a measured export or an exhaustive comparison with the app's 5,022 navigation/rubric nodes. No downloadable structured export or explicit redistribution license for its hosted transcription was established. Its [terms](https://mittelbild.com/agb) distinguish open works from proprietary specialist content and do not establish a blanket right to copy all hosted datasets.

**Recommendation:** retain the retired import privately; rebuild only from the original scan or an explicitly authorized structured transcription. Preserve clinical, causation and temperament parts separately; do not turn relationship tables into scored symptom rubrics or guess strength from italics.

### Knerr: substantially richer electronic candidates, but a new audit and rights are needed

[Library of Congress: Knerr/Hering repertory, 1896](https://www.loc.gov/item/07014533/) provides page images, online text and PDF. The collection's item record states public-domain/free-reuse status. This supplies a lawful independent proofreading reference, not a certified structured data file. The date is 1896, unlike the retired import's application ID `knerr1897`; edition identity should be corrected only in a new dataset, without rewriting saved selections.

[Homeoint's Knerr index](https://homeoint.org/hering/index.php) and [sample Throat transcription](https://www.homeoint.org/hering/throat-lr.htm) expose nested headings, remedy typography and explicit “See” targets in HTML. This is structurally more informative than a flat OCR import. However, the fetched HTML footer carries a Médi-T copyright notice (2005). No redistribution license for that transcription was found, and no independent full-book proofreading was performed here. It is a comparison candidate, not an approved bundle.

[Mittelbild's Knerr page](https://mittelbild.com/repertorien/knerr) advertises **128,357 rubrics**, original English text and retained grading. This advertised total cannot be compared one-for-one with the app's 11,807 parsed nodes; the counting methods and actual export have not been reconciled. No approved downloadable export was obtained.

OOREP's `hering` catalogue identifies Knerr's 1896 repertory. One read-only wildcard metadata page on the public API reported **143,637 source rows** over **1,437 pages**. Its cached catalogue lists **431 remedy IDs**. The sample exposes full source paths and weighted remedy associations; no full audit of hierarchy, grade scale, cross-references, duplicates or omissions was performed. Catalogue remedy-ID counts are not guaranteed to match normalized abbreviations in another edition.

**Recommendation:** prioritize a rights-cleared structured Knerr export, then compare all source IDs, full paths, remedy links, grade levels, cross-references and scan samples. Do not add an unaudited 143,637-row source merely because its count is larger.

### Boger: distinguish Synoptic Key from General Analysis

[HathiTrust's 1915 Synoptic Key record](https://catalog.hathitrust.org/Record/012113970) offers a full-view original, vi + 224 pages. It is the same bibliographic source family referenced by the old import, so it is not evidence that a newly discovered scan automatically repairs that import. A fresh extraction would need to separate the repertory portion from the Materia Medica portion and preserve their original organization.

[Homeoint's Synoptic Key index](https://www.homeoint.org/books2/bogersyn/index.htm) exposes a symptom-first short repertory and a separate remedy synopsis. The fetched index has a Médi-T copyright notice (1999). Its HTML is potentially useful for comparison, but its redistribution rights and independent proofreading were not established. An alternate Homeoint index also labels part of the repertory under construction; completeness cannot be assumed from a title page.

OOREP identifies **two different books**: `bogsk` is Synoptic Key, second edition, **1916**; `boger` is General Analysis, catalogued **1931**. They must not be merged under a “General Analysis and Synoptic Key 1915” label.

One read-only wildcard metadata page per candidate reported:

| OOREP candidate | Reported source rows | Reported pages | Catalogue remedy IDs | What the sample establishes |
|---|---:|---:|---:|---|
| `bogsk` — Synoptic Key 1916 | 7,707 | 78 | 435 | Source IDs and some explicit parent/path fields are present; the first row is a heading |
| `boger` — General Analysis 1931 | 519 | 6 | 263 | Symptom-first source paths and numeric remedy weights are present; the sampled first row includes grades 1, 2 and 3 |

These are different editions and counting units. More rows do not establish superiority, and one-page inspection is not a full content audit.

**Recommendation:** a future replacement should identify the intended Boger work and edition explicitly, use an authorized structured export or independently proofread original, preserve its grading and cross-references, and avoid the previous remedy-to-symptom inversion shortcut.

## OOREP redistribution boundary

The [OOREP FAQ](https://www.oorep.com/faq) lists the candidate repertories and states that hosted datasets beyond the accompanied Kent German/Publicum repertories and Boericke Materia Medica are not currently intended for redistribution by default. Its software license does not automatically license every hosted dataset.

The owner's existing confirmed permission covers English Kent, Boericke, BBCR and TPB. It has **not** been generalized here to `hering`, `bogsk` or `boger`. Reading a public metadata page for comparison is not authority to bundle that content.

Read-only count endpoints used (page 0 only):

- [Knerr/Hering metadata](https://www.oorep.com/api/lookup_rep?repertory=hering&symptom=%2A%2A%2A&page=0&remedyString=&minWeight=0&getRemedies=0)
- [Boger Synoptic Key metadata](https://www.oorep.com/api/lookup_rep?repertory=bogsk&symptom=%2A%2A%2A&page=0&remedyString=&minWeight=0&getRemedies=0)
- [Boger General Analysis metadata](https://www.oorep.com/api/lookup_rep?repertory=boger&symptom=%2A%2A%2A&page=0&remedyString=&minWeight=0&getRemedies=0)

## Integration decision

**No replacement repertory from this research is certified ready for bundling.** The three requested imports should be removed now while preserving already-saved rubric content in cases. Existing verified Kent, Publicum, Boericke, BBCR and TPB remain available. Any later replacement must pass source-rights, full-row coverage, remedy identity, hierarchy, grading and reference audits before entering the APK; a PDF reader or a new unreviewed OCR import would not satisfy the owner's database requirement.
