package com.storywave.app.data.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\r\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ(\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u001d\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/storywave/app/data/remote/RetrofitService;", "", "getCategories", "", "Lcom/storywave/app/model/Category;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStories", "Lcom/storywave/app/model/Story;", "getUserStats", "Lcom/storywave/app/model/UserStats;", "login", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "request", "Lcom/storywave/app/model/LoginRequest;", "(Lcom/storywave/app/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginFallback", "register", "Lcom/storywave/app/model/RegisterRequest;", "(Lcom/storywave/app/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchStories", "query", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleBookmark", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "progress", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RetrofitService {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object login(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.storywave.app.model.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion);
    
    @retrofit2.http.POST(value = "login")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object loginFallback(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.storywave.app.model.LoginRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion);
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object register(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.storywave.app.model.RegisterRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion);
    
    @retrofit2.http.GET(value = "stories")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Story>> $completion);
    
    @retrofit2.http.GET(value = "categories")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCategories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Category>> $completion);
    
    @retrofit2.http.GET(value = "stories/search")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchStories(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.storywave.app.model.Story>> $completion);
    
    @retrofit2.http.POST(value = "stories/{id}/bookmark")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleBookmark(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion);
    
    @retrofit2.http.POST(value = "stories/{id}/progress")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProgress(@retrofit2.http.Path(value = "id")
    int id, @retrofit2.http.Query(value = "progress")
    int progress, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> $completion);
    
    @retrofit2.http.GET(value = "user/stats")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUserStats(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.storywave.app.model.UserStats> $completion);
}