# English Kent / OOREP database audit

Audit completed 22 October 2026 against the public OOREP API at https://www.oorep.com/. This verifies the imported database against that electronic source; it is not a scan-by-scan audit of a printed Kent edition.

## Source identity correction

The existing app key `kent` points to **Repertorium Publicum**, authored by Vladimir Polony (2008), sourced from OOREP's GPL database. OOREP separately serves **Kent's Repertory**, English, with API abbreviation `kent`, author J. T. Kent and the API's edition metadata “6th American Edition”. These are different compilations.

The APK now includes English Kent under the distinct key `kent1897`. The old `kent` key, Publicum database bytes, saved rubric UIDs, case weights and remedy selections are retained. No existing case is converted silently. The source picker names the books separately.

OOREP's public source-code dump does not contain the separate English Kent book. Its FAQ distinguishes its bundled GPL datasets from the other hosted books. The owner explicitly confirmed permission to redistribute the English Kent dataset in this task. The source metadata records that confirmation and does not claim that OOREP supplies a GPL license for this separate dataset.

## Exhaustive comparison

| Check | Result |
|---|---:|
| API pages cached, read sequentially | 688 |
| API-declared source rows | 68,742 |
| Unique imported original source IDs | 68,742 |
| Missing original source IDs | 0 |
| Unexpected original source IDs | 0 |
| Original paths compared verbatim | 68,742 |
| Original remedy records and grades compared | 624,010 |
| Chapters | 37 |
| Chapter index rows retained in chapter metadata | 37 |
| Original rubric rows in the reading lists | 68,705 |
| Synthetic, non-scoring navigation headings | 1,039 |

The reading tree therefore contains **69,744 displayed nodes**, including the synthetic headings. This count is different from the original source-row count; it does not mean the book gained or lost source rubrics. The 37 chapter index rows are retained separately as chapter metadata. Each original ID occurs exactly once across the tree and chapter index metadata.

Every original remedy abbreviation and its grade is compared against the cached API row. None of these records is merged with Publicum or another repertory. API totals are checked on every page; duplicate source IDs across pages and incomplete downloads cause the importer to stop. The completed snapshot has per-page SHA-256 evidence.

## Differences from the previously bundled Publicum book

After normalising only case and whitespace, 64,058 Kent source paths occur in Publicum. Another **4,684 Kent paths** do not occur there, including chapter index entries. **9,746 shared paths** have different remedy lists or grades. These figures describe differences between two compilations; they are not a reason to overwrite Publicum's source data with Kent values.

The complete machine-readable report, including the unmatched paths and page hashes, is bundled as `data/kent_oorep_audit.json`.

## Duplicate-path exception

OOREP contains two distinct records for **Extremities, clenching, fingers**: IDs **15359** and **65440**. Their remedy lists differ; Lachesis is grade 1 in the first and grade 2 in the second.

Both records and their exact grades are preserved. The app labels each with its source row ID and explains the difference. Each version can now be selected independently, retaining its own grades and row identity. No arbitrary “correct” grade has been substituted.

## Cross-references and synonyms

All **five explicit Kent parenthetical cross-reference occurrences** are integrated and resolve to existing nodes in the same book:

- Face → Eruptions → Smarting: compare Burning.
- Mind → Memory → Weakness → Mental labour, and its Fatigue child: compare Stupefaction.
- Mind → Rudeness, and its Ailments-from child: compare Insolent.

They appear beside the rubric and in its Source sheet. Following a reference opens the exact target; **Previous rubric** returns to the originating search/reading position. Following references does not select or score a rubric automatically.

All **103 source-recorded alternate remedy names** in the Kent snapshot are integrated into repertory search, library search and remedy profiles. These are **remedy synonyms**, not authorisation to treat different rubrics as equivalent. This API snapshot has no separate rubric-synonym table or explicitly labelled rubric-synonym entries. None has been invented. Existing patient-word search suggestions remain a separate application feature.

Publicum also receives its three explicit reference occurrences and **229 alternate remedy names** from the GPL remedy dictionary through a metadata sidecar. Its clinical database stays byte-identical.

## Reproducing the audit

The release includes a clinical-only Kent audit snapshot ZIP containing the original aggregated API rows and page-hash manifest. It contains no patient records, login information or authentication tokens.

Use `scripts/audit_oorep_live.py` to obtain a new, resumable snapshot; it sends one request at a time, spaces requests, honours retry delays and stops on access rejection. New snapshots may differ if OOREP changes its database. Use `scripts/integrate_kent_oorep.py` for import and `tests/kent_oorep_audit.py` for the independent exhaustive comparison. The source overlay includes the added compact APK datasets and the audit tools; retained old assets and the existing signing keys are needed for a full application rebuild.

Primary references: [OOREP](https://www.oorep.com/), [OOREP FAQ](https://www.oorep.com/faq), [official OOREP source/schema](https://github.com/nondeterministic/oorep).

Version 1.24 permits selections from different repertories and removes the earlier duplicate-version selection restriction. Each source row and its grades remain separate. Selecting two related rows contributes two independent entries; no source grades are merged or rewritten.
