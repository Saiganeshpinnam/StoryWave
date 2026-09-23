# Walkthrough - Enhanced Authentication Debugging

I have updated the app's authentication logic to help you identify the exact cause of the "Server returned non-JSON" error during login.

## Changes

### 1. Robust Response Capture
- **StoriesViewModel.kt**: I updated the `login` and `register` functions to handle cases where the server sends back HTML instead of JSON.
- **Clearer Error Messages**: If the server returns an HTML page (like a 404 or a Spring Security login page), the app will now say: **"Server Error: Received HTML instead of JSON."** This prevents the cryptic malformed JSON crash you were seeing.

### 2. Deep Technical Logging
- **Full Response Logging**: I added `Log.e` calls that print the **entire raw server response** to your Logcat.
- **Error Code Tracking**: If the server returns a failure code (like 401 or 500), the app now logs the specific code and the server's error message.

## How to Debug Your Backend Now

1.  **Run the app** on your mobile device.
2.  Attempt to **Login**.
3.  In Android Studio, open the **Logcat** tab (at the bottom).
4.  Filter the search by: `StoriesVM`.
5.  **Look for the log entry**: `Login parsing error. Raw body: ...`.
6.  **Read the HTML content**: Look for the `<title>` tag in that log. It will usually say something like **"Whitelabel Error Page"**, **"404 Not Found"**, or **"Login - Spring Boot"**.

> [!TIP]
> If you see a Spring Boot login page in the logs, it means your backend's security configuration is redirecting the API request to a web login page instead of returning an unauthorized JSON response.

## Verification Results

### Build Status
- Ran `./gradlew :app:assembleDebug` - **Success**.
