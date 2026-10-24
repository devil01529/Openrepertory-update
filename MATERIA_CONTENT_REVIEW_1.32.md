# Materia Medica content review — 1.32

## Release decision

Quality takes precedence over volume count. This update freshly imports 78 distinct remedy monographs from two digitally proofread editions: Mure (24) and Anshutz (54). Seven exact-name chapters are newly available. Every previous supplemental remedy/source pairing is retained, with the duplicate Echinacea import removed. Kent and every other repertory dataset, remedy identities and grades are unchanged.

337 suspect OCR passages are withheld from reading and differential evidence. Their original bytes, hashes and reasons remain in a recoverable audit archive outside the APK. The 870 remaining classical OCR excerpts are explicitly labelled **not fully proofread**. Automated screening cannot certify the remaining text as error-free. No new unproofread repertory has been added.

## Admission criteria

An addition needs an identified edition, a redistributable source, evidence of transcription proofreading, complete chapter boundaries, an unambiguous remedy identity, preserved wording/footnotes, and reproducible source hashes. Repertories additionally need auditable rubric hierarchy, remedy associations, original grading and cross-references. We do not fabricate grades or synonyms, mix editions silently, or call a larger database better merely because it contains more rows. This is a release quality threshold, not a claim that a particular total is universally optimal.

## Fresh proofread sources

