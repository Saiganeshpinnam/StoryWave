package com.storywave.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u001a\u0010\u001d\u001a\u00020\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001c0\u001fJ\b\u0010 \u001a\u00020\u001cH\u0002J2\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u001a\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020\u001c0&J\u0006\u0010\'\u001a\u00020\u001cJ\b\u0010(\u001a\u00020\u001cH\u0002J\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020#J:\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020#2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u001a\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020\u001c0&J\u0006\u0010-\u001a\u00020\u001cJ\b\u0010.\u001a\u00020\u001cH\u0002J\u000e\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u000201J\u0016\u00102\u001a\u00020\u001c2\u0006\u00100\u001a\u0002012\u0006\u00103\u001a\u000201R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014\u00a8\u00064"}, d2 = {"Lcom/storywave/app/viewmodel/StoriesViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/storywave/app/repository/StoriesRepository;", "networkObserver", "Lcom/storywave/app/data/remote/NetworkObserver;", "tokenManager", "Lcom/storywave/app/data/remote/TokenManager;", "(Lcom/storywave/app/repository/StoriesRepository;Lcom/storywave/app/data/remote/NetworkObserver;Lcom/storywave/app/data/remote/TokenManager;)V", "_isAuthenticated", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isLoading", "_isOnline", "_uiState", "Lcom/storywave/app/viewmodel/StoriesUiState;", "_userStats", "Lcom/storywave/app/model/UserStats;", "isAuthenticated", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "isOnline", "uiState", "getUiState", "userStats", "getUserStats", "checkAuthentication", "", "deleteAccount", "onComplete", "Lkotlin/Function1;", "loadContent", "login", "email", "", "password", "onResult", "Lkotlin/Function2;", "logout", "observeConnectivity", "onSearch", "query", "register", "username", "resetAppProgress", "syncContent", "toggleBookmark", "storyId", "", "updateReadingProgress", "progress", "app_debug"})
public final class StoriesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.repository.StoriesRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.remote.NetworkObserver networkObserver = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.remote.TokenManager tokenManager = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.storywave.app.viewmodel.StoriesUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.storywave.app.viewmodel.StoriesUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.storywave.app.model.UserStats> _userStats = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.storywave.app.model.UserStats> userStats = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isOnline = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOnline = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isAuthenticated = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAuthenticated = null;
    
    public StoriesViewModel(@org.jetbrains.annotations.NotNull
    com.storywave.app.repository.StoriesRepository repository, @org.jetbrains.annotations.NotNull
    com.storywave.app.data.remote.NetworkObserver networkObserver, @org.jetbrains.annotations.NotNull
    com.storywave.app.data.remote.TokenManager tokenManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.storywave.app.viewmodel.StoriesUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.storywave.app.model.UserStats> getUserStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isOnline() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAuthenticated() {
        return null;
    }
    
    private final void checkAuthentication() {
    }
    
    private final void observeConnectivity() {
    }
    
    private final void loadContent() {
    }
    
    private final void syncContent() {
    }
    
    public final void login(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    public final void toggleBookmark(int storyId) {
    }
    
    public final void updateReadingProgress(int storyId, int progress) {
    }
    
    public final void resetAppProgress() {
    }
    
    public final void logout() {
    }
    
    public final void deleteAccount(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onComplete) {
    }
    
    public final void onSearch(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
}