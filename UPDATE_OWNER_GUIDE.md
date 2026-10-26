# Open Repertory: free APK updates

Repository: https://github.com/devil01529/Openrepertory-update

## Install the first updater-enabled APK

Download the APK from Releases → Latest, open it and choose Update. Keep the existing installation: do not uninstall or clear storage. Android may ask you to allow installations from the browser/files app used for this first installation.

Afterward, open Open Repertory → three-dot menu → App updates. This screen shows the installed version, the last successful check and any available release. After successful access verification, background checks happen at most once every 24 hours without delaying login. Tap Check for updates whenever you want an immediate check.

Downloads begin only when the user taps Download update. The file is verified before installation. If Android asks to allow installation from Open Repertory, enable it, return to App updates and tap Install update again. Android then asks you to confirm Update. Cancelling does not delete records, and the verified download can be retried. A failed or cancelled download restarts when retried. Keep sufficient free space for both the download and Android's installation.

Public APK downloads do not grant clinical access. Every user's Apper account and phone still require approval. The updater sends no patient data, email, account ID or phone fingerprint to GitHub. GitHub receives normal download traffic such as IP address and app version in the user agent.

## Your role: publish a prepared update from a phone or computer

A developer first makes the requested changes, builds and tests the APK, and prepares the matching signed `update.json`. Uploading an APK does not itself create features. The developer gives you four prepared files: the versioned APK, `update.json`, `RELEASE_NOTES.md` and `SHA256SUMS`.

1. Sign in to your own GitHub account in a browser. Open the repository link above.
2. Open Releases → Draft a new release. On a narrow mobile screen, use the browser's Desktop site view if these controls are hidden.
3. Choose a NEW tag matching the prepared package, for example `v1.20`. Use `main` as its target.
4. Title the release `Open Repertory 1.20`, and paste its release notes.
5. Upload all four prepared files. Preserve filenames exactly, especially `update.json` and the APK name. Wait until every upload completes.
6. Keep it as a draft until the developer/owner acceptance checks pass. Drafts cannot be downloaded by ordinary installed apps.
7. Publish it as a normal release and select **Set as the latest release**. Do not mark the public release as a prerelease.
8. On a phone with the preceding updater-enabled version, tap App updates → Check for updates. Confirm the new notes and size, then test download, installation and retained records before announcing the release widely.

Users do not need GitHub accounts or GitHub subscriptions. The repository must remain public. GitHub's current standard free public release hosting is used, subject to its policies and limits; no paid update service, Firebase service, domain or push-notification service is required. Existing Apper service limits/costs are separate.

Checks happen when the app is in use; this is not an instant background push to a closed app. Users can decline an optional update. Android requires user confirmation and may impose additional installation checks.

## Optional: publish prepared files with GitHub Actions

A free manual workflow is installed in this public repository. It handles the environment-specific upload credential limitation encountered during implementation. It uses GitHub's short-lived built-in repository token; it does not need your password, a paid server, or either private signing key.

After a developer commits the prepared public files under `prepared/v1.20/` and updates the public release configuration:

1. Open the repository → Actions → Publish verified APK release → Run workflow.
2. Keep branch `main`, enter the prepared tag (for example `v1.20`), and leave Publish unchecked to upload a verified draft.
3. Wait for a green successful run. The workflow verifies the metadata signature and APK hash before uploading.
4. Open Releases, review the draft, then publish it as Latest after acceptance checks. Alternatively, an accepted prepared release can be published directly by checking Publish when running the workflow.

The same workflow can retry an incomplete draft. It refuses to replace an already published release. Prepared files committed to this public repository are publicly visible even while the GitHub Release is a draft; they contain no patient data or private keys. Standard Linux runners for public repositories are used under GitHub's current free service policies.

## Developer: prepare releases without exposing keys

The authoritative configuration is `release-config.json`. Increase `versionCode` for EVERY release and change `versionName` (for example, `21` and `1.20-dev`). Keep the same package name and Android signing identity. Update the notes, then build, test and prepare:

