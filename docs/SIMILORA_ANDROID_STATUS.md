# Similora Android continuation — 9 November 2026

Repository: https://github.com/devil01529/similora-update

## Work completed

- Recovered the 660 packaged assets from verified, signed v1.41 into a separate private working directory, then applied the new Similora name and original glass S mark.
- Compared all recovered assets: eight presentation files changed; all 580 clinical data files remain byte-identical. Original author/license notices remain intact.
- Updated the native updater Java sources available in this repository: product wording, installer guidance, user-agent product label and download display name. Update identity, validation, package ID, signing certificate and metadata public key are unchanged.
- Changed future release titles to Similora. Historical APK names and signed metadata are unchanged to preserve compatibility.
- Added a repeatable branding script and regression test. Generated logo is in `integration/similora/`.

## Verified behavior

The recovered and rebranded web assets passed Chromium checks at mobile dimensions: all eight packaged repertories loaded; selecting a rubric and obtaining nonempty analysis worked for each; materia medica opened; offline finder returned results; eight selected rubrics persisted across reload. No JavaScript errors were observed. This was not an Android emulator run and does not verify native authentication, encryption, installer or live Gemini.

Branding-script repeat application preserves the original backup, clinical files and storage/backup identities. Release-retention tests passed. The existing v1.41 signed manifest and APK hashes still verify. The installed updater's old GitHub metadata URL redirected successfully after the repository rename and returned byte-identical signed metadata.

## What remains unavailable

The available repository has one branch (`main`), release APKs/metadata and partial native updater source. Its tracked history does not contain the complete Android project/build script, MainActivity source or signing keystore. Release v1.41 has no full source archive. No Actions build artifacts were returned. Access to listing repository Actions secrets returned HTTP 403; their existence or absence could not be established.

The prior recovery notes likewise record that the original development archive and Android/update-metadata private signing keys were not retained in this workspace. An APK cannot recover its private signing key.

A new installable update requires the retained full project/development archive and access to its original signing setup through a secure build environment. Do not send passwords/private keys in chat and do not generate replacement production keys. Android launcher/application labels, adaptive icons, native login screens and an in-place upgrade must be verified there before a signed release can be published.

No new APK, signed update metadata or release version was published by this work. Latest installable version remains v1.41.
