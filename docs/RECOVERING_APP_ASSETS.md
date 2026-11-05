# Recover packaged app assets

Run from a clone of this repository:

```sh
python3 scripts/recover_app_assets.py ../open-repertory-recovered
```

Python 3 and OpenSSL are required. The script verifies the configured release's signed metadata and APK hash before extracting assets. It rejects existing destinations and destinations inside this public repository. The output contains packaged JavaScript, HTML, CSS, images, clinical data and an asset hash inventory; keep it local. It is not the original maintained source tree, native Android source, a buildable project or a signing-key backup.

Validation on 5 November 2026: four extraction tests and eight existing release-retention tests passed. Signed v1.41 verification passed; 660 assets were recovered. This does not constitute testing a new APK.

The next work is tracked in [the continuation plan](CONTINUATION_PLAN.md). Murphy's repertory has not been imported: no verified, redistribution-authorized export has been obtained. Existing signing keys and native app source must be securely restored before a compatible release can be built. No replacement signing identity should be generated for the existing update channel.
