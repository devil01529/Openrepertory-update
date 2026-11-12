# Similora Android — new independent build

This directory contains the complete native source and build scripts for **org.similora.app**, version **2.0.7**. It is a new signing lineage and installs alongside `com.openrepertory.app.dev`; it does not overwrite or automatically decrypt the old app's records.

## Accounts and privacy

Use the **Similora website account**, not the former APK's separate Apper credentials. The Cloudflare Workers/D1 service performs authentication and owner approval for both website and APK. Account & access opens inside a separate WebView with no JavaScript storage bridge. Owners see the same account approval/removal controls as the website. Website approval now grants access to this rebuilt APK as requested.

Clinical records, patients, notes and Gemini keys stay in account-scoped Android Keystore/AES-GCM encrypted local storage. They are not uploaded to the account service. The app requires a connection to verify approval at startup/resume and periodically while open. A remembered website session avoids daily password entry; choose **Stay signed in for 30 days** on your private phone. If connection checking fails, access pauses rather than assuming approval.

Existing screenshots remain allowed by default; Menu → Mobile security controls capture while the app is active. Background/locked screens are protected. Native file pickers export/import backups without requesting broad storage permissions.

## Build

Requirements: Python 3.9+, Java 17+ runtime, OpenSSL, original *new Similora* signing files held privately. No Gradle dependency resolution is required: the pinned official Android SDK tools, Eclipse compiler and D8 build the native source directly.

```sh
python3 android-rebuild/bootstrap_tools.py /private/android-tools
python3 android-rebuild/prepare_assets.py /private/similora-assets
python3 android-rebuild/build.py --tools /private/android-tools --assets /private/similora-assets/assets --signing /private/similora-signing
```

The signing directory must contain `similora-release.p12` (alias `similora`) and `store-password`. The independent update metadata private key is `update-private.pem`. **Never regenerate these for an update. Never commit them or their passwords.** The public fingerprint and update verification key are in `release-config.json` and `UpdateConfig.java`.

The baseline v1.41 APK is hash-pinned. The recovered 580 data files are checked byte-for-byte. Only product presentation and the mobile security menu change; required original book/provider notices remain intact. If v1.41 is eventually pruned from release history, retain its private build baseline or use a verified later asset archive with a reviewed preparation-script change.

## Update channel

New app: `android-rebuild/similora-update.json` on the canonical repository's main branch, signed with the new metadata key. APK URLs must point to that repository's release assets, and installer checks require the new package, version and certificate. Old v1.41 metadata and its trusted signing identity are kept separate. Publish the new release with `--latest=false` so the legacy `/releases/latest/download/update.json` endpoint continues working.

## Migration

1. Keep the old APK installed.
2. Export a password-encrypted backup from its Backup & restore screen.
3. Install Similora as a second app; sign in with your website account and obtain owner approval if needed.
4. Import the encrypted backup, enter its password, and verify patients, cases, selected rubrics and notes before removing the old app.
5. Re-enter the Gemini API key if you use online AI; keys are not included in clinical backups.

Backup format is retained, but a complete old-native-to-new-native migration still requires a device check with a non-sensitive sample. Do not uninstall the old app until your own records are verified.

## Test scope

See `TEST_REPORT.md` for actual results and remaining limits. Native instrumentation is a separate QA APK under `tests/`; it never ships inside the production app. Live test session fixtures, local case data and signing keys are excluded from Git.

## Signing continuity and owner backup

The new signing material is held only in the private build workspace (`/workspace/.similora-signing/`). Back up that entire directory into owner-controlled encrypted storage before the workspace expires. It includes the APK keystore/password and the separate update metadata key. Public GitHub contains only public verification material. GitHub Actions secret configuration was denied by the current connector (HTTP 403), so automated release signing is **not configured**. Never upload those files as release assets or repository files.

The test build accepts `SIMILORA_ANDROID_TOOLS` and `SIMILORA_SIGNING_DIR`; set `SIMILORA_TEST_SESSION_FILE` only for a synthetic private test fixture. Never publish the QA APK. Android 9 software emulation is functional coverage, not a phone-performance measurement.

## Publishing a future version

Increment the Android manifest version code and name, and `release-config.json`. Build and run the applicable checks. Sign `similora-update.json` with `sign_update.py --apk … --signing … --notes …` using the **same** private metadata key. Upload the signed APK and checksum to its versioned GitHub release, then publish the matching signed metadata on main. Preserve `--latest=false` while the old app's latest-release endpoint is still supported. Do not change the old root `update.json` or `release-config.json` to the new package.

For the hosting migration and owner controls, see [Cloudflare operations](../SIMILORA_MAINTENANCE/CLOUDFLARE_OPERATIONS.md). Updating 2.0.0 to 2.0.1 keeps the package, signer and encrypted vault. Sign in once at the new hosting address.

Startup 2.0.3 plays the full intro once before revealing ready content. A slower workspace shows loading controls after completion. The footage edges blend into the screen background; account navigation does not replay the intro.

Your AI setup, security boundaries, supported providers and test limits are documented in [Your AI guide](../SIMILORA_MAINTENANCE/YOUR_AI_PLAN_AND_GUIDE.md).
