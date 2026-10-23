# Liquid Glass clinical interface — version 1.27

## Goal and constraints
Create an Apple-inspired glass appearance for Open Repertory’s navigation and controls, adapted to Android and long clinical reading sessions. Retain the existing clinical workflows, database records, rubric wording, remedy grades, scoring methods, signed updater, Apper owner approvals and encrypted records. No new Android permission or remote UI dependency. This is an original implementation, not Apple system UI.

## Phase 1 — visual foundation
- Add a separate, auditable glass stylesheet after the existing clinical styles.
- Define light, dark and automatic-theme material tokens, subtle blue/teal ambient light, restrained highlights and layered shadows.
- Start with fully solid, readable materials. Enhance eligible navigation surfaces with a small static backdrop blur only where supported.
- Provide a saved Glass effects switch under Menu → Repertory & reading. Turning it off restores solid surfaces; honor system reduced-transparency and increased-contrast preferences when available.
- Preserve reduced-motion behavior. Avoid animated blur, parallax, shine loops and scroll-linked effects.

## Phase 2 — clinical workspace application
- Give the shared header, bottom navigation/tablet rail, tools menu and modal shell coherent rounded glass materials.
- Use a clear raised capsule for the selected destination and segmented Analysis/Patient tabs.
- Refresh Today cards and action hierarchy, illustrated chapter rows/current indication, book picker, selected-rubric controls, patient cards and library controls.
- Keep rubric/remedy text, matrix cells/sticky axes, form inputs, private notes and the presentation shield opaque. Preserve compact remedy spacing and original grade colors/styles.
- Keep entire-drawer and Today scrolling, existing responsive breakpoints, navigation clearance, menu focus trapping and keyboard accessibility.

## Phase 3 — independent review and verification
- Ask separate subagents to review contrast/layout and build independent glass regression tests. Address actionable findings before freezing the APK.
- Exercise light/dark/automatic themes, 320/393/640-landscape/768/1280 layouts, 80–140% text, supported/unsupported blur, solid mode, reduced motion and print.
- Verify drawer artwork/scroll/filter, source switching, rubric selection and clipboard, Selected/Ranking/Matrix, saved records, library, backups, owner approval and background/device-lock behavior.
- Compare retained clinical assets and scoring, check unchanged signing identity/permissions, and install the final APK over the previous release on an isolated emulator.
- Document actual passed checks and limits; automated checks cannot guarantee absence of all device-specific bugs.

## Phase 4 — signed delivery
- Prepare versionCode 28 / versionName 1.27-dev with the retained Android and metadata signing identities.
- Publish immutable APK, signed update metadata, source overlay, release notes, test report and owner guide through the existing public GitHub workflow.
- Download the public Latest-channel metadata and APK anonymously, verify signature/hash/size against the tested build, and record the verification.
- Provide the APK link, guide and in-app update instructions. Users install over the existing app without uninstalling.

## Completion record

All four implementation/review phases are complete through signed release preparation. 1354 automated checks passed; the 1.26→1.27 upgrade retained 7 encrypted records and phone identity. Public publication/download verification is recorded separately after release. See TEST_REPORT_1.27.md and LIQUID_GLASS_REVIEW_1.27.md.

## Public 1.27 release verification

[GitHub release job](https://github.com/devil01529/Openrepertory-update/actions/runs/39952491482) completed successfully and published version 1.27 as Latest. An anonymous download verified the metadata signature and exact tested APK: versionCode 28, 26,839,609 bytes, SHA-256 `7c52fcba9d38de398bf5a4edc18106c3c254c11491b74085c7a501005d96605a`. See [verification record](PUBLIC_RELEASE_VERIFICATION_1.27.json).
