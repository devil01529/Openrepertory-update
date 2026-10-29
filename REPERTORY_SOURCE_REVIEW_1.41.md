# Repertory source review — 28 October 2026

**No new clinical database is bundled in 1.41.** This search found additional historical source candidates, but did not produce a new edition that passed full transcription, remedy-identity, grading and hierarchy review. Existing eight datasets, including earlier-edition archives, remain byte-for-byte unchanged. This report distinguishes discovery, sample proofreading and complete verification.

## Online database candidates checked

| Candidate | Evidence inspected | Decision |
| --- | --- | --- |
| [OOREP](https://github.com/nondeterministic/oorep) | Current README describes Scala application and a PostgreSQL dump; the app already contains authorized English Kent, Boericke, BBCR and TPB snapshots | Do not duplicate an existing book or treat another API client as a new edition. Existing edition-specific permission is retained. |
| [OOREP MCP](https://github.com/Dhi13man/oorep-mcp) | README describes a client querying the same OOREP service | No independent repertory corpus or original-book proofreading evidence supplied by this wrapper. |
| [hydrogen2oxygen/repertory](https://github.com/hydrogen2oxygen/repertory) | Root contains LICENSE and a 56-byte README only | No rubric/remedy dataset to import. |
| [mbarzakh/repertory.github.io](https://github.com/mbarzakh/repertory.github.io) | Root contains PHP/HTML application files, several empty files, and an Apache licence | Root inspection does not establish an edition-matched, reviewed corpus. No full nested-data audit claimed. |
| [Homeopatic-Medicine](https://github.com/danielrennich-sys/Homeopatic-Medicine) | Data directory, ingest scripts and [derive_repertory_sources.py](https://github.com/danielrennich-sys/Homeopatic-Medicine/blob/main/scripts/derive_repertory_sources.py) | Derives per-remedy text from symptom-organized sources and uses abbreviation/prefix resolution. This is not demonstrated equivalent to preserving each original rubric ID, hierarchy and printed grade. Do not reverse that transformation into scored records without source review. |

GitHub search queries included “homeopathy repertory” and “homeopathic database”. Search results and repository descriptions alone are not proofreading certificates. No untrusted downloaded scripts were executed.

## Historical original-book candidates

Internet Archive was also searched for pre-1930 medical repertories. The following National Library of Medicine scans have identifiable titles and authors:

| Work | Original source | Current review status |
| --- | --- | --- |
| Samuel Worcester, *Repertory to the Modalities … Temperature, Air, Water, Winds, Weather, and Seasons* (1880) | [NLM scan](https://archive.org/details/64340360R.nlm.nih.gov) | Metadata identifies PDF and ABBYY OCR. Complete rubric/grade transcription not reviewed. |
| M. E. Douglass, *Repertory of Tongue Symptoms* (1896) | [NLM scan](https://archive.org/details/101303847.nlm.nih.gov) | Metadata identifies PDF and plain OCR. Complete rubric/grade transcription not reviewed. |
| Ellis M. Santee, *A Repertory of Convulsions* (1890) | [NLM scan](https://archive.org/details/66340810R.nlm.nih.gov) | PDF, plain OCR and ABBYY downloaded for assessment; 100 PDF pages. One scan page visually compared with extracted text, not a whole-book audit. |

The sampled Santee page is printed p.9 / PDF p.13. The scan prints **Æsc-h.**, while extracted text gives **^sc-h.**. Nested lines use ditto dashes whose meaning depends on preceding lines: “Sensation … of a mouse running down the arm … and back”. OCR text alone cannot safely assign the full path. Bold rubric words must not be mistaken for bold remedy grades. These findings require correction and a book-wide review before import; they do not establish that the printed book is defective.

These nineteenth-century works are historical public-domain candidates; the inspected Archive metadata did not contain an explicit `licenseurl` or `rights` field. Archive availability is not a licence for unrelated modern edited databases. Synthesis and Complete Repertory are not bundled or relabelled as these historical works.

## Import acceptance criteria

1. Fix the exact edition and source hash; verify page completeness and relevant redistribution status.
2. Transcribe every rubric, inherited/ditto path, remedy abbreviation, cross-reference and printed grading convention. Retain page evidence and unresolved cases.
3. Compare each imported association and grade with the original, recording the actual review scope. Never infer a grade from symptom-heading emphasis or invent a remedy identity from a prefix.
4. Resolve source references without silently merging different books. Record source entries separately from generated navigation headings.
5. Reject orphan hierarchy, duplicate IDs, ambiguous remedy mappings and unsupported grades; keep uncertainty out of weighted scoring.
6. Test chapter navigation, search, original grades, cross-references, mixed-book analysis, encrypted cases, backup and upgrade before enabling the edition.

No independent clinician proofreading certificate is claimed. The downloaded candidates remain outside the APK and public release assets. Only a verified database would be integrated; no PDF reader was added.
