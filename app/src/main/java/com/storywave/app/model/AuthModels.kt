package com.storywave.app.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String? = null,
    val username: String? = null,
    val email: String? = null
)
