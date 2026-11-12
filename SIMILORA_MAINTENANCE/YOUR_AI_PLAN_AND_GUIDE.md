# Your AI for Smart Search — 2.0.6

## Implemented plan

1. Extend the existing Gemini-only setup to a user-owned provider connection: Gemini and OpenAI, with a model ID and the user's own API key. Keep the existing saved Gemini key compatible. No app-owned shared key or AI proxy server.
2. Store provider/model/key in the account-scoped Android Keystore encrypted vault. Public configuration returns only provider, model and connected state. Generic storage reads and clinical backups cannot expose the key. Disconnect deletes the saved connection. Provider URLs are fixed HTTPS endpoints; model and header values are validated and redirects denied.
3. Keep the existing local repertory lookup. AI only supplies bounded symptom concepts/search terms. The user reviews text before sending it and reviews real local rubric candidates before adding them. No automatic remedy prescription, new database entries or case additions. Add an accessible Stop search action.
4. Bound input/output, classify provider errors, preserve cancellation and offline fallback, check account/config identity before accepting responses, and test native storage plus request/response fixtures and browser workflows. Provider API access, quota and fees remain the user's responsibility.

## Automatic selection in 2.0.7

The model-name field has been removed. Choose a provider and enter its API key. The app supplies a default internally and tries an alternative when the provider reports an unavailable model (HTTP 404). Authentication and quota errors are not retried. Existing valid saved model settings remain compatible. API providers still require a model in their requests, but users do not need to name it.

## How to use

1. Update the APK and sign in normally. Open Menu → Your AI, or Smart Search → Your AI settings.
2. Choose Google Gemini or OpenAI. Obtain a key in your own account at https://aistudio.google.com/apikey or https://platform.openai.com/api-keys . Do not send keys through chat or to the owner.
3. Enter the API key and choose Save connection. The app selects the model; there is no model-name field. Saving is local configuration, not proof that the provider accepted the key.
4. Reopen settings and choose Test saved connection. This sends only a synthetic connection-test prompt to the saved provider. A consumer ChatGPT/Gemini subscription does not automatically supply API access. Provider availability, free quotas and billing can change.
5. Describe the symptom in Smart Search. Remove identifying details in the review dialog. Choose Send to the selected provider, or Use offline search. The request contains the reviewed symptom text, selected book/chapter names and search instructions, not the patient's saved record or clinical database.
6. Review the returned candidates and add appropriate real rubrics yourself. Stop search cancels pending interpretation/lookup. Connection, quota, malformed response or model errors retain offline search.
7. To keep the saved key, leave the key blank for the same provider. A different provider needs its own key. Disconnect removes both new settings and any legacy Gemini key.

## Limits and recovery

Keys are local to this phone/account and are not included in clinical backups. Re-enter the key on a different installation. Names or sensitive information typed into the reviewed text will be sent if the user confirms it; the provider processes that text under its terms. No key bypass or use of a consumer chat login is implemented. The model output is a search aid and must be checked against the repertory.

Automated tests use synthetic fixtures and no real API key. The in-app Test saved connection verifies the user's actual provider/model/key. No claim is made that every model or account region has been tested live.

After restoring a clinical backup, reconnect your AI if its local settings were cleared. Developer browser verification: `python3 tests/browser/your_ai.py --assets /private/similora-assets/assets/www`. Native fixture tests are in the separate QA APK, never in the release.

## OpenAI 429 troubleshooting (2.0.8)

HTTP 429 is ambiguous by itself. OpenAI uses it for temporary rate limits and for insufficient API quota. The app now reads the bounded error response and recognizes insufficient_quota, billing_hard_limit_reached and usage_limit_reached without displaying raw provider text.

- Quota/billing message: check https://platform.openai.com/settings/organization/billing/overview and the API project's usage/limits. A ChatGPT subscription does not include API credit. Replacing the same project's valid key or repeatedly retrying does not add credit.
- Temporary request limit: wait for the provider's rate window, then test once. Check https://platform.openai.com/settings/organization/limits . Shared project usage can affect the limit.
- Invalid key/permissions is a different error. Do not publish your key or send it in chat.

The app cannot increase provider limits or change API billing. Offline phrase search remains available. Your actual account's condition is determined by the provider's response; synthetic tests cannot inspect its balance.
