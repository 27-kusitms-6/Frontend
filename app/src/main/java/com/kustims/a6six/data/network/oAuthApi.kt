package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.request.LoginRequest
import com.kustims.a6six.data.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface oAuthApi {

    @POST("/auth/google/idToken")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

}