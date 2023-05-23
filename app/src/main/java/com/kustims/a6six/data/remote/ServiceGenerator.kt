package com.kustims.a6six.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kustims.a6six.BuildConfig
import com.kustims.a6six.data.Constants
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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

        return retrofit
    }

    fun <S> Retrofit.createService(
        serviceClass: Class<S>
    ): S = retrofit.create(serviceClass)


}