- [Mure, 1853 — Project Gutenberg 70350](https://www.gutenberg.org/ebooks/70350): credits Bryan Ness and the Online Distributed Proofreading Team. All 38 remedy chapter headings inventoried; 24 uniquely match existing remedy names. The other 14 remain unassigned. Chapter-local references to the book's endnotes are included.
- [Anshutz, 1900 — Project Gutenberg 38757](https://www.gutenberg.org/ebooks/38757): credits Bryan Ness, Pat McCoy and the Online Distributed Proofreading Team. The body contains 88 remedy/chapter headings, including a combined Bacillinum/Tuberculinum/Aviaire chapter; 54 uniquely map, 34 remain unassigned. The old importer reported 90 contents entries; a contents count is not a count of independent monographs. Nested sections, such as “Cases of Spider Bite”, are retained inside their owning chapter.

Source HTML is archived unmodified. The app uses plain-text excerpts: words, punctuation, numbered symptoms, paragraph boundaries and referenced footnotes are preserved; printed illustrations and font styling are not reproduced. Upstream proofreading is credited accurately; this update checks extraction and mapping, and does not claim a new scan-by-scan proofread. Every source chapter and omission is listed in `review-report.json`. One explicit dictionary spelling exception retains the established `Cuph.` identity for Cuphea viscosissima; no repertory name or ID is rewritten.

## Boericke

All 688 structured Boericke records were checked for unique book/remedy identity, nonempty sections and obvious wrapper contamination; none failed those structural checks. Ten sample entries were compared with [Homeoint's Boericke transcription](https://www.homeoint.org/books/boericmm/): Abies canadensis, Aconitum, Belladonna, Arsenicum, Nux vomica, Phosphorus, Lycopodium, Sulphur, Pulsatilla and Natrum muriaticum. In-order word-token coverage ranges from 98.42% to 100%; punctuation, navigation, edition and transcription differences are possible. This is not an accuracy percentage or proof that every passage is complete.

The bundled source is catalogued as 1906. Independent online texts and scans may be different editions; edition equivalence and full proofreading remain unestablished. No unsupported wholesale Boericke replacement was made. The UI says “Structured transcription · full proofreading not established”. The incorrect blanket claim that the book inherits the OOREP software GPL license has been removed; the source marks the book Public without a separate book license.

## Existing volume screening

Every existing volume group was included in structural/extraction screening. These are **partial extracts**, not complete volumes. Confirmed examples include Clarke's Hydrophobinum entry starting in a contents list before Abies canadensis, Clarke's Zincum valerianicum capturing contents before Iberis, Allen passages continuing into other remedy monographs, and downloaded Archive.org scripts appended to OCR. Running page headers naming a different remedy are also held for review; a flagged entry is not automatically declared entirely false or trimmed at a guessed boundary.

| Classical source | Before | Retained OCR | Withheld |
|---|---:|---:|---:|
| Allen · vol. 5 | 26 | 8 | 18 |
| Clarke · vol. I | 176 | 98 | 78 |
| Boger · 1915 | 245 | 242 | 3 |
| Allen · vol. 1 | 56 | 44 | 12 |
| Allen · vol. 10 | 149 | 102 | 47 |
| Hahnemann · Pura vol. 1 | 6 | 1 | 5 |
| Nash · 1913 | 55 | 40 | 15 |
| Hahnemann · Chronic Diseases | 25 | 12 | 13 |
| Allen · vol. 2 | 45 | 31 | 14 |
| Allen · vol. 3 | 40 | 24 | 16 |
| Hahnemann · Pura vol. 2 | 7 | 2 | 5 |
| Allen · vol. 4 | 53 | 29 | 24 |
| Clarke · vol. II/1 | 83 | 71 | 12 |
| Clarke · vol. II/2 | 111 | 91 | 20 |
| Hahnemann · Pura vol. 3 | 6 | 0 | 6 |
| Allen · vol. 6 | 26 | 14 | 12 |
| Allen · vol. 7 | 26 | 13 | 13 |
| Hahnemann · Pura vol. 4 | 8 | 6 | 2 |
| Allen · vol. 8 | 36 | 23 | 13 |
| Allen · vol. 9 | 21 | 12 | 9 |
| Hering · Guiding Symptoms · Vol. I | 2 | 2 | 0 |
| Hering · Guiding Symptoms · Vol. II | 2 | 2 | 0 |
| Hering · Guiding Symptoms · Vol. III | 1 | 1 | 0 |
| Hering · Guiding Symptoms · Vol. VII | 1 | 1 | 0 |
| Hering & Farrington · 1877 | 1 | 1 | 0 |

Hering 1872: all 17 previously bundled passages structurally screened and retained byte-for-byte, with OCR review status shown. Its Nux moschata introduction's “table of contents” wording is not by itself treated as a bad assignment. Boericke, Mure and Anshutz are discussed above. This covers all 29 bundled source/volume groups. A full printed-page proofreading audit of the retained OCR remains outstanding.

## Other internet sources considered

| Source | Evidence and decision |
|---|---|
| [NLM / Hull's Jahr](https://collections.nlm.nih.gov/catalog/nlm%3Anlmuid-101310420-bk) | Public-domain scan available; no verified, proofread structured import established. Held. |
| [Farrington, Library of Congress](https://www.loc.gov/item/08020584/) | Historical scan available; raw OCR alone does not pass the admission criteria. Held. |
| [Wellcome Boericke](https://wellcomecollection.org/works/f7tpbxbb) | Later-edition scan; not silently mixed into the 1906-labelled source. |
| [Homeopathy Library](https://homeopathylibrary.com/about) | Historical texts are described as public domain, while site-specific work is reserved; no verified proofread export selected. |
| [CurAnon's library](https://curanon.com/library/) | Source-oriented browsing is useful for comparison; no suitable verified redistribution export selected. |
| [Hpathy attribution](https://hpathy.ai/attribution/) | No authorized bulk, proofread dataset established for this import. |
| OOREP Boericke, BBCR and TPB repertories | Already added with owner-confirmed permission and full API-record comparison in 1.31. Not confused with proofread printed-book editions. Retained unchanged. |
| Phatak, Synthetic and Complete | No verified authorized structured exports established; not bundled from unverified downloads. |

## Chapter illustrations

The same offline picture set used in Kent now covers every chapter in the ten installed repertories, including the new Boericke, BBCR and TPB. Explicit subject mappings handle Eyes/Eye, Ears/Ear, different head and limb divisions, respiratory and urinary systems, and source-specific names. General subjects use the general-body picture; pictures are navigation cues, not anatomical diagnostics. Chapter labels, hierarchy and data are unchanged. Lazy loading and fixed image dimensions avoid layout jumps; selected-chapter and drawer pictures agree.

## Using the update

Install 1.32 over the existing APK or open Menu → App updates. Do not uninstall: updating retains the existing signing identity and encrypted records. In Repertory, open the chapter drawer to see the restored pictures. In Library, select a remedy and an author tab: proofread-source/OCR status appears beneath the source title. A compact notice explains withheld passages; your own notes and repertory links remain available. Mure and Anshutz include an offline “Transcription license and credits” dialog.

## Next content phases

1. Review the archived boundary failures against identified printed editions, remedy by remedy; restore only passages with confirmed start/end boundaries and checked wording.
2. Establish the exact Boericke source edition, then compare all 688 monographs to its scans. Keep an explicit correction ledger; the ten web samples do not replace this work.
3. Expand the proofread imports only after resolving the 48 unmatched chapter identities with source-recorded aliases or a reviewed dictionary extension. Keep combined chapters clearly labelled.
4. Admit additional repertories only after hierarchy, remedies, original grades, references, rights and completeness can be audited. Quantity alone does not trigger replacement.
