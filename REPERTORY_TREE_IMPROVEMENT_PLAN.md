# Today and repertory navigation — phased implementation (1.25)

## Phase 1 — Restore reliable scrolling
The Today flex child currently grows to its full content height (1,194 px in a 640 px viewport), so it never becomes a scroll container. Constrain its height, allow flex children to shrink, and retain one vertical scroller. Keep bottom navigation outside the scroll surface. Verify wheel and Android-style touch gestures, reach the final action, and return from other pages without losing access to the content. Cover small phones, landscape, tablets and enlarged text.

## Phase 2 — One consistent book picker
Remove the visible legacy Android select, including its source/year-heavy labels. Both the reading toolbar and chapter panel must open the same application-styled picker. Use short repertory names, a selected check, clear focus indication, comfortable touch targets and bounded scrolling. Retain all seven books, lazy loading, source metadata through existing source details, and current error handling. No system-font dialog should appear from book selection.

## Phase 3 — Compact searchable chapters
Replace decorative chapter pictures with a clean textual index: chapter name, count and selected indication. Add a labelled chapter filter with clear and no-results states; filter only the current book and reset on book switch. Keep the panel header/filter fixed while the chapter list scrolls. Use real buttons for keyboard operation. Choosing any chapter, including the current one, must close the panel and open that chapter rather than erase it.

## Phase 4 — Readable rubric branches
Keep source wording and actual parent/sub-rubric relationships. Use clear branch guides, an expand control with the child count, and a single expand/collapse-all action. Collapsed branches must not render thousands of hidden children; expand them on demand using existing paged rendering. Do not infer new relationships from commas, merge source records, or alter remedy grades. Preserve search, source references, remedy visibility, selection, clipboard navigation and mixed-source analysis.

## Phase 5 — Verify and distribute
Add targeted regressions for scrolling, all book entry points, keyboard/filter/no-results states, current-chapter reselection, branch controls and lazy expansion. Run relevant clinical identity, scoring, reliability, menu, release integrity and native smoke/upgrade checks. Build with the existing package and signing key; retain encrypted records and approval rules. Publish a new immutable signed GitHub release only after checks pass. Provide a concise usage guide, detailed test evidence and the direct APK link.

## Completion criteria
Today reaches its final action by touch and wheel; every visible book switch opens the same app picker; chapter filtering and selection work on phone/tablet; branch controls never remove clinical records; APK installs in place with the same certificate. Emulator timings are not claims about physical-device performance.

## Verification finding — late startup recovery
Android testing found an inherited failure: after 60 seconds, the local-workspace readiness poll stopped, so a document that finished later remained hidden. Continue a one-second poll behind the slow-start feedback, resume polling on foreground return, and restart correctly on Retry. Keep every approval, foreground and document-generation guard. Exercise deliberately delayed readiness in native tests before publication.

## Implemented phase status
Phases 1–4 are implemented. Phase 5 automated verification and signed package preparation are complete; public release verification is recorded separately after publication. See TEST_REPORT_1.25.md for measured results and limits.
