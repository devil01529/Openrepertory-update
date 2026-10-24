# Security policy

## Report vulnerabilities privately

Do not open a public issue containing credentials, patient information, unpublished source code, signing keys, or exploit details. Contact the repository owner privately using GitHub's private vulnerability reporting feature if enabled, or another trusted private channel agreed with the owner.

## Public update channel

This repository is a public update distribution channel. Public APKs and signed metadata must remain downloadable without login for installed app updates. **Do not put proprietary source trees, internal audit archives, patient records, private signing keys, secrets, keystores, or credentials into this repository, release assets, commit history, workflow logs, or artifacts.** Keep proprietary app development in a separately access-controlled private repository.

The Android signing key and release-metadata signing private key belong in secure owner-controlled storage, not GitHub. Never regenerate or replace signing identities casually because existing users depend on them. Treat compromised credentials as an incident requiring rotation and an upgrade/migration plan.

## Release safeguards

Only publish versioned APKs after a successful verification run. Preserve signed `update.json`, APK SHA-256, package identity and signing certificate checks. Never replace the bytes of a published release; use a new version code for corrections. Review proposed workflow changes and pin third-party Actions to immutable commit SHAs.

## Account and repository safeguards

Enable two-factor authentication, require approval for collaborator access, keep GitHub tokens short-lived and least-privileged, enable available secret/dependency/code scanning and push protection, and apply a GitHub ruleset to protect `main` from force pushes, deletions and unreviewed changes. Protect `.github/workflows`, `scripts`, the public key and `release-config.json` with required owner review. CODEOWNERS alone does not enforce approval without repository rules.

## Previously published content

Public repositories and public release assets can be cloned and copied. Visibility changes or later deletion cannot revoke copies made before removal. Review past public source archives and remove accidental disclosures from future distribution; treat committed secrets as compromised and rotate them instead of relying on deletion.
