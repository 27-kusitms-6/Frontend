package com.kustims.a6six.data.remote

import com.kustims.a6six.data.model.request.LoginGoogleRequest
import com.kustims.a6six.data.model.request.LoginRequest
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth/google/idToken")
    suspend fun fetchAuthInfo(@Body request: LoginRequest): Response<LoginResponse>?

    @POST("oauth2/v4/token")
    suspend fun fetchGoogleAuthInfo(@Body request: LoginGoogleRequest): Response<LoginGoogleResponse>?
}