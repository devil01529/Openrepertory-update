# Open Repertory — free APK updates

Download: **[Latest release](https://github.com/devil01529/Openrepertory-update/releases/latest)**

Install the APK over your existing Open Repertory installation. **Do not uninstall or clear app storage.** The first updater-enabled release is 1.19. Future updates appear inside the app under **⋮ → App updates**.

Android asks you to allow installation and confirm the update. Existing Apper login and owner approval remain required. Public APK downloads do not grant access to clinical records.

## For the owner

Read the **[step-by-step owner and developer guide](UPDATE_OWNER_GUIDE.md)**. A developer prepares the tested APK and signed `update.json`; you can upload these files to a GitHub Release from a phone or computer. Publish a normal release, set it as Latest, and retain the exact filenames.

This repository hosts public APK release files, public verification material, update components and release tooling. It contains **no patient records, account passwords, publishing tokens or private signing keys**. It is not the full clinical app source or a standalone app build.

## Cost and behaviour

GitHub's standard free public release hosting is used, subject to its policies and service limits. Users need no GitHub account or subscription. Update checks run after approved access, at most once a day; manual checks are immediate. Downloads start when the user chooses. There is no silent installation or paid push server. Existing Apper service costs/limits remain separate.

## Release integrity

The native app pins a public RSA verification key. It validates release signatures, HTTPS destinations, download hash/size, package, version, Android compatibility and the existing APK signing identity before handing the APK to Android. Never replace a published release with different files; issue a corrected release under a higher Android version code.

Keep the existing Android keystore and the separate metadata signing key in secure owner-controlled storage, outside this repository. See the guide before changing the repository URL or verification keys.

## Verification

[Release test report](TEST_REPORT_1.19.md): 143 automated checks passed, plus an actual in-place upgrade retaining 31 encrypted test records and phone identity. Anonymous public APK/manifest/guide downloads match the tested build. The final supplementary Android live-download check was blocked by the restarted emulator’s DNS/services; modern physical-phone live download and complete update installation remain acceptance checks.

[Download the application source overlay](https://github.com/devil01529/Openrepertory-update/releases/download/v1.19/Open-Repertory-1.19-Update-Source.zip) for the complete native/menu integration changes; apply it over the retained full application source.
