# Open Repertory — free APK updates

## New in 1.36

Keeps book names and simplifies provider labels to **Open Repertory source**, with original credits and licences accessible separately. Adds encrypted remembered approval for up to 30 days, quiet access checks, faster startup before the full repertory loads, and fixes to source-switching, early Library use and sign-out. Clinical data, grades, images and signing identity are retained.

[Usage guide](OWNER_GUIDE_1.36.md) · [Improvement plan](IMPROVEMENT_PLAN_1.36.md) · [Validation](TEST_REPORT_1.36.md)

Download: **[Latest release](https://github.com/devil01529/Openrepertory-update/releases/latest)**

Install the APK over your existing Open Repertory installation. **Do not uninstall or clear app storage.** The first updater-enabled release is 1.19. **Version 1.35** removes the three requested repertories while preserving saved cases and adds Wolf’s complete Apis monograph. From an updater-enabled version, open **⋮ → App updates → Check for updates** to get it.

Android asks you to allow installation and confirm the update. Existing Apper login and owner approval remain required. Public APK downloads do not grant access to clinical records.

## For the owner

Read the **[step-by-step owner and developer guide](UPDATE_OWNER_GUIDE.md)**. A developer prepares the tested APK and signed `update.json`; you can upload these files to a GitHub Release from a phone or computer. Publish a normal release, set it as Latest, and retain the exact filenames.

This repository hosts public APK release files, public verification material, update components and release tooling. It contains **no patient records, account passwords, publishing tokens or private signing keys**. It is not the full clinical app source or a standalone app build.

## Cost and behaviour

GitHub's standard free public release hosting is used, subject to its policies and service limits. Users need no GitHub account or subscription. Update checks run after approved access, at most once a day; manual checks are immediate. Downloads start when the user chooses. There is no silent installation or paid push server. Existing Apper service costs/limits remain separate.

## Release integrity

The native app pins a public RSA verification key. It validates release signatures, HTTPS destinations, download hash/size, package, version, Android compatibility and the existing APK signing identity before handing the APK to Android. Never replace a published release with different files; issue a corrected release under a higher Android version code.

Keep the existing Android keystore and the separate metadata signing key in secure owner-controlled storage, outside this repository. See the guide before changing the repository URL or verification keys.

## New in 1.35

Removes Clarke, Knerr and Boger repertories from the APK. Existing saved rubric evidence, source IDs, grades, analysis, snapshots and exports remain usable. Kent and the seven remaining repertory databases are unchanged.

Adds C. W. Wolf’s complete *Apis Mellifica* (1858), with 12 original sections, to Materia Medica. Existing library passages remain intact. Online alternative repertory sources were researched; none was bundled without a verified rights and content audit.

[Release notes](UPDATE_RELEASE_NOTES_1.35.md) · [Repertory alternatives](REPERTORY_ALTERNATIVES_1.35.md) · [Materia Medica addition and research](MATERIA_ADDITION_1.35.md) · [Validation](TEST_REPORT_1.35.md)

## Release retention

Only the **five newest published versions** are retained. Older releases and tags are removed in chronological order, oldest first, along with their prepared binaries on the current branch. Cleanup runs after verified publication, on manually published releases, and daily. Draft releases and the latest update endpoint are protected. Git commit history is not rewritten.

For older releases still retained, use the [releases page](https://github.com/devil01529/Openrepertory-update/releases). Links to deleted historical versions will no longer download.
