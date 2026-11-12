# Recovery and release procedure

## Preserve the identity

Keep `org.similora.app`, the APK signing key and the update metadata signing key. Increment versionCode above every published build; after 2.0.5/code 6, use at least code 7. Do not generate replacement keys. The old `com.openrepertory.app.dev` lineage cannot be upgraded with the new key.

Native source: `android-rebuild/src/org/similora/app/`. Manifest/resources/build scripts/tests: `android-rebuild/`. Complete instructions: [Android build guide](../android-rebuild/README.md). Clinical assets are recovered from hash-pinned prepared v1.41; keep that baseline privately before release retention eventually removes it. Do not overwrite book data, grades or original required notices during a hosting change.

## Private material to retain

The original new-app signing bundle is in `/workspace/.similora-signing/` in the current build workspace. The owner handoff archive is `/workspace/similora-owner-private/Similora-PRIVATE-signing-backup.zip`. These workspace paths are not permanent backups. Download and retain it in owner-controlled encrypted storage. It contains `similora-release.p12` (alias `similora`), `store-password`, `update-private.pem` and public verification files. Never commit or publish the archive.

Loss of the APK signing key prevents compatible updates. Loss of the metadata signing key prevents updates trusted by the installed updater. Public certificate fingerprints cannot recreate private keys. GitHub secret writes were denied by the current connector; automated private signing is not configured. Release upload automation is configured and uses GitHub's scoped workflow token.

## Build and publish

1. Restore the private signing material securely; compare public certificate and metadata key with ANDROID_IDENTITY.json.
2. Bootstrap the pinned Android tools; prepare the v1.41 assets and Similora overlay using the scripts in android-rebuild.
3. Increase manifest/config version code and name; keep package and authority unchanged. Ensure build output filename follows the version.
4. Build, verify APK signature, run applicable native and browser regressions. Confirm approval/revocation, account isolation, encrypted persistence, backups, eight book workflows, Materia Medica and updater validation.
5. Sign new metadata using `android-rebuild/sign_update.py`. Upload the APK and checksum before activating the matching main-branch manifest.
6. `publish-similora.yml` is currently pinned to v2.0.5. Adapt its tag/paths for a future version, keep signature/hash verification, and never overwrite published assets. Its existing workflow succeeded for v2.0.0. Direct connector uploads failed; GitHub Actions upload works.
7. Preserve `--latest=false` while the legacy v1.41 endpoint is needed. Keep the two signing/update channels distinct. Retention keeps five published versions and refuses to delete the protected Latest endpoint. Revisit this policy before the legacy version would fall outside those five; never silently break existing users.
8. Verify the publicly downloaded bytes and manifest, update ANDROID_IDENTITY.json and the release test report.

## Account-provider migration

A change of hosting does not require a new APK package. Reuse the signing keys and send the next version through the existing updater. Keep the current account service operating until the new authentication/approval service and APK have passed live tests.

Preserve website account IDs when migrating the independent account database so encrypted APK vaults remain associated with the same identity. Do not map identities solely by an unverified email address. If identity migration cannot be authenticated, require an explicit encrypted-backup migration and keep the old vault intact. Test an installed 2.0.0-to-new-version upgrade with synthetic records before publication.

Use the same secured owner panel inside the APK for both website and app approvals. Enforce owner role and revocation on the backend; hiding buttons is not authorization. Do not upload patient/case data to the account database. Browser cases remain local and initials-only with the five-case confirmation rule.

## Deployment automation target

Cloudflare account authorization is a one-time owner action. The planned workflow uses protected GitHub deployment credentials, Workers + D1 on Free and a free workers.dev address. Routine source pushes will run tests and deploy without manual setting copy/paste. The workflow and account migration are deployed; see CLOUDFLARE_OPERATIONS.md. No paid upgrade or domain purchase was performed.

Retention reserves one of the five slots for the active legacy Latest endpoint (v1.41), and keeps the newest four other releases once that endpoint is older than the latest five. This avoids breaking the still-signed legacy update URL while capping release count at five. Deletions of unprotected versions remain oldest first.
