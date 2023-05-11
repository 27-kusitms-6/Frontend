package com.kustims.a6six.data.remote

import com.kustims.a6six.domain.model.LoginGoogleRequest
import com.kustims.a6six.domain.model.LoginGoogleResponse
import com.kustims.a6six.domain.model.LoginSlothRequest
import com.kustims.a6six.domain.model.LoginSlothResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/oauth/login")
    suspend fun fetchSlothAuthInfo(@Body request: LoginSlothRequest): Response<LoginSlothResponse>?

    @POST("oauth2/v4/token")
    suspend fun fetchGoogleAuthInfo(@Body request: LoginGoogleRequest): Response<LoginGoogleResponse>?
}