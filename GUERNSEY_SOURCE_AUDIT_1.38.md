# Guernsey 1892: source audit and integration decision

Reviewed 26 October 2026 for Open Repertory 1.38.

## Source and scope

William Jefferson Guernsey, *The Homœopathic Therapeutics of Hæmorrhoids*, second edition, Boericke & Tafel, Philadelphia, **1892**. The [Internet Archive item](https://archive.org/details/homoeopathicther00guer) supplies a [full scan](https://archive.org/download/homoeopathicther00guer/homoeopathicther00guer.pdf). The [Wellcome catalogue](https://wellcomecollection.org/works/ybt6pchf/items) identifies the Countway Library / Medical Heritage Library copy with **Public Domain Mark**. No modern reprint additions or third-party proprietary database were imported.

The scan contains 156 PDF pages including covers, blanks and front matter. This import covers the **entire repertory section**, printed **121–140**, PDF **127–146**: Subjective, Objective, and Aggravation and Amelioration. The separate remedy-first Materia Medica section is outside this repertory import. No PDF reader or scan is bundled.

Source PDF SHA-256: `7b4ea8c3e5f361fc92cc11cbaa1622413fd195a91f44cfbd738ff019b0f2a9f2`.

The Complete 2026 bibliography inspected earlier cannot supply rubric associations or grades. Lippe's full historical repertory was also located, but its OCR contains misread abbreviations and it has not received a complete page/grade audit, so it was not bundled. Guernsey was chosen because its complete specialist repertory section could be checked within this review. It is a distinct work from Synthesis and modern Complete Repertory.

## Review method and measured coverage

OCR and its font classifications were used as a draft only. All twenty repertory page images were compared with the draft, including continuation lines, original reference entries, ditto/repeated-heading families, remedy spellings and plain/italic/bold distinctions. Corrections and source-page mappings are retained privately with the reviewed transcription. Automated validation checks the import against that reviewed input; this is not an independent printed-book transcription.

This was **AI-assisted page-by-page review**, not independent clinician certification, a guarantee of a flawless historical book, or clinical evidence for the treatments described. Source ambiguities remain explicit.

- **518 source entries** after expanding combined worse/better lines, including **222 remedy-bearing entries**, **295 reference entries**, and one original grouping heading.
- **38 generated navigation headings**, clearly marked as such, bring the structural node count to **556**. Generated headings do not acquire remedy grades.
- **138 identified scored remedies**, **1,825 scored associations**: **1,422 plain (1)**, **260 italic (2)**, **143 bold Roman (3)**. No conversion to another book's grading scale.
- **316 See/Compare links**: **280** single-target links and **36** links with explicit target choices. Every target ID exists. A broad reference to both worse and better conditions offers a choice.
- Repeated heading families are grouped for navigation. The interface renders two levels; deeper modality qualifiers remain in full labels. Full source paths, page numbers and entry IDs are retained.
- **143 source-abbreviation alias strings** accompany canonical identities. Original paper spellings remain in source metadata; ae/oe ligatures, spacing and abbreviation punctuation are normalized for readable/searchable display.

Corrected examples include bold/roman errors in Downward, Throbbing, Large, Evening, Parturition and source abbreviations misread by OCR. Remedy identities were checked against the book's full-name headings: **Jacar. is Jacaranda caroba**, **Merc. is quicksilver**, **Sacch. is Saccharum album**, and **Stront. is Strontiana carbonica**. These are not guessed from a similar abbreviation in another book.

## Four original occurrences retained unscored

| Printed page | Printed abbreviation | Printed grade | Why it is not scored |
|---|---|---:|---|
| 122 | Mang. | 1 | The scan does not establish the preparation; it is not silently changed to Manc. or merged with the shared Manganum aceticum entry. |
| 130 | Angus. | 2 | Agnus and Angust. both occur in the work; the spelling does not identify one unambiguously. |
| 138 | Angus. | 1 | Same identity ambiguity. |
| 138 | Chem. | 1 | Printed separately from Cham.; no unambiguous matching full-name heading was found. |

All four occurrences retain their text, grade and explanation in source details. Thus **1,829 printed associations are accounted for**, of which 1,825 identified associations can be scored. No unsupported remedy identity was created to make the anomaly count zero.

## Comparison with installed books

Counts below measure the packaged editions. Nodes include reference/navigation entries, so they are not equivalent to remedy-bearing rubrics. Alias and reference data from Publicum's bundled sidecar are included. Original edition scope and quality matter more than comparing unlike totals.

| Book | Chapters | Nodes | Remedy-bearing | Distinct scored remedies | Scored associations | Grades present | References |
|---|---:|---:|---:|---:|---:|---|---:|
| Kent · English (OOREP) | 37 | 69744 | 68705 | 624 | 611027 | 1, 2, 3 | 5 |
| Repertorium Publicum | 41 | 75703 | 74440 | 666 | 719934 | 1, 2, 3 | 3 |
| Boericke | 25 | 8261 | 7285 | 1055 | 66201 | 1, 2 | 708 |
| Boenninghausen — BBCR | 61 | 30747 | 29861 | 497 | 421484 | 1, 2, 3, 4 | 0 |
| Therapeutic Pocket Book — Allen 1891 | 51 | 2924 | 2718 | 359 | 127315 | 1, 2, 3, 4 | 0 |
| Boenninghausen's Characteristics & Repertory (Boger 1905) | 57 | 10769 | 10769 | 140 | 115582 | 1, 2, 3 | 0 |
| Therapeutic Pocket-book for Homoeopathic Physicians | 6 | 1641 | 1641 | 120 | 48090 | 1 | 0 |
| Guernsey — Haemorrhoids | 3 | 556 | 222 | 138 | 1825 | 1, 2, 3 | 316 |

**Decision:** add Guernsey as a specialist reference. Its scan-reviewed three-type grading and dense explicit references are useful for this subject. Its narrower scope does not justify replacing a general repertory. Kent and all seven existing repertory files are unchanged, as are Materia Medica, remedy dictionary, original legal notices and chapter art; the new book reuses three appropriate approved pictures. The oldest OCR editions retain their existing quality labels.

The new compressed content adds about 53 KB to the APK together with this release's UI changes. This is a measured package-size difference, not a phone memory/performance benchmark.

## Software integration

The book participates in existing offline search, chapter browsing, remedy display, original-grade analysis, source review, mixed-book cases, snapshots and encrypted records. Source information remains on demand under the rubric's info button and Legal credits. No standalone database file is published.

The existing scoring function is unchanged. The only production native source change is a large-record encryption correction in RecordStore: bounded cipher updates and length checks preserve the existing key/IV/AAD format. Account approval, device lock, secure bridge and update protection sources remain unchanged. Ranking and Matrix can now find every scored remedy through search and paging; a 24-remedy display batch is a rendering choice, not a total-result cap. The original seven clinical datasets are checked byte for byte against 1.37. See [validation](TEST_REPORT_1.38.md) and the [usage guide](OWNER_GUIDE_1.38.md).
