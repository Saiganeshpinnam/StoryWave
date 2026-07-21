package com.example.englishstories.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\tH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u0016\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/englishstories/repository/StoriesRepository;", "", "storyDao", "Lcom/example/englishstories/data/local/StoryDao;", "retrofitService", "Lcom/example/englishstories/data/remote/RetrofitService;", "(Lcom/example/englishstories/data/local/StoryDao;Lcom/example/englishstories/data/remote/RetrofitService;)V", "getCachedStories", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/englishstories/model/Story;", "getRemoteCategories", "Lcom/example/englishstories/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetDatabaseInRoom", "", "searchStories", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchStoriesLocally", "seedDatabaseIfEmpty", "syncStoriesWithBackend", "toggleBookmarkInRoom", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgressInRoom", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class StoriesRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.englishstories.data.local.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.englishstories.data.remote.RetrofitService retrofitService = null;
    
    public StoriesRepository(@org.jetbrains.annotations.NotNull
    com.example.englishstories.data.local.StoryDao storyDao, @org.jetbrains.annotations.NotNull
    com.example.englishstories.data.remote.RetrofitService retrofitService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.englishstories.model.Story>> getCachedStories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object seedDatabaseIfEmpty(@org.jetbrains.annotations.NotNull
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
    kotlin.coroutines.Continuation<? super java.util.List<com.example.englishstories.model.Category>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchStories(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.englishstories.model.Story>> $completion) {
        return null;
    }
    
    private final java.lang.Object searchStoriesLocally(java.lang.String query, kotlin.coroutines.Continuation<? super java.util.List<com.example.englishstories.model.Story>> $completion) {
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