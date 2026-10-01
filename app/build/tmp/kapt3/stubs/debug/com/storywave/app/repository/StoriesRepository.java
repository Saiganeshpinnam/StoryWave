package com.storywave.app.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00a2\u0006\u0002\u0010 J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@\u00a2\u0006\u0002\u0010 J\u000e\u0010\"\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010#\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u001e\u0010(\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010*R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/storywave/app/repository/StoriesRepository;", "", "storyDao", "Lcom/storywave/app/data/local/StoryDao;", "retrofitService", "Lcom/storywave/app/data/remote/RetrofitService;", "localRetrofitService", "(Lcom/storywave/app/data/local/StoryDao;Lcom/storywave/app/data/remote/RetrofitService;Lcom/storywave/app/data/remote/RetrofitService;)V", "getCachedStories", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/storywave/app/model/Story;", "getRemoteCategories", "Lcom/storywave/app/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserStats", "Lcom/storywave/app/model/UserStats;", "login", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "request", "Lcom/storywave/app/model/LoginRequest;", "(Lcom/storywave/app/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginFallback", "register", "Lcom/storywave/app/model/RegisterRequest;", "(Lcom/storywave/app/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDatabaseInRoom", "", "searchStories", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchStoriesLocally", "seedDatabaseIfEmpty", "syncStoriesWithBackend", "toggleBookmarkInRoom", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgressInRoom", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class StoriesRepository {
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.local.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.remote.RetrofitService retrofitService = null;
    @org.jetbrains.annotations.Nullable
    private final com.storywave.app.data.remote.RetrofitService localRetrofitService = null;
    
    public StoriesRepository(@org.jetbrains.annotations.NotNull
    com.storywave.app.data.local.StoryDao storyDao, @org.jetbrains.annotations.NotNull
    com.storywave.app.data.remote.RetrofitService retrofitService, @org.jetbrains.annotations.Nullable
    com.storywave.app.data.remote.RetrofitService localRetrofitService) {
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