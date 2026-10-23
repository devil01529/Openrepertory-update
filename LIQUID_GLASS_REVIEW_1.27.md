# Independent Liquid Glass UI review — 1.27

The review used source inspection, Chromium screenshots and screenshot-pixel contrast sampling. Hiding only the sampled text while retaining its panel/background captures actual compositing, including the menu's sibling backdrop; raw rgba values alone do not establish contrast.

## Issues found and corrected

- Light menu subtitles retained the old muted color on a translucent surface. A real screenshot produced approximately 4.42:1. Menu subtitles and reading notes now use the glass-specific muted color.
- Current chapter node counts inherited the old muted color over the new selected fill, approximately 4.05:1. They now use the glass muted color.
- Another reviewer identified fixed-navy chapter/onboarding headings in dark mode. The corrected headings use theme text colors; the final dark Today screenshot was inspected.
- Leaving presentation mode restored workspace visibility but left the active consultation labels saying “Presentation mode / Patient identity hidden” until page navigation. Both the shield exit and settings Apply paths now refresh consultation labels immediately. Independent regressions cover both paths.

## Final review coverage

`tests/liquid_glass_review.py` covers 320×640, 393×800, 640×360, 768×850 and 1280×900; explicit light/dark and automatic dark themes; 140% text; actual rendered navigation/menu/current-chapter contrast; last reading-option reachability; horizontal menu overflow; solid mode; an opaque unblurred presentation shield; both presentation exit paths; reduced motion; print and JavaScript errors.

Actual screenshot-composited text contrast remains at least 4.5:1 for every sampled label. Final examples: current chapter count 5.36:1 light and 4.89:1 dark; menu subtitles approximately 5.85:1 light and 7.57:1 dark. Visual inspection of final light/dark Today screenshots found readable headings and clear control separation.

The opaque baseline uses mature CSS properties. Blur is limited to three finite navigation/menu surfaces behind a feature query. Clinical lists, remedy typography, forms, matrix cells and privacy shielding retain opaque reading backgrounds. No data or scoring logic was changed by this review.

The final result count is recorded in `test-results/v1.27/liquid-glass-independent-review.json`. Native Android fallback, signing/update identity, clinical asset integrity and broader functional tests are recorded separately by the release owner. Automated review cannot guarantee the absence of all bugs or certify physical-device performance.

A final release-owner specificity check found that the generic new Add-button background overrode the original selected mint fill. The final CSS explicitly preserves green text/border on mint for `.add-btn.added`, and white text on a sufficiently dark blue for `.wt button.on`. The workflow reviewer added real selected-control contrast and weight-change regressions in both themes and all five layouts.

Native screenshot inspection also found the inherited navy Selected-view panel title. Final glass styles now set all panel titles to theme text, use explicit flex centering for the menu dots on WebView 66, and give count badges a dark-blue/white contrast pair. The workflow suite checks the actual Selected heading, count badge and menu-dot geometry in both themes at all five sizes; native Glass checks verify all three on the final APK.

Content stress review found that an unbroken all-caps patient label could overflow Today. Inherited card word wrapping now retains the full string. At enlarged text on a short screen, the context switch could consume almost all reading space; a scoped short-screen rule now shows compact, ellipsized context while keeping complete text in the DOM, Today and case dialog. The new 130-check probe verifies full labels, overflow and actual last-action reachability at five layouts and three text sizes.
