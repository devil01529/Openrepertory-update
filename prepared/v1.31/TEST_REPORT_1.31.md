# Open Repertory 1.31 validation

529 counted browser/package/native checks passed: {"higher-grades.json": 9, "classical-sources-web.json": 23, "materia-reader.json": 210, "source-switch-guard-tests.json": 24, "scoring-compatibility-tests.json": 16, "navigation-tree-tests.json": 115, "menu-web-tests.json": 81, "browser-reliability.json": 14, "package-invariants": 28, "android-classical": 8, "android-update-retention-workflow": 1}.

Independent exhaustive source audit verifies every imported row, path, original parent, raw remedy association and weight against hashed cached API pages. It verifies scoring exclusions, duplicate handling, unique selection identities and direct reference targets. Kent files and 691 earlier data assets match 1.30 byte for byte.

Browser workflows cover imported-book loading, chapter rendering, remedy aliases, search, source-specific selection, mixed-book analysis, persistence, references, conflicting-grade notices, grades 4/5, snapshots and encrypted backups. Legacy scoring matches the published 1.23 engine. Navigation and Materia Medica regressions passed. Native classes are unchanged from 1.30.

Android classical workflow suite passed: True. Upgrade retention workflow passed: True. See attached test logs for emulator results (synthetic test records and access receipts only). No physical-device testing or printed-book scan certification is claimed, and source anomalies remain disclosed. No zero-bug guarantee.

APK SHA-256: `3b98b8c74aac3597114f1dcbc3a9afcd0dc2fffa9c5b8e7792f37ca495a9104d`
