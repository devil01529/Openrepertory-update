# Open Repertory 1.30 validation

377 automated browser/package checks passed: {"materia-reader.json": 210, "section-picker.json": 150, "package-invariants": 17}.

The picker suite covers five viewport sizes, light/dark themes, 140% text, exact source heading order, filtering/empty results, selected markers, section scrolling/focus, Escape, repeated Back, overlay cleanup, source text retention and reduced motion. The Materia Medica suite verifies remedy search/filtering, source switching, section navigation, notes across refresh/reload and existing profile navigation.

694 clinical data assets match 1.29 byte for byte. Native classes and all JavaScript modules except Materia Medica are unchanged. Packaged changed assets match those tested. APK build and retained signing identity verified.

Limits: browser and package checks only in this iteration; Android instrumentation and physical-device frame pacing were not rerun. No zero-bug guarantee.

APK SHA-256: `0550c9be8ce7a4d23444a57f00ed6258fc7d808c730afd7ba59beab2e776ef59`
