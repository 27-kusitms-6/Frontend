package com.kustims.a6six.data.network

import com.kustims.a6six.data.model.response.GetReviewResponse
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.model.response.SetUserInfoReponse
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MypageService {

    @PATCH("mypage/filter")


    @GET("mypage/info")
    suspend fun getUserInfo() : GetUserInfoResponse

    @GET("mypage/review")
    suspend fun getUserReview() : GetReviewResponse

    @PATCH("mypage/info")
    suspend fun setUserInfo(): SetUserInfoReponse


}