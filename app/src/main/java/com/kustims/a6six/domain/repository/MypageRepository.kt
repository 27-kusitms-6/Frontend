package com.kustims.a6six.domain.repository

import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.request.SetFiltersRequest
import com.kustims.a6six.data.model.response.GetBookMarkResponse
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.model.response.SetFilterReponse
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

    suspend fun setFilter(accessToken: String, _filters: List<String>): MypageState<SetFilterReponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl = LIKEIT_BASE_URL,
            authToken = accessToken
        )
            .create(MypageService::class.java)
            .setFilter(
                SetFiltersRequest(
                    filters = _filters
                )
            )?.run {
                return MypageState.Success(
                    this.body() ?: SetFilterReponse(
                        message = this.body()?.message ?:""
                    )
                )
            } ?: return MypageState.Error(Exception("Retrofit Exception"))
    }

    suspend fun getBookmark(accessToken: String): MypageState<GetBookMarkResponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl =  LIKEIT_BASE_URL,
            authToken = accessToken
        )
            .create(MypageService::class.java)
            .getBookmark()?.run {
                return MypageState.Success(
                    this.body() ?: GetBookMarkResponse(
                        data = (this.body()?.data ?: GetBookMarkResponse.Data()) as List<GetBookMarkResponse.Data>,
                        message = this.body()?.message ?: ""
                    )
                )
            } ?: return MypageState.Error(Exception("Retrofit Exception"))
    }

}



