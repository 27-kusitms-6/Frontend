package com.kustims.a6six.di


import com.kustims.a6six.BuildConfig
import com.kustims.a6six.data.remote.LoginService
import com.kustims.a6six.data.Constants.CONNECT_TIME_OUT
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.Constants.READ_TIME_OUT
import com.kustims.a6six.data.Constants.WRITE_TIME_OUT
import com.kustims.a6six.data.network.ApiProvider
import com.kustims.a6six.data.network.MypageApi
import com.kustims.a6six.data.network.UserApi
import com.kustims.a6six.data.network.oAuthApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun MypageApi(): MypageApi {
        return ApiProvider.retrofit.create(MypageApi::class.java)
    }

    @Singleton
    @Provides
    fun oAuthApi(): oAuthApi {
        return ApiProvider.retrofit.create(oAuthApi::class.java)
    }

    @Singleton
    @Provides
    fun UserApi(): UserApi {
        return ApiProvider.retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}


