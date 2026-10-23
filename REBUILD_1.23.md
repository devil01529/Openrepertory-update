# Open Repertory 1.23 source overlay

Apply over the retained full clinical app source and preserve its existing data assets and signing identity. This overlay includes maintained native/web source, resources, the added English Kent/relationships/audit data, and tests/build tools. It does not include the private Android keystore or metadata signing key.

See INTEGRATION_1.23.md for build/test prerequisites; CLINICAL_UI_GUIDE_1.23.md for usage; KENT_OOREP_AUDIT_1.23.md for the source comparison; TEST_REPORT_1.23.md for 500 passing checks, upgrade retention and limitations. The separate clinical-only audit snapshot ZIP provides the original Kent API rows and page-hash manifest. The APK also contains the retained old clinical JSON/text assets.

Build with bash build.sh using release-config.json and the pinned tools. Do not replace the existing signing key for installed users. Publish higher version codes through the verified prepared-release workflow.
