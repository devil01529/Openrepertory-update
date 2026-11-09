# Origin trace and audit queue

This trace reads actual packaged metadata and source hashes, rather than assuming every dataset came from the GitHub dump.

- English Kent, Boericke, structured BBCR and Allen TPB: hosted OOREP API snapshots, with original source IDs, remedy IDs, raw grades and page hashes retained. These editions are not supplied in the public GitHub SQL dump. Re-fetch the hosted edition or obtain an edition-matched original scan; a different book is not a valid replacement.
- English Publicum: OOREP-derived dataset. Full GitHub dump comparison completed separately; snapshot discrepancies require review.
- Earlier BBCR: user-supplied Boger 1905 OCR extraction. Metadata does not identify an exact scan item or checksum. Do not invent an archive source. Known first-line remedy extraction defect remains documented; structured BBCR is the preferred separate source.
- Earlier TPB 1846: Digital Library of India / Internet Archive item in.ernet.dli.2015.93043. The source describes Parts I–VI; Concordances intentionally omitted. Grade 1 is presence-only. A different Allen 1891 edition cannot certify it.
- Guernsey 1892: Countway / Medical Heritage Library scan homoeopathicther00guer, also Wellcome item ybt6pchf. Packaged metadata retains PDF hash and the twenty reviewed repertory pages. This is AI-assisted historical transcription review, not independent clinician certification.

Active hosted audit is Boericke: 35/83 pages compared, 3,500 rows, no differences in retrieved pages. Full completion is still blocked by source timeouts. Audit the remaining pages before starting the next live book. The trace is complete for available metadata; it does not claim the missing original scan for earlier BBCR was located.

Provider names are provenance/legal information. Calling a dataset “Open Repertory source” does not turn proprietary editions into open-licensed data or erase required attribution. Clinical UI can use neutral labels; original credits remain available through legal information. Browser-delivered data cannot be made unextractable by hiding links.
