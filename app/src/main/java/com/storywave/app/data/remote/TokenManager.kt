package com.storywave.app.data.remote

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("storywave_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "jwt_token"
    }

    fun saveAuthData(token: String) {
        prefs.edit().apply {
            putString(TOKEN_KEY, token)
            apply()
        }
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    fun registerUserLocally(email: String, password: String) {
        val key = "user_pwd_" + email.lowercase().trim()
        prefs.edit().putString(key, password).apply()
    }

    fun verifyLocalUser(email: String, password: String): Boolean {
        val key = "user_pwd_" + email.lowercase().trim()
        val storedPassword = prefs.getString(key, null)
        return storedPassword != null && storedPassword == password
    }

    fun isUserRegisteredLocally(email: String): Boolean {
        val key = "user_pwd_" + email.lowercase().trim()
        return prefs.contains(key)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
