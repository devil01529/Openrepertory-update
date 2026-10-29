# Native updater presentation

AppUpdates.java uses UpdateGlass.java for update summary, notes and offer dialogs. Copy fonts/NotoSans-Regular.ttf and fonts/OFL.txt to the Android assets/fonts directory when integrating these source files. The font is distributed under the included SIL Open Font License.

The optional Host.appearance() returns a JSONObject with theme (light, dark or auto) and glassEffects (boolean). Open Repertory reads these values from its encrypted preferences; the default host uses the system theme and glass-style surfaces. Existing allowed() and beforeInstall() gates must remain implemented.

No private signing keys or clinical databases are included in this source folder.
