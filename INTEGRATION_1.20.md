# Open Repertory 1.20 menu update

The phone overflow menu now contains seven actions in Clinical tools and App & account groups. Home/Search/Case/Patients/Library remain in bottom navigation; desktop retains a compact Go to grid. Find rubrics, Patient file, Compare remedies, Account & access, Backup & restore, App updates and Settings remain directly available. Appearance and Online AI are the Settings subview. No feature, clinical data or backend is removed.

The popup has a labelled dialog, matching SVG icons, explicit close/back controls, grouped headings, wrapped labels, 44px+ touch targets, bounded scrolling, focus containment and restoration. It isolates underlying content from assistive technology while open. The header stays available while contents scroll. Resize/visual-viewport events reposition the menu. Explicit hidden styles support the older Android WebView.

The native Back handler first asks OR.dismissMenu to return from Settings or close the main menu; it retains its previous fallback when no menu is open. Existing Owner manager Back behaviour remains first. Patient file and remedy comparison now identify their Patients/Library primary location visually and accessibly.

Changes are limited to menu markup/styles, maintained app.js navigation and the native Back delegate. All 690 clinical data/text assets, Apper endpoint, signed updater implementation, package and signing identity are retained. No permissions are added.

Version 1.19 users: Menu → App updates → Check for updates → Download update → Install update. Android asks you to confirm. Do not uninstall or clear storage. The full public release/source overlay and test report are in this repository's release/documentation.
