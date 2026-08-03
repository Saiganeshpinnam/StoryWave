package com.example.englishstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.englishstories.data.local.AppRoomDatabase
import com.example.englishstories.data.remote.RetrofitService
import com.example.englishstories.repository.StoriesRepository
import com.example.englishstories.ui.screens.*
import com.example.englishstories.viewmodel.StoriesViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Standard Material 3 Dark Color Scheme (Classic Midnight theme)
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF2B2930),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF211F26),
    onSurfaceVariant = Color(0xFF938F99),
    error = Color(0xFFF2B8B5)
)

// Standard Material 3 Light Color Scheme (Classic Soft Light theme)
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1D1B20),
    surface = Color(0xFFF3EDF7),
    onSurface = Color(0xFF1D1B20),
    surfaceVariant = Color(0xFFECE6F0),
    onSurfaceVariant = Color(0xFF49454F),
    error = Color(0xFFB3261E)
)

class StoriesViewModelFactory(private val repository: StoriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SQLite Room Database builder (Offline-First cache)
        val database = Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java,
            "english_stories_db"
        ).fallbackToDestructiveMigration().build()

        // Initialize Retrofit Client (Connecting to real cloud database backends)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ais-dev-afs32tzajecojdczqie5yq-42301013462.asia-southeast1.run.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)

        // Inject dependency graph
        val repository = StoriesRepository(database.storyDao(), retrofitService)
        val viewModelFactory = StoriesViewModelFactory(repository)
        val viewModel: StoriesViewModel = ViewModelProvider(this, viewModelFactory)[StoriesViewModel::class.java]

        setContent {
            var currentScreen by remember { mutableStateOf("Splash") }
            var isDarkTheme by remember { mutableStateOf(false) }
            var selectedStoryId by remember { mutableStateOf<Int?>(null) }
            var selectedCategoryId by remember { mutableStateOf<String?>(null) }
            var selectedCategoryTab by remember { mutableStateOf("all") }
            var previousScreen by remember { mutableStateOf("Home") }

            val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

            MaterialTheme(
                colorScheme = colorScheme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "Splash" -> {
                            SplashScreen(
                                onNavigateToLogin = {
                                    currentScreen = "Login"
                                }
                            )
                        }
                        "Login" -> {
                            LoginScreen(
                                viewModel = viewModel,
                                onNavigateToRegister = { currentScreen = "Register" },
                                onNavigateToHome = { currentScreen = "Home" }
                            )
                        }
                        "Register" -> {
                            RegisterScreen(
                                viewModel = viewModel,
                                onNavigateToLogin = { currentScreen = "Login" },
                                onNavigateToHome = { currentScreen = "Home" }
                            )
                        }
                        "Home" -> {
                            HomeScreen(
                                viewModel = viewModel,
                                selectedCategoryTab = selectedCategoryTab,
                                onCategoryTabSelect = { selectedCategoryTab = it },
                                onNavigateToCategories = { currentScreen = "Categories" },
                                onNavigateToFavorites = { currentScreen = "Favorites" },
                                onNavigateToProfile = { currentScreen = "Profile" },
                                onNavigateToDetail = { storyId ->
                                    selectedStoryId = storyId
                                    previousScreen = "Home"
                                    currentScreen = "StoryDetail"
                                }
                            )
                        }
                        "Categories" -> {
                            CategoriesScreen(
                                viewModel = viewModel,
                                onCategoryClick = { categoryId ->
                                    selectedCategoryId = categoryId
                                    currentScreen = "CategoryStories"
                                },
                                onNavigateToHome = { currentScreen = "Home" },
                                onNavigateToFavorites = { currentScreen = "Favorites" },
                                onNavigateToProfile = { currentScreen = "Profile" }
                            )
                        }
                        "CategoryStories" -> {
                            selectedCategoryId?.let { categoryId ->
                                CategoryStoriesScreen(
                                    viewModel = viewModel,
                                    categoryId = categoryId,
                                    onNavigateBack = { currentScreen = "Categories" },
                                    onNavigateToDetail = { storyId ->
                                        selectedStoryId = storyId
                                        previousScreen = "CategoryStories"
                                        currentScreen = "StoryDetail"
                                    }
                                )
                            }
                        }
                        "Favorites" -> {
                            FavoritesScreen(
                                viewModel = viewModel,
                                onNavigateToHome = { currentScreen = "Home" },
                                onNavigateToCategories = { currentScreen = "Categories" },
                                onNavigateToProfile = { currentScreen = "Profile" },
                                onNavigateToDetail = { storyId ->
                                    selectedStoryId = storyId
                                    previousScreen = "Favorites"
                                    currentScreen = "StoryDetail"
                                }
                            )
                        }
                        "StoryDetail" -> {
                            selectedStoryId?.let { storyId ->
                                StoryDetailScreen(
                                    viewModel = viewModel,
                                    storyId = storyId,
                                    onNavigateBack = { currentScreen = previousScreen }
                                )
                            }
                        }
                        "Profile" -> {
                            ProfileScreen(
                                viewModel = viewModel,
                                isDarkTheme = isDarkTheme,
                                onThemeToggle = { isDarkTheme = !isDarkTheme },
                                onNavigateToHome = { currentScreen = "Home" },
                                onNavigateToCategories = { currentScreen = "Categories" },
                                onNavigateToFavorites = { currentScreen = "Favorites" },
                                onLogout = { currentScreen = "Login" }
                            )
                        }
                    }
                }
            }
        }
    }
}
