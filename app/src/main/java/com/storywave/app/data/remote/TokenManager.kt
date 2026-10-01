package com.storywave.app.data.remote

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(
            "storywave_prefs",
            Context.MODE_PRIVATE
        )

    companion object {
        private const val TOKEN_KEY = "jwt_token"
    }

    fun saveAuthData(token: String) {

        val cleanToken = token
            .replace("\r", "")
            .replace("\n", "")
            .replace("\u200B", "")
            .trim()

        prefs.edit()
            .putString(TOKEN_KEY, cleanToken)
            .apply()
    }

    fun getToken(): String? {

        return prefs.getString(TOKEN_KEY, null)
            ?.replace("\r", "")
            ?.replace("\n", "")
            ?.replace("\u200B", "")
            ?.trim()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}