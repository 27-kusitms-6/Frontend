package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.request.LoginGoogleRequest
import com.kustims.a6six.data.model.request.RecommendationRequest
import com.kustims.a6six.data.model.response.DetailPlaceResponse
import com.kustims.a6six.data.model.response.HomePlaceResponse
import com.kustims.a6six.data.model.response.HomeTop2Response
import com.kustims.a6six.data.model.response.RecommendationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface APIS {

    //top2 정보
    @GET("/main/places/bookmark")
    suspend fun getTop2Data(): Response<HomeTop2Response>

    @GET("/main/places/filter")
    suspend fun getHomePlaceData(
        @Header("Authorization") accessToken: String
    ): Response<HomePlaceResponse>

    @POST("/place/list/restaurant")
    suspend fun getRecommendationRestaurant(
        @Header("Authorization") accessToken: String,
        @Body request: RecommendationRequest
    ): Response<RecommendationResponse>

    @GET(" /place/detail/{placeId}")
    suspend fun getDetailPlaceData(
        @Header("Authorization") accessToken: String,
        @Path("placeId") placeId: Int,
    ): Response<DetailPlaceResponse>


}