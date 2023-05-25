package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.request.SetFiltersRequest
import com.kustims.a6six.data.model.response.RegenTokenResponse
import com.kustims.a6six.data.model.response.SetFilterReponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("user/regenerateToken")
    suspend fun regenToken(): RegenTokenResponse

    @POST("user/setFilters")
    suspend fun setFilters(@Body request: SetFiltersRequest): Response<SetFilterReponse>

}