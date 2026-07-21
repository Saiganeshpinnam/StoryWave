# Implementation Plan - Add Medium and Long Stories

Add at least one "Medium" (1001-2000 words) and one "Long" (> 2000 words) story to the mock data to populate the new sections on the Home page.

## User Review Required

> [!NOTE]
> I will add two new stories to the `MockStoriesData.kt` file. To ensure these stories appear in your current app without needing a full database reset, you may need to click **Reset Progress** in the **Profile** section, as the app currently only seeds the database when it is empty.

## Proposed Changes

### Data Layer

#### [MODIFY] [MockStoriesData.kt](file:///C:/Users/Saiganesh Pinnam/Downloads/English-Stories-AiStudio-main/English-Stories-AiStudio-main/android/app/src/main/java/com/example/englishstories/data/local/MockStoriesData.kt)
- Add ID 13: "The Chronicles of Aetheria" (~1200 words) to fall into the **Medium** category.
- Add ID 14: "The Odyssey of the Last Architect" (~2500 words) to fall into the **Long** category.

## Verification Plan

### Manual Verification
- Deploy the app.
- Navigate to the **Profile** section and click **Reset Progress** (to trigger re-seeding with new data).
- Navigate back to the **Home** page.
- Verify that **Medium Stories** and **Long Stories** sections are now visible and contain the new stories.
