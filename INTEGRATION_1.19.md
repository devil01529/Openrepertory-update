# Android updater integration in Open Repertory 1.19

The delivered APK implements these changes in the full application workspace. This public repository contains the updater modules and release tooling; the release source overlay contains all changed application files.

- `AppUpdates` owns optional checks/downloads, pinned signed manifests, file validation, private cached APKs and Android installation.
- `UpdateFileProvider` exposes exactly one read-only APK URI using temporary installer grants. The Android manifest declares this non-exported provider and REQUEST_INSTALL_PACKAGES.
- `ImprovedActivity` constructs the updater, permits UI access only for approved unlocked foreground clinical use, schedules checks after workspace readiness, flushes drafts before installation, dismisses update dialogs on pause/revocation, and disposes its worker at activity destruction.
- The existing restricted clinical bridge adds only `openUpdates()`; callers cannot provide URLs, files or verification keys. The remote Apper WebView receives no new installation bridge.
- The clinical menu adds `App updates`, connected in `web-src/js/app.js`. Other clinical UI and all database/text assets retain their previous behaviour.
- `release-config.json` is the shared package/version/repository/public-key configuration. The build generates `UpdateConfig.java`, retains the existing keystore and verifies the produced APK identity. A missing signing key stops the build.
- GitHub Releases is a separate public delivery channel. No Apper backend schema or permission changes are needed, and no clinical records are uploaded.

Build/source overlay instructions and private-key custody requirements are in UPDATE_OWNER_GUIDE.md. Never put a private key in the release overlay.
