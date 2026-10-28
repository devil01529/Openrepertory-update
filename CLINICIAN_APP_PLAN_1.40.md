# Open Repertory: clinician-friendly improvement plan, 1.40

Baseline: released 1.39. Scope covers the complete consultation journey while retaining original data, source grades, scoring, the glass interface and approval rules.

| Phase / area | Implementation and acceptance |
|---|---|
| 1. Today and consultation context | Add an on-demand Consultation review, accessible from Today and Clinical tools. Show the active case, patient linkage, rubric/source counts and current analysis filter. Offer direct actions to the patient file, visit creation, rubric selection, analysis, Library, snapshot and backup. No extra permanent patient strip. |
| 2. Repertory performance | Build remedy buttons only when remedies are shown; release their DOM when hidden. Adding hidden rubrics still uses original source associations. Preserve illustrated chapters, branch controls, references and the existing complete result paths. Measure actual node reduction. |
| 3. Finding and continuity | Add recent successful searches scoped to each case, preserving book and chapter scope. Repeat a search only in its recorded book/chapter; a late source load must not act on a different case. Provide confirmed clearing of just that case's history. Store history through the existing encrypted native store. |
| 4. Analysis review | Review selected rubric counts, source-specific identities, distinct books, current scoring method and grade filter. Show unlinked-case and empty-selection guidance. Direct actions use existing analysis, evidence, snapshot and comparison flows; no automatic prescription or changed scoring. |
| 5. Patient and visit workflow | Review can open only the active case's linked patient or create a visit for that patient. Missing/deleted links receive a clear message. Preserve drafts and the existing Overview/Visits/Cases/Notes workflow. |
| 6. Materia Medica | Add Find in text within the selected source passage, with highlighted matches, Previous/Next and Clear. Search cannot touch private notes, rewrite source passages or mix editions. Source changes reset text-find state. Keep source/section selection and original reader tabs. Case links open the exact originating book/rubric and Back restores the reader. The explicit related-rubrics action selects whole-book scope. |
| 7. Reliability, privacy and accessibility | Make reading-density, text-size, remedy visibility and presentation preferences transactional: failed writes retain the previous saved setting and keep the relevant dialog open. Protect review/history in presentation mode. Keep touch targets, enlarged text, keyboard focus, reduced motion and opaque reading surfaces. |
| 8. Records, security, content and delivery | Keep native encryption/access/update code and all databases unchanged. Review exposes the existing encrypted backup and source-audit entry points; never claim a backup completed before the existing file workflow confirms it. Verify case/source/scoring isolation, private-note preservation and upgrade compatibility. Publish signed APK/update metadata and retain the latest five releases; no standalone database or ZIP. |

## Verification

Use synthetic cases and patients, both themes, compact/enlarged text and phone/tablet layouts. Compare saved cases and original grades before/after every new tool. Test write failures, interrupted searches, deleted scope targets, repeated reader find/clear, source switches, privacy mode and old-WebView behavior. Compare all content with 1.39; the generated build-audit timestamp may change. Install the signed build over 1.39 and verify records/approval survive.

The release implements the rows above; the earlier long-term roadmap's full list virtualization, modern Android gesture certification and physical-device performance measurements are separate work. No algorithmic symptom equivalence, new medical dataset or treatment recommendation is introduced. Passing software checks is not a guarantee of zero device-specific defects or clinical effectiveness.

## Implementation record

The eight rows above are implemented for 1.40. Browser workflow/regression checks, 672 signed-package checks and 32 native upgrade/workflow checks pass. Actual scope and delivery verification are described in [the validation report](TEST_REPORT_1.40.md); clinician usage is in [the guide](OWNER_GUIDE_1.40.md).
