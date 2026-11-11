Similora 2.0.1 — unified website and APK approvals

- Moves the rebuilt APK's login and approval service to the free-tier Cloudflare deployment. Website: https://similora.similora-167bde6ed1.workers.dev
- Owner: open Menu → Account & access to approve, reject or revoke users for both website and APK. No ChatGPT, Apper dashboard or repeated console copying is needed.
- Existing Similora website accounts, passwords and approvals are retained. Sign in once at the new address; choose Stay signed in on your private device. Approval is still checked when opening/resuming the app.
- Same package and signing identity as 2.0.0. Install as an update; do not uninstall or clear app data. Encrypted phone records are retained.
- Signed App updates are now also available on the connection-error cover, without granting access to clinical records.
- All 661 bundled clinical/UI assets are unchanged from 2.0.0. No repertory content or grading changes.
- Recovery/build/hosting information is stored in SIMILORA_MAINTENANCE in GitHub. Free hosting remains subject to Cloudflare's quotas.

This updates org.similora.app (2.0.0). Legacy com.openrepertory.app.dev installations still need the separate Similora install and encrypted-backup migration; their signing key is different.
