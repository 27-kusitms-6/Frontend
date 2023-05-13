package com.kustims.a6six.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.kustims.a6six.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor

object ServiceGenerator {
    private const val timeoutRead = 30
    private const val timeoutConnect = 30

    private lateinit var retrofit: Retrofit
    private val builder: Retrofit.Builder = Retrofit.Builder()

    fun setBuilderOptions(
        targetUrl: String,
        authToken: String? = null
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val authInterceptor = AuthenticationInterceptor(authToken)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        httpClient.apply {
            httpClient.apply {
                addInterceptor(authInterceptor)
                addInterceptor(loggingInterceptor)
                connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
                readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
            }
            retrofit = builder
                .baseUrl(targetUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    fun <S> Retrofit.createService(
        serviceClass: Class<S>
    ): S = retrofit.create(serviceClass)


}