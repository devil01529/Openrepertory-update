# Similora branding — prepared, not an APK release

Similora is the new product name. The original generated mark uses a glass S in sage and champagne on midnight teal. `similora-mark-v1.png` is the master image.

Run `python3 apply_branding.py /path/to/apk/assets/www` against the retained complete Android project. It changes known presentation strings and the web header mark, preserving source JSON, original notices, database keys, backup format and update URLs. It creates local before-similora copies for changed assets; move those copies outside the packaging tree before building.

In the actual Android project, set the launcher/application label resource (normally `app_name`) to `Similora`. Generate launcher assets from this mark using Android Studio Image Asset, inspect both legacy and adaptive round/square masks, and update native login, account, update-dialog and report product labels. Keep the existing package `com.openrepertory.app.dev`, keystore, update verification key and storage identities. Renaming a package would install a separate app and break continuity.

Product and neutral provider-display labels become Similora / Similora source. Original book authors, provider attribution, licenses and historical notices remain accurate and available in Legal information. This migration does not assert Similora authored the underlying historical books or imported databases.

Build and sign with the existing private signing setup; verify an upgrade preserves login, approvals, encrypted records, library data, case analysis, backups and updater validation. Only then increase the release version and publish signed APK/update metadata. This repository still lacks the complete buildable Android project and its signing setup, so this commit is an integration package only. No APK release or update manifest was changed.

## Repository renaming

Website and Android update repositories remain separate. After renaming on GitHub, update the website Railway source mapping and maintainer remotes. Keep existing Android update-feed URLs working for installed versions, then migrate them with a correctly signed release. Do not rewrite signed metadata or verification keys to rename the product.
