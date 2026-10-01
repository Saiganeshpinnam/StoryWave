package com.storywave.app.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val path = request.url.encodedPath

        // Login/register requests don't need a JWT
        if (
            path.endsWith("/auth/login") ||
            path.endsWith("/login") ||
            path.endsWith("/auth/register") ||
            path.endsWith("/register")
        ) {
            return chain.proceed(request)
        }

        var token = tokenManager.getToken()

        if (!token.isNullOrBlank()) {

            // Remove whitespace/control/format characters
            token = token
                .filter { char ->
                    !char.isWhitespace() &&
                            Character.getType(char) != Character.FORMAT.toInt() &&
                            !char.isISOControl()
                }
                .trim()

            // A JWT should contain exactly 3 parts
            val parts = token.split(".")

            if (parts.size == 3 && token.length < 10000) {

                val requestBuilder = request.newBuilder()

                requestBuilder.header(
                    "Authorization",
                    "Bearer $token"
                )

                return chain.proceed(requestBuilder.build())
            } else {
                // Invalid/incorrect token - don't send it
                tokenManager.clear()
            }
        }

        return chain.proceed(request)
    }
}