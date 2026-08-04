package com.storywave.app.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001d\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001d\u001a\u00020\fH\u0082@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u001f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010 \u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010\'R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/storywave/app/repository/StoriesRepository;", "", "storyDao", "Lcom/storywave/app/data/local/StoryDao;", "userDao", "Lcom/storywave/app/data/local/UserDao;", "retrofitService", "Lcom/storywave/app/data/remote/RetrofitService;", "(Lcom/storywave/app/data/local/StoryDao;Lcom/storywave/app/data/local/UserDao;Lcom/storywave/app/data/remote/RetrofitService;)V", "deleteUser", "", "email", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCachedStories", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/storywave/app/model/Story;", "getRemoteCategories", "Lcom/storywave/app/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserByEmail", "Lcom/storywave/app/model/User;", "registerUser", "", "user", "(Lcom/storywave/app/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDatabaseInRoom", "searchStories", "query", "searchStoriesLocally", "seedDatabaseIfEmpty", "syncStoriesWithBackend", "toggleBookmarkInRoom", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgressInRoom", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class StoriesRepository {
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.local.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.local.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.storywave.app.data.remote.RetrofitService retrofitService = null;
    
    public StoriesRepository(@org.jetbrains.annotations.NotNull
    com.storywave.app.data.local.StoryDao storyDao, @org.jetbrains.annotations.NotNull
    com.storywave.app.data.local.UserDao userDao, @org.jetbrains.annotations.NotNull
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
    public final java.lang.Object registerUser(@org.jetbrains.annotations.NotNull
    com.storywave.app.model.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getUserByEmail(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.storywave.app.model.User> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteUser(@org.jetbrains.annotations.NotNull
    java.lang.String email, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
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
    public final java.lang.Object resetDatabaseInRoom(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}