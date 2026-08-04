package com.storywave.app.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u001c\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/storywave/app/data/local/StoryDao;", "", "getAllStoriesFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/storywave/app/model/Story;", "getBookmarkedStoriesFlow", "insertStories", "", "stories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleBookmark", "storyId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao
public abstract interface StoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM stories ORDER BY id ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.storywave.app.model.Story>> getAllStoriesFlow();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertStories(@org.jetbrains.annotations.NotNull
    java.util.List<com.storywave.app.model.Story> stories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE stories SET isBookmarked = NOT isBookmarked WHERE id = :storyId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleBookmark(int storyId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE stories SET progress = :progress WHERE id = :storyId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProgress(int storyId, int progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM stories WHERE isBookmarked = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.storywave.app.model.Story>> getBookmarkedStoriesFlow();
}