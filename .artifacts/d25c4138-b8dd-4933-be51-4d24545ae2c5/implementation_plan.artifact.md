# Implementation Plan - Update App Branding to StoryWave

This plan involves replacing the current generic icons with the new "StoryWave" logo provided by the user and updating the app's name and slogan throughout the onboarding and login screens.

## User Review Required

> [!IMPORTANT]
> **Action Required**: Before I apply these changes, please ensure you have saved the provided image into your project at:
> `app/src/main/res/drawable/app_logo.png`
> (Rename the image file to `app_logo.png` if it has a different name).

## Proposed Changes

### UI Screens

#### [MODIFY] [LoginScreen.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/example/englishstories/ui/screens/LoginScreen.kt)
- Replace the `Icons.Default.AutoStories` icon with an `Image` component using `painterResource(id = R.drawable.app_logo)`.
- Update the headline text from "English Stories" to "StoryWave".
- Update the sub-headline to reflect the new branding.

#### [MODIFY] [RegisterScreen.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/example/englishstories/ui/screens/RegisterScreen.kt)
- Add the `app_logo` image at the top of the registration form for consistency.
- Update any text references to the app name.

#### [MODIFY] [SplashScreen.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/example/englishstories/ui/screens/SplashScreen.kt)
- Replace the `Icons.Default.Book` icon with the new `app_logo`.
- Update the title and slogan to "StoryWave" and "Read • Learn • Grow".

### Resources

#### [MODIFY] [strings.xml](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/res/values/strings.xml)
- Change `app_name` to "StoryWave".

## Verification Plan

### Automated Tests
- Run `./gradlew :app:assembleDebug` to ensure no resource errors (e.g., missing `app_logo`).

### Manual Verification
- Launch the app and verify the splash screen shows the new logo and title.
- Verify the login and registration screens display the new branding correctly.
