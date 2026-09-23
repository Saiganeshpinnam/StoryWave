package com.storywave.app.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u000e\u0010!\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\"\u001a\u00020\u001bH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010&J\u001e\u0010\'\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%2\u0006\u0010(\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010)R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/storywave/app/repository/StoriesRepository;", "", "storyDao", "Lcom/storywave/app/data/local/StoryDao;", "retrofitService", "Lcom/storywave/app/data/remote/RetrofitService;", "(Lcom/storywave/app/data/local/StoryDao;Lcom/storywave/app/data/remote/RetrofitService;)V", "getCachedStories", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/storywave/app/model/Story;", "getRemoteCategories", "Lcom/storywave/app/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserStats", "Lcom/storywave/app/model/UserStats;", "login", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "request", "Lcom/storywave/app/model/LoginRequest;", "(Lcom/storywave/app/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginFallback", "register", "Lcom/storywave/app/model/RegisterRequest;", "(Lcom/storywave/app/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDatabaseInRoom", "", "searchStories", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchStoriesLocally", "seedDatabaseIfEmpty", "syncStoriesWithBackend", "toggleBookmarkInRoom", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgressInRoom", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class StoriesRepository {
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.local.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.remote.RetrofitService retrofitService = null;
    
    public StoriesRepository(@org.jetbrains.annotations.NotNull
    com.storywave.app.data.local.StoryDao storyDao, @org.jetbrains.annotations.NotNull
    com.storywave.app.data.remote.RetrofitService retrofitService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.storywave.app.model.Story>> getCachedStories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object seedDatabaseIfEmpty(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull
    com.storywave.app.model.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object loginFallback(@org.jetbrains.annotations.NotNull
    com.storywave.app.model.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull
    com.storywave.app.model.RegisterRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object syncStoriesWithBackend(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getRemoteCategories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Category>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchStories(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Story>> $completion) {
        return null;
    }
    
    private final java.lang.Object searchStoriesLocally(java.lang.String query, kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Story>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object toggleBookmarkInRoom(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateProgressInRoom(int id, int progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserStats(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.storywave.app.model.UserStats> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object resetDatabaseInRoom(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}