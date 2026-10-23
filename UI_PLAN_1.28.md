# Reading workspace UI improvement plan

## Phase 1 — Contextual controls (implemented)
Move the entire Repertory & reading section to a single button beside the book name at the top of Repertory. Remove both reading entries from the global menu. Keep settings collapsed until requested. Other pages do not show the button.

## Phase 2 — Focused customization (implemented)
Reuse the existing persisted text size, remedy visibility, branch, patient-word, density/privacy and glass settings. Use one scrollable dialog with a stable heading and Close control. Restore focus on dismiss, handle Escape and Android Back routing, and close the panel on page changes. Keep control listeners and clinical state intact.

## Phase 3 — Restrained motion (implemented)
Use a 160 ms opacity/8 px translation entrance and 120 ms backdrop fade. Do not animate blur, reading lists, scores or page dimensions. Immediate dismissal avoids delayed taps and stale backdrops. Respect reduced-motion preferences; retain solid surfaces for older WebViews and the glass-off setting.

## Phase 4 — Regression and delivery (implemented)
Check small phones, landscape and tablets, both themes, enlarged text, persistence, focus and repeated navigation. Run existing clinical and menu regressions, build with the retained identity and verify signed release metadata before publication.

## Next UI phases (planned)
1. Measure frame pacing on actual low/mid-range Android devices; tune entrance duration only from measured results.
2. Audit one-handed reach and thumb travel during consultation using clinician feedback; preserve compact rubric spacing and chapter illustrations.
3. Review contrast, large text and keyboard/talkback navigation across remaining native dialogs. Keep clinical labels and remedy grades explicit.
4. Introduce consistent, small state-change feedback for save/selection actions after performance measurement. Avoid animated rearrangement of analysis results while reading.
