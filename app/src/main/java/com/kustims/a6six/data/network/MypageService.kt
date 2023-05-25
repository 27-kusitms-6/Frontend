package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.request.SetFiltersRequest
import com.kustims.a6six.data.model.response.GetReviewUserResponse
import com.kustims.a6six.data.model.response.GetBookMarkResponse
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.model.response.SetFilterReponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MypageService {
    @GET("mypage/info")
    suspend fun getUserInfo() : Response<GetUserInfoResponse>?

    @GET("mypage/review")
    suspend fun getUserReview() : Response<GetReviewUserResponse>?

    @PATCH("mypage/filter")
    suspend fun setFilter( @Body request:SetFiltersRequest) : Response<SetFilterReponse>?

    @GET("mypage/bookmark")
    suspend fun getBookmark() : Response<GetBookMarkResponse>?



}