```sh
bash build.sh
python scripts/prepare_release.py dist/Open-Repertory-Clarke-Dev.apk \
  --key-file /secure/private/location/metadata-private.pem \
  --notes RELEASE_NOTES.md --tag v1.20 --out releases/1.20
```

The preparation tool rejects APK/package/version/certificate mismatches and an incorrect metadata signing key. It produces only public delivery files. It requires Python 3.9+, `cryptography`, and the pinned Android build tools. The Android build also requires Java, Node and the tools described in the project's README. The small distribution repository is not a standalone build of the full clinical application; retain the full application source separately.

A developer who uses GitHub CLI may publish the prepared files with:

```sh
python scripts/publish_release.py releases/1.20 --tag v1.20 --draft
# After owner acceptance checks:
python scripts/publish_release.py releases/1.20 --tag v1.20
```

These commands use the developer's normal GitHub CLI authentication. No publishing token belongs in the APK, manifest or repository. The script refuses to overwrite a published release; use a higher version for fixes.

## Protect the two signing identities

Existing installations use package `com.openrepertory.app.dev` and the retained Android signing key. The development-labelled certificate is retained for compatibility. Its keystore password alone is not a substitute for protecting the actual key file. The updater additionally pins an independent release-metadata verification key.

Keep secure, owner-controlled backups of both the existing Android keystore and the independent metadata private key, plus the complete source. Losing either key prevents this release process from continuing normally. The build now stops when the Android key is missing instead of creating an incompatible replacement.

During implementation the retained files are outside the public repository at `/workspace/.open-repertory-release/android-existing.p12` and `/workspace/.open-repertory-release/metadata-private.pem`, with restricted filesystem permissions. Arrange secure transfer to your own encrypted storage before this workspace is retired. These private files are deliberately NOT in GitHub Releases, the APK, or the downloadable public source archive. Never attach them to a public GitHub issue or upload them as release assets.

## Release acceptance and recovery

Before publishing, verify the new APK on a modern Android phone: login, owner/member approval behaviour, search, rubric selection, analysis, patient notes, backup/restore, update download and installation. Test an in-place upgrade with synthetic records first, then confirm the owner phone retains its records. Maintain a usable encrypted backup before major record-schema changes.

If a release is faulty, remove its public availability and restore the preceding known-good release as Latest so additional users are not offered it. Publish a corrected APK with a HIGHER version code for users who already installed the faulty release. Never ask users to uninstall or clear app storage as an update fix, and never rely on Android allowing a downgrade. Keep data migrations compatible and tested; an updater cannot repair a destructive migration by itself.

## Security and limitations

The app verifies the signed manifest, APK hash and size, package/version, signing certificate and Android compatibility. Downloads use HTTPS and a restricted set of GitHub asset hosts. Only the verified APK is exposed read-only to Android's installer. No remote page can supply an arbitrary APK URL to the native bridge.

Do not change the repository URL or public verification key in place and expect already installed apps to discover it. Such changes need a compatible app update while the old channel remains available. An update-channel outage does not change Apper approval rules or erase records.

## Keeping only five versions

The repository automatically keeps the five newest published app releases. Cleanup deletes older releases and their tags oldest first and removes obsolete prepared files from the current branch. Drafts are preserved. It runs after the publish workflow, when a release is published manually, and daily. You can also run **Actions → Keep latest five releases → Run workflow**.

The script refuses to delete the release serving the latest update endpoint. If a workflow fails, inspect Actions; no automatic policy can run when GitHub Actions is disabled. Previous git commits are retained; this is release retention, not a history rewrite.


## Remembered access and source labels in 1.36

See [the 1.36 app usage guide](OWNER_GUIDE_1.36.md) for remembered-approval expiry, quiet checks, device locking, sign-out, and Legal credits. Previously verified access is bounded to 30 days when a server decision cannot be checked.
