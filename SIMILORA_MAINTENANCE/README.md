# Similora maintenance and recovery

This folder is the starting point for future developers. It records public installation/update identity, build and deployment requirements so another rebuild is not needed when a session ends.

- [Android identity](ANDROID_IDENTITY.json): package ID, version code, signature fingerprint, update verification key and repository URLs.
- [Recovery and release procedure](RECOVERY_AND_RELEASE.md): build, signing, migration, deployment and validation steps.
- [Hosting decision](HOSTING_DECISION.md): verified hosting limits and migration status.

**Never put passwords, signing keystores, private metadata keys, session tokens, user approval lists, device installation identifiers or clinical records in this public folder.** An APK package/version identifier is public; an individual user's installation ID is account data and belongs only in the secured approval service if needed.

Current production APK is 2.0.0 using the independent website account service. A Cloudflare migration is being prepared; it has not been deployed. Existing Apper approval tables remain intact. No claim is made that the current APK already uses Cloudflare or Apper.
