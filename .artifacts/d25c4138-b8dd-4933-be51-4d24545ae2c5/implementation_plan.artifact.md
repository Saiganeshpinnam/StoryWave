# Implementation Plan - Delete Account Feature

This plan adds a "Delete Account" option to the Profile screen, allowing users to permanently remove their data from the local database.

## User Review Required

> [!CAUTION]
> Deleting an account is a permanent action. All local data associated with the user will be removed. I will include a confirmation dialog to prevent accidental deletions.

## Proposed Changes

### Data Layer

#### [MODIFY] [RoomDatabase.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/data/local/RoomDatabase.kt)
- Add `@Query("DELETE FROM users WHERE email = :email")` to `UserDao`.

#### [MODIFY] [StoriesRepository.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/repository/StoriesRepository.kt)
- Add `deleteUser(email: String)` method.

### Logic Layer

#### [MODIFY] [StoriesViewModel.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/viewmodel/StoriesViewModel.kt)
- Track the `currentUserEmail`.
- Implement `deleteAccount()` logic:
    1. Call repository to delete user.
    2. Clear local session state.
    3. Trigger logout.

### UI Layer

#### [MODIFY] [ProfileScreen.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/storywave/app/ui/screens/ProfileScreen.kt)
- Add a "Delete Account" row in the settings section with a `DeleteForever` icon.
- Implement an `AlertDialog` for confirmation.
- On confirmation, execute the deletion and navigate to the Login screen.

## Verification Plan

### Automated Tests
- Build the project using `./gradlew :app:assembleDebug`.

### Manual Verification
1. Register a test account and log in.
2. Navigate to the Profile screen.
3. Click "Delete Account".
4. Verify the confirmation dialog appears.
5. Confirm deletion.
6. Verify the app returns to the Login screen.
7. Attempt to log in with the deleted email and verify it shows "User not found".
