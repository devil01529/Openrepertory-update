# Open Repertory 1.39: refined glass interface

## Goal

Bring the existing glass design closer to Apple's current approach through a clear navigation layer, consistent materials and responsive motion, adapted to Android and long clinical reading. This is an original WebView implementation; Apple's native optical renderer is not available in this APK.

Reference: [Apple Human Interface Guidelines — Materials](https://developer.apple.com/design/human-interface-guidelines/materials), checked 26 October 2026. The design uses the regular, readable material approach for controls, keeps clinical content opaque and adapts to accessibility preferences. No Apple assets or proprietary fonts are copied.

## Phase 1 — material and hierarchy

- Refine light/dark material tokens, edge highlights and elevation. Use blur only on the finite navigation and tools surfaces that already support it. Preserve a fully opaque fallback on old WebViews.
- Introduce an inset floating navigation capsule on phones and a compact floating rail on tablets. Keep all five named destinations and their existing icons. Reserve the real occupied area so the last content row and clipboard remain reachable.
- Replace heavy repeated outlines with restrained separators, calm content cards and one clear primary action. Keep dense rubric/remedy spacing, original grade colours, matrix cells, source text and patient notes opaque.
- Keep the approved chapter pictures, title labels, reading-panel placement, compact header and all source attribution rules.

## Phase 2 — interaction and motion

- Add one moving selection capsule beneath the navigation buttons, tracking the actual active destination on taps, Back and programmatic navigation. Recalculate after resizing, text changes and tablet rotation.
- Add short, interruptible entry transitions for tools, sheets and the chapter drawer, plus a restrained page fade. Use transform/opacity; never animate blur, list height or matrix geometry.
- Add touch press feedback without moving text layout. Keep keyboard focus rings and prevent persistent touch-focus outlines from cluttering controls.
- Add scroll-edge separation only when the active reading pane has scrolled. Use a passive listener on the current pane, with one animation-frame update; do not observe the whole rubric tree or run continuous animation loops.
- Keep dismissal immediate so Back, form saving, focus restoration, nested dialogs and rapid navigation do not wait for animation callbacks.

## Phase 3 — accessible controls and responsive sheets

- Add Settings → Visual effects for saved Glass effects and Reduce motion preferences, while keeping Repertory & reading only in Repertory. Mirror the existing glass switch and retain unrelated saved preferences.
- Respect system reduced motion/transparency and increased contrast. Reduced motion stops running decorative animations as well as preventing new ones. Solid mode removes backdrop blur.
- Shape phone dialogs as readable rounded sheets and retain centred desktop dialogs. Keep the dialog inside the visible viewport when the keyboard opens; retain scrolling, close buttons and focus trapping.
- Verify 44px primary touch targets, 320px phones, typical phones, short landscape, tablet, enlarged type, both themes, solid mode and printing.

## Phase 4 — verification and delivery

- Test moving selection after rapid navigation/resize, reduced-motion changes, touch versus keyboard focus, saved preference failures, scroll edge state, nested modal focus, reading controls, source evidence, notes, analysis and patient workflows.
- Compare every repertory, Materia Medica asset, chapter picture, remedy grade and scoring result with 1.38. Keep production native code, encryption correction, approval policy, screenshot rules and update protections unchanged.
- Build and install the actual signed APK over 1.38 in an isolated Android emulator. Verify encrypted records and installation identity survive, then test navigation and saved visual settings on the legacy WebView.
- Publish an immutable 1.39 APK and signed update metadata through the existing GitHub workflow. Verify an anonymous download and retain only the latest five releases. Publish no standalone database or ZIP.

## Acceptance and limits

All destinations, source-grade identities, calculations and saved records must remain intact. No continuous sheen, pointer-tracking refraction, external animation library or remote UI dependency is introduced. Automated functional/layout checks and software-emulator observations cannot establish physical-phone frame rates or guarantee absence of device-specific defects; actual measured results belong in the test report.

## Implementation record

Phases 1–3 are implemented. Browser/package checks and all 39 native upgrade/visual checks pass; signed delivery is tracked in [the validation report](TEST_REPORT_1.39.md). The clinical engine and native code remain unchanged.
