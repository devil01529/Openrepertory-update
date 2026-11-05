# Packaged repertory audit — 5 November 2026

**No APK or clinical database was changed by this audit.** All eight packaged datasets were structurally checked. Live comparison is incomplete because OOREP timed out after 21 Kent pages; an additional Boericke first page was retrieved separately. Single-page and catalogue requests also subsequently timed out. Failed/absent pages are not evidence of missing rubrics.

| Packaged book | Navigation/rubric nodes | Retained source IDs | Distinct scored remedies | Scored associations |
| --- | ---: | ---: | ---: | ---: |
| Kent English | 69,744 plus 37 chapter-index rows | 68,742 | 624 | 611,027 |
| Repertorium Publicum | 75,703 | 75,704 | 666 | 719,934 |
| BBCR, earlier OCR | 10,769 | 10,769 | 140 | 115,582 |
| TPB 1846, earlier OCR | 1,641 | 1,640 | 120 | 48,090 |
| Boericke | 8,261 | 8,211 | 1,055 | 66,201 |
| BBCR, OOREP | 30,747 | 30,709 | 497 | 421,484 |
| TPB Allen 1891, OOREP | 2,924 | 2,802 | 359 | 127,315 |
| Guernsey 1892 | 556 | 518 | 138 | 1,825 |

Navigation nodes and source rows are different quantities: generated headings, grouped source IDs and separate chapter indexes affect the counts. Do not subtract these counts to infer missing clinical rubrics.

All eight datasets had zero duplicate explicit node IDs and zero scored grade values outside 1–5. This is structural validation, not proof that every grade matches the printed edition. Reference counts and grade distributions are in each `.audit.json` file. A lack of recorded references does not prove the printed book has none.

## Confirmed limitations

- Earlier BBCR OCR metadata explicitly says only the first printed remedy line was captured. Its remedy lists are incomplete and its reconstructed grades mostly unverified. The newer OOREP BBCR is separate and substantially larger. Earlier saved selections must not be silently rewritten.
- Earlier TPB 1846 omits Part VII Concordances because that section is remedy-indexed and imports grades as presence-only 1. TPB Allen 1891 is a different edition, not an interchangeable replacement.
- Boger General Analysis / Synoptic Key was removed at the owner's earlier request. OOREP currently lists both `boger` and `bogsk`, but they are not packaged datasets and were not compared as if they were.

## Actual live comparison scope

Kent: 21 of 688 pages, 2,100 source rows. No missing IDs, changed paths or changed scored associations in this sample. Chapter-index rows were included in the comparison. Kent was not modified.

Boericke: 1 of 83 pages, 100 source rows. No missing IDs, path/hierarchy changes, raw remedy-record changes or scored association changes in this sample. This first-page response reports 8,211 total source rows.

BBCR and TPB: packaged totals above match historical import reports, but a fresh exhaustive source comparison did not complete. No claim of current live equality is made. Publicum likewise has no fresh live comparison. Historical scan-derived datasets cannot be validated against a different edition by assuming matching labels imply matching content.

Raw snapshots remain local. Per-page SHA-256 evidence and bounded difference lists are in the per-book reports; arrays over 20 entries are truncated and have explicit total-count fields. IDs not observed in a partial download are not reported as erroneous extra app records.

## Murphy

The owner states permission to add requested material. No usable Murphy export was obtained. Murphy is absent from the retrieved OOREP public catalogue; GitHub repository search for Murphy repertory and an Internet Archive title/creator query returned no results. These are limited searches, not proof that no source exists anywhere. The previously located B. Jain third-edition product page is a book listing, not a graded database export. Murphy is not integrated or substituted with another book.

## Run and resume

```sh
./.audit ../recovered-verified-app/assets/www/data \
  --cache ../oorep-audit-cache --output ../repertory-audit.json \
  --live boericke1906 bbcr1905_oorep tpb1891 kent1897 kent
```

Use `--cached-only` to avoid network requests. Requests are now sequential, timeout-limited and resumable; cached files are reused. Use a fresh cache directory for a new dated audit. The command does not mutate clinical data. All 9 audit tests, 4 recovery tests and 8 retention tests passed. No Android build or runtime tests were performed in this audit.

## Integration gate

Complete live comparisons when the service responds; inspect exact source-ID differences and grades, then prepare an edition-specific patch only for verified differences. Preserve raw unsupported/conflicting values unscored and retain case identities. Build and upgrade-test with restored native source and existing signing identities before publishing a changed APK. No fabricated remedies or automatic guessed grades.
