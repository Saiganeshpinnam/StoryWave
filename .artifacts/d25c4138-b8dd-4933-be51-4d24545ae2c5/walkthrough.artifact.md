# Walkthrough - StoryWave Branding Update

I have updated the app's branding from "English Stories" to **StoryWave**, including the new logo and slogan.

## Changes

### 1. Resource Updates
- **strings.xml**: Updated `app_name` to "StoryWave".

### 2. UI Branding (Splash, Login, Register)
I replaced the generic material icons with the new StoryWave logo across the following screens:
- **SplashScreen.kt**: Added the logo and the slogan "Read • Learn • Grow".
- **LoginScreen.kt**: Integrated the new logo and updated the branding text.
- **RegisterScreen.kt**: Added the logo for a consistent registration experience.

## Important Note

> [!CAUTION]
> The code now references `R.drawable.app_logo`.
> **You must save your logo image as `app_logo.png` in the `app/src/main/res/drawable/` directory** for the project to build successfully.

## Verification Results

### Build Status
- The code is updated and syntactically correct, but a full build will require the physical `app_logo.png` file to be present in the resources.
