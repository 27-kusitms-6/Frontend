package com.kustims.a6six.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor(private val accessToken: String?) : Interceptor {
    private val contentType = "Content-Type"
    private val contentTypeValue = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header(contentType, contentTypeValue)

        accessToken?.run {
            builder.header("Authorization", "Bearer $this")
        }

        val request: Request = builder.build()

        return chain.proceed(request)
    }
}





