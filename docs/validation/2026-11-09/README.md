# Packaged app checks — 9 November 2026

All eight packaged repertories loaded in Chromium at a 412×915 viewport. A remedy-bearing rubric from each book was added to the same case and produced nonempty rankings. All eight selections survived a page reload in browser storage. No page JavaScript errors occurred. Materia Medica opened Aconitum's profile with 9,899 rendered characters. The phrase finder completed “fear of death” with neither Gemini nor Claude connected, using the existing offline phrase interpreter.

These checks exercise recovered v1.41 web assets. They do not verify every rubric, every user interaction, Android encrypted storage, Apper approval, native lifecycle, APK installation or live Gemini. Browser storage persistence is not evidence of Android encryption testing. No new APK was built or published.

## AI without an API

The packaged app already supports local phrase matching and local database lookup without an API key. This is not a generative language model. Gemini uses a native bridge, encrypted API-key storage and a review/consent step before sending text. A live provider test requires valid credentials and the native bridge; it was not run here. Provider authentication cannot be removed to obtain API-free Gemini. Existing integration is retained.

A future on-device generative model would require native model runtime integration, a redistributable model, storage/RAM profiling and device tests. It cannot be claimed implemented by changing a JavaScript provider label.

## Source audit continuation

The resumed retrieval ended with source timeouts, but retained more valid pages. Cached comparison now covers 1,100 Boericke rows (11/83 pages), 600 BBCR rows (6/308 pages), and 400 Allen TPB rows (4/29 pages). No missing source IDs, changed paths, raw remedy-association differences, scored-association differences or hierarchy differences were found within those samples. The complete source audit is unfinished. Counts and page hashes are in `source-sample.json`.

The full local totals and known older OCR limitations remain in [the eight-book audit](../../audits/2026-11-05/README.md). Do not confuse the common remedy dictionary's 2,648 entries with the distinct remedies actually used in each book. Older BBCR has incomplete remedy lines; older TPB uses presence-only grades. Different editions cannot be merged by assuming their paths and scales are equivalent.

No new missing source records have been verified for a data patch. Clinical databases are unchanged. The repository still needs the full native build source and existing signing identities restored securely before a compatible Android update can be released.

## Reproduce

Serve recovered `assets/www` locally, then run:

```sh
python tests/browser/packaged_smoke.py --url http://127.0.0.1:8765 --output /tmp/packaged-smoke.json
```

Requires Python Playwright and Chromium at `/usr/bin/chromium`. Use synthetic cases only. The test modifies its isolated browser profile and does not contact live accounts or transmit patient data.
