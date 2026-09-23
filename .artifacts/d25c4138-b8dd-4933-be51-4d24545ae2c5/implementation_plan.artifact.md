# Implementation Plan - Fix Login "Non-JSON" Error

The app is receiving an HTML response instead of JSON when attempting to log in, even though registration appears to work. This typically indicates a mismatch between the app's request (URL or data) and the backend's expectations.

## Proposed Changes

### Logic Layer (Debugging & Fix)

#### [MODIFY] [StoriesViewModel.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/viewmodel/StoriesViewModel.kt)
- **Enhanced Logging**: Log the **entire** raw server response to Logcat when parsing fails. This will allow us to see the title of the HTML page (e.g., "404 Not Found" or "Whitelabel Error Page").
- **Flexible Endpoints**: I will try adjusting the login path to see if it resolves the 404/HTML issue.
- **Robust Parsing**: Ensure that even if the server returns a 200 OK with HTML (common misconfiguration), the app correctly reports a "Server Misconfiguration" error instead of a crash.

---

### Networking Layer

#### [MODIFY] [StoryApiService.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/data/remote/StoryApiService.kt)
- Add a secondary login method targeting `/api/login` (no `auth/` prefix) to test if the backend route structure is inconsistent.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure no syntax errors.

### Manual Verification (Critical)
1. Run the app and attempt to log in.
2. **Open Logcat** in Android Studio and filter by `StoriesVM`.
3. **Look for the full HTML printout.** The text inside the `<title>` tag of that HTML will tell us exactly why the server is rejecting the request.
4. If the fallback route works, the login will succeed immediately.
