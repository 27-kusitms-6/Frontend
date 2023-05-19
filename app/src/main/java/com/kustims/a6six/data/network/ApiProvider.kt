package com.kustims.a6six.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kustims.a6six.BuildConfig
import com.kustims.a6six.data.Constants
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.remote.AuthTokenInterceptor
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
object ApiProvider {

    private const val BASE_URL = LIKEIT_BASE_URL
    private val contentType = "application/json".toMediaType()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
        coerceInputValues = true
        encodeDefaults = true
    }
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthTokenInterceptor())
        .addInterceptor(logger)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(client)
        .build()



}