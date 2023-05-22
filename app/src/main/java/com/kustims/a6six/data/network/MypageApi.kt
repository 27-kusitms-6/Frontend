package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.response.GetReviewResponse
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.domain.model.User
import com.squareup.okhttp.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MypageApi {

    @PATCH("mypage/filter")


    @GET("mypage/info")
    suspend fun getUserInfo() : GetUserInfoResponse

    @PATCH("mypage/info")


    @GET("mypage/review")
    suspend fun getReview() : GetReviewResponse

}