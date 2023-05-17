package com.kustims.a6six.data.remote


import com.kustims.a6six.data.util.AuthDataStore
import okhttp3.Interceptor
import okhttp3.Response

class
AuthTokenInterceptor(
    private val authDataStore: AuthDataStore = AuthDataStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authToken = authDataStore.authToken
        val tokenRequest = if (authToken.isNotEmpty()) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $authToken")
                .build()
        } else {
            chain.request().newBuilder()
                .build()
        }
        return chain.proceed(tokenRequest)
    }
