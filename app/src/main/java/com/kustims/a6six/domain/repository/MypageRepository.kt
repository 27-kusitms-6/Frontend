package com.kustims.a6six.domain.repository

import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.network.MypageService
import com.kustims.a6six.di.ServiceGenerator
import com.kustims.a6six.ui.viewmodelstate.MypageState

class MypageRepository {

    suspend fun getUserInfo(accessToken:String): MypageState<GetUserInfoResponse>
    {
        ServiceGenerator.setBuilderOptions(
            targetUrl = LIKEIT_BASE_URL,
            authToken = accessToken
        )
            .create(MypageService::class.java)
            .getUserInfo()?.run {
                return MypageState.Success(
                    this.body() ?: GetUserInfoResponse(
                        data = this.body()?.data ?: GetUserInfoResponse.Data(),
                        message = this.body()?.message ?: ""
                    )
                )
            } ?: return MypageState.Error(Exception("Retrofit Exception"))
    }




}