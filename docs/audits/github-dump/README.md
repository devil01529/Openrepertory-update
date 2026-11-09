# GitHub dump comparison

Source: https://github.com/nondeterministic/oorep at ac6dbb00ba60da572675b78315cd91cda9eff089, oorep.sql.gz. The dump contains Publicum English and Kent German only. Do not equate this dump with the current hosted datasets.

The entire English Publicum dataset was compared with the APK's retained Publicum source-ID groups and exact abbreviation/grade sets: 74,667 dump rows, 75,704 packaged source IDs, 75,703 packaged groups. 226 dump IDs are not mapped in the packaged source IDs; 1,263 packaged IDs are absent from this dump. 9,724 packaged groups differ in source-ID availability or association/grade sets. These are review candidates, not 9,724 confirmed missing rubrics. IDs, versions, grouped records, aliases and navigation chapter rows need reconciliation before changing clinical content.

Full machine-readable results and checkpoint are retained locally under /workspace/github-dump-audit/publicum. No clinical records were automatically inserted or replaced. Kent English remains unchanged. No APK update was published.

Boericke is explicitly SOURCE_NOT_IN_GITHUB_DUMP, not falsely marked complete. BBCR, TPB and Murphy are also absent from this dump. Their online comparisons cannot be completed using GitHub content that is not supplied there.

The audit script processes one book per invocation. SQLite checkpoints retain committed positions in order; rerunning with the same dump and book resumes from the last committed position. Input hashes bind progress to exact input files; changed inputs require a new output directory. It does not execute SQL or read account tables. Four fixture tests passed, including repeat/resume and changed-input rejection.

Command: python scripts/audit_github_dump.py DUMP.sql.gz BOOK.json LOCAL_OUTPUT_DIRECTORY

Website samples remain unpublished and await design approval before APK data integration. The comparison PNG is a generated design concept, not a screenshot of a deployed website.
