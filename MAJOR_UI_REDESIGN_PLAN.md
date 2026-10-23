# Open Repertory — major clinical UI redesign

Prepared 22 October 2026. Audience: practising homeopaths using Android phones and tablets. Status: **core redesign implemented in v1.23**. The original design brief follows; see CLINICAL_UI_GUIDE_1.23.md and TEST_REPORT_1.23.md for implemented behaviour and verification limits.

[View the proposed screen layouts](https://github.com/devil01529/Openrepertory-update/blob/main/docs/clinical-workspace-concept.png) (synthetic illustrative data; not implemented screens).

## 1. Direction and scope

Rebuild the interface around a consultation: **patient → visit notes → rubric selection → analysis → reference checking → saved review**. This is a structural redesign of the workspace, screen hierarchy, interactions and typography, not a change of colours alone. Keep the existing encrypted records, repertory databases, analysis algorithms, approval system and signed update channel.

Use RadarOpus as a reference for moving between patient, repertory and analysis contexts, and Synthesis as a reference for book hierarchy and source referencing. Create our own visual system and mobile interactions. This does not add the proprietary Synthesis database or reproduce another product's assets.

Primary references reviewed:

- [RadarOpus Academy: repertory search and case analysis](https://www.radaropus.com/academy/repertories-video).
- [RadarOpus manual: moving between patient, repertory and analysis tabs](https://www.radaropus.com/RadarOpus/docs/manual/2.2/en/files/basic-html/page98.html).
- [Synthesis repertory order and author references](https://cdn.radaropus.com/RadarOpus/docs/synthesis/the_repertory_order_of_synthesis/adonis/en/files/basic-html/page2.html).

The proposed design choices below are recommendations for Open Repertory, not claims that these products implement every proposed interaction.

## 2. What the existing app already provides

The app has patient files and visits, multiple source-locked cases, six repertories, weighted analysis and a remedy matrix, snapshots, remedy comparison, Materia Medica, local notes, encrypted backups, owner approvals and signed APK updates. Surface these features coherently instead of adding parallel versions.

Current interface problems to solve:

1. Branding, case context, search, chapter controls and finder tools compete for vertical space on phones.
2. Patient records, Patient file and Case are related but feel like separate destinations. Clinicians must repeatedly orient themselves.
3. Repertory browsing needs a stronger hierarchy between chapter, parent rubric, subrubric, remedy entries and source information.
4. Analysis controls, selected rubrics, ranking and the full matrix need progressive disclosure, especially on small screens.
5. The dashboard uses substantial introductory space even when an active consultation exists.
6. Loading, offline access and saved-state feedback should have a consistent language across native account screens and the local clinical workspace.

## 3. Target screen architecture

### Shared consultation frame

- One restrained header: app mark, current screen title, Tools button. Use navy, warm white, muted slate and one blue action colour; reserve red for errors and supported source grading.
- A compact patient/case strip: patient initials or privacy alias, visit date, case name, active repertory and saved state. Tap to switch context deliberately; an unlinked case stays clearly labelled.
- Five stable phone destinations: **Today / Repertory / Analysis / Patients / Library**. Rename the existing ambiguous Case tab to Analysis during the redesign, after checking all labels and help text together.
- Owner approval management, updates, backup, appearance and online AI remain in grouped Tools/Settings, not additional bottom tabs.
- Preserve the selected case, source, query and scroll location when navigating away and returning. Do not silently substitute a different patient or book.

### Today: a working consultation dashboard

Replace the large introductory hero with a compact “Continue consultation” panel showing the active patient, visit, selected rubric count and last saved time. Put Resume, New patient and New visit within immediate reach. Show recent patients and recent cases below, with dates. Keep onboarding guidance only for an empty workspace and make it dismissible.

Acceptance: a returning clinician can resume the current case in one tap; no promotional or tutorial hero displaces it.

### Patient and visit workspace

Combine the existing Patients and Patient file journeys into a master list and a clearly identified patient detail view. Within a patient, use **Overview / Visits / Cases / Notes**. Put the current visit's editable notes next to its linked cases on tablets; on phones, use full-screen sections rather than nested dialogs.

Retain drafts, save/recovery indicators and existing case links. Keep destructive actions away from primary Save/Continue controls and require a clear confirmation for deletion. Offer a presentation privacy mode to hide patient identity in shared views without altering stored names. Do not introduce cloud patient sync as part of this UI change.

### Repertory: the main reading workspace

- A single search field with a concise scope control: current chapter or whole selected book. Put source selection in the case/source strip; make source locking obvious.
- A chapter drawer on phones; a persistent chapter tree on larger tablets.
- Compact breadcrumb showing the current rubric path. Expand the full path on demand instead of truncating essential words.
- Rubric rows: indentation and disclosure arrow at the left, readable rubric title, remedy count, and an explicit Add/Added control. Keep children close to parents with subtle guide lines; do not use large separate cards for every rubric.
- Default remedy lines to a readable preview with Expand; retain a preference to show all. Preserve source-specific typography and provide a visible grade/style legend. Do not rely on colour alone.
- Source, transcription quality and cross-references open a detail sheet, leaving the reading position intact. Every displayed reference must come from available data; do not fabricate page numbers or citations.
- Show database coverage in the source catalogue. For example, the current Clarke database contains Parts I–III; Parts IV–V are not converted. Keep partial coverage and pending source review visible rather than implying that every book is complete or fully verified.
- Keep Find rubrics from the patient's words as a secondary expandable tool. Candidates remain reviewable against the original source before selection; they do not enter analysis automatically.
- The floating clipboard Analysis button shows selected count and opens the existing complete Analysis page. It is already implemented in v1.21 for repertory browsing when at least one rubric is selected.

Acceptance: a clinician can find a rubric, inspect its source, select it and open analysis without losing their book position. Long titles, dense remedy lists and nested subrubrics never overlap their controls.

### Analysis: a dedicated case workspace

Phone layout: **Selected rubrics / Ranking / Matrix** as three local views within the Analysis screen. Selected rubrics show their full path, source, importance and remove action. Ranking shows remedy name, score and coverage. Tapping a score reveals how it was calculated, including excluded grades and rubric weights. Matrix supports deliberate horizontal scrolling, a pinned rubric column and a small sticky legend.

Tablet layout: selected rubric list on the left; ranking or matrix on the right. Show method and grade filter in one compact control strip. Keep Save snapshot, history and compare remedies together in a case action bar; export and print are secondary actions.

Retain existing scoring functions and snapshot schema. Scores represent the selected repertory links and settings, not a diagnosis or an automatic prescription. Clarke remains presence-count analysis: its printed italics must not become strength grades. Tests must compare rankings before and after the UI change on identical fixtures.

Acceptance: the full current-case analysis is reachable in one tap from the floating button; score method, coverage, weights and source context remain inspectable on phones.

### Library: a reading and comparison workspace

Group Materia Medica and remedy comparison under Library. Use a compact remedy search, alphabetical grouping and meaningful filters such as “With source text”; avoid a large introductory block. Distinguish bundled source text from the clinician's private notes. Keep remedy identity and author/source visible while reading. On tablets show remedy list beside text; on phones open a focused reader and restore the list position on Back.

### Account, approval and update screens

Match the clinical app's typography, spacing, controls and status language as far as the Apper integration permits. Show distinct states: signing in, checking this phone, awaiting approval, approved, offline grace period, revoked and service unavailable. Keep retry and email verification actions explicit where supported. Preserve role checks and per-phone approval; visual redesign must not relax access control.

Updates show current/new version, release notes, download progress and the Android installation handoff. Keep the existing signature, certificate and hash checks. Downloaded APKs continue to require Android's installation confirmation; do not promise silent background installation.

## 4. Responsive design and accessibility contract

| Width | Workspace |
|---|---|
| Under 700 CSS px | One focused pane, bottom navigation, chapter drawer, clipboard shortcut above navigation/status |
| 700–1099 CSS px | Navigation rail or compact header, two panes where usable; in narrow landscape allow a focused pane |
| 1100 CSS px and above | Chapter/patient list, working pane and optional analysis/reference pane; bounded reading width |

Breakpoints are initial proposals; validate them with real screen sizes, keyboard visibility and font scaling before locking them.

- Define shared spacing, type, colour, border and focus tokens. Use a 4/8-pixel spacing rhythm; 16-pixel default body text as a starting target, adjusted through device testing.
- Standard action targets at least 48×48 CSS px in the new design, while allowing dense readable content between controls. Separate reading density from touch target size.
- Comfortable and Compact reading presets; neither may hide rubric text, source distinctions or analysis settings.
- Support 200% text, TalkBack descriptions, logical focus order, visible focus, reduced motion, light/dark themes and sufficient text contrast. Matrix structure must also have an accessible list alternative.
- Respect Android status/navigation insets, landscape, soft keyboard and small phones. Avoid motion that delays reading; use short optional transitions and stable placeholders.

## 5. Implementation sequence and review gates

| Phase | Deliverable | Gate before release |
|---|---|---|
| 0 — baseline | Document existing routes and state; record representative clinical workflows and timings; capture phone/tablet screenshots | Backups and upgrade identity verified; six books and existing analysis fixtures recorded |
| 1 — design prototype | Shared visual tokens and navigable mockups of Today, patient visit, repertory and analysis at phone/tablet sizes | Owner reviews a complete consultation flow, not isolated colour samples; clinician usability review if available |
| 2 — consultation frame | New header, context strip, navigation, compact Today and grouped Tools | Patient/case/source identity survives every navigation path; access checks and Android Back still work |
| 3 — repertory workspace | New chapter navigation, search hierarchy, dense rows, remedy previews, source sheet and integrated shortcut | Identical search results, hierarchy, grades, typography and selected rubric data across all six books |
| 4 — analysis workspace | Selected/Ranking/Matrix views, adaptive split pane and explainable scoring controls | Identical ranking/weights/snapshots to baseline; count and source lock correct after restore/import/case switch |
| 5 — patient and library | Unified visit/case navigation, focused remedy reader, private notes and comparison layouts | Drafts, encrypted backup/restore, patient links, long notes and reading positions retained |
| 6 — native polish and release | Account/update styling, accessibility pass, performance pass, release notes and owner guide | Signed upgrade from current installed APK; encrypted records and phone approval identity retained; real-device review |

Publish small tested releases at phase boundaries so clinicians can continue working. The end result is a major redesign; staging reduces the risk of losing working clinical behaviour. Do not bundle database re-transcription or new scoring algorithms into these visual releases.

## 6. Verification and success measures

Use synthetic records for automation, never real patient screenshots in a public repository. Include linked/unlinked cases, each book, long nested rubrics, empty/new cases, dense selected lists, snapshots, encrypted backup round trips, connection failure, owner/member sessions and revoked phones.

Measure these before and after; they are **targets, not results already achieved**:

- Resume active consultation: one tap from Today.
- Selected rubric to complete analysis: one tap with the floating shortcut.
- Main reading chrome should leave at least 60% of a 393×800 CSS-pixel screen for clinical content with the keyboard closed; measure rather than shrink text to meet it.
- No clipped/overlapping controls at 320px width, landscape, 200% text, light/dark and keyboard-open states.
- Typical select/open-analysis feedback under 150ms on the reference Android device once data is loaded; benchmark heavy cases separately and display progress if computation is slow.
- No lost records, changed scoring outputs or extra login prompts caused by navigation.
- Compare common task completion times and wrong-case/source mistakes with the current release using representative homeopaths before rolling out the whole design.

## 7. First implementation decision

Start the major redesign with **Phase 1 prototypes and Phase 2 consultation frame**, then repertory and analysis together. These offer the largest daily benefit. Defer optional presentation privacy mode and new density preferences until the core flow is stable. No new paid service is required for the UI work; the existing update distribution remains in use.

The v1.23 release implements the core consultation frame, Today, repertory, analysis, patient/library, reading/privacy and native navigation work. The clipboard is now icon-only. Physical-phone clinician usability and timing targets remain validation work; see the release test report.
