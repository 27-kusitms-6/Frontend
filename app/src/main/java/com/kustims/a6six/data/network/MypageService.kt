package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.request.SetFiltersRequest
import com.kustims.a6six.data.model.response.GetReviewResponse
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.model.response.SetFilterReponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MypageService {
    @GET("mypage/info")
    suspend fun getUserInfo() : Response<GetUserInfoResponse>?

    @GET("mypage/review")
    suspend fun getUserReview() : GetReviewResponse

    @PATCH("mypage/filter")
    suspend fun setFilter( @Body request:SetFiltersRequest) : Response<SetFilterReponse>?



}