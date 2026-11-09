# Approved Glass website build

The builder copies all packaged assets into a local output directory and overlays the approved desktop Glass navigation. Existing clinical logic, source identifiers, remedy grades, images and Materia Medica remain intact. A SHA-256 inventory verifies copied data files. No clinical databases are checked into this public source repository.

This is an unpublished integration build. It is NOT access-protected production hosting. Do not expose the output using a public static server: browsers receive static assets and client-side hiding does not protect them. Full website launch needs authenticated delivery of clinical assets, browser grant enforcement and native-feature replacements. Website storage does not inherit Android Keystore encryption. The clinical app already generalizes provider display labels while preserving legal credits and raw provenance.

Run: python scripts/build_clinical_website.py /path/to/recovered/assets/www /private/local/output

The existing Apper project is the login/approval authority. Its owner device-request dashboard now requires Owner_c even on direct navigation. This change does not itself approve a browser or implement clinical hosting. APK access routes and the native nonce bridge remain intact.

Next integration gate: authenticated server/Apper delivery of the large clinical assets, explicit browser grants and end-to-end member/owner tests. No route should accept a role passed from query parameters, local storage or postMessage as authorization. Owner links must derive from the authenticated role; member UI must not render owner management. Private asset requests need backend checks, not just a protected index page.
