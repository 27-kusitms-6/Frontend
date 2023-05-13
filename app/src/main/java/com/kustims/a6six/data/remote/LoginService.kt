package com.kustims.a6six.data.remote

import com.kustims.a6six.domain.model.LoginGoogleRequest
import com.kustims.a6six.domain.model.LoginGoogleResponse
import com.kustims.a6six.domain.model.LoginRequest
import com.kustims.a6six.domain.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.POST

interface LoginService {
    //ID_TOKEN 요청 API
    @POST("auth/google/idToken")
    suspend fun fetchAuthInfo(@Body request: LoginRequest): Response<LoginResponse>?

    @POST("oauth2/v4/token")
    suspend fun fetchGoogleAuthInfo(@Body request: LoginGoogleRequest): Response<LoginGoogleResponse>?
}