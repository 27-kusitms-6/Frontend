package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.response.HomeTop2Response
import retrofit2.Response
import retrofit2.http.GET

interface APIS {

    //top2 정보
    @GET("/main/places/bookmark")
    suspend fun getTop2Data(): Response<HomeTop2Response>
}