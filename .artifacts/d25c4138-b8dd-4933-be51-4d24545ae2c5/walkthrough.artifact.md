# Walkthrough - Added Delete Account Feature

I have added a "Delete Account" feature to the StoryWave app, allowing users to permanently remove their account and all associated local data.

## Changes

### 1. Database & Repository
- **UserDao**: Added a new delete query: `DELETE FROM users WHERE email = :email`.
- **StoriesRepository**: Exposed the `deleteUser` method to the logic layer.

### 2. ViewModel Logic
- **Session Tracking**: The `StoriesViewModel` now tracks the `currentUserEmail` upon successful login.
- **Account Deletion**: Implemented `deleteAccount()` which:
    1. Removes the user from the local database.
    2. Clears the current session.
    3. Notifies the UI to navigate back to the Login screen.

### 3. User Interface (Profile Screen)
- **Settings Integration**: Added a "Delete Account" option in the Profile settings section with a clear warning icon.
- **Safety Confirmation**: When clicked, a confirmation dialog appears to prevent accidental deletions. Users must explicitly confirm they want to proceed.
- **Automatic Logout**: Upon successful deletion, the user is automatically logged out and redirected to the login screen.

## Verification Results

### Build Status
- Ran `./gradlew :app:assembleDebug` - **Success**.

### Security & UX
- Verified that the "Delete" action is irreversible and requires explicit user consent through an `AlertDialog`.
- Verified that deleting an account correctly clears the app session.
