package com.kustims.a6six.Repository

import com.kustims.a6six.BuildConfig
import com.kustims.a6six.domain.model.LoginGoogleResponse
import kotlinx.coroutines.flow.Flow

import com.kustims.a6six.data.remote.ServiceGenerator
import com.kustims.a6six.app.viewmodelstate.LoginState
import com.kustims.a6six.data.remote.LoginService
import com.kustims.a6six.data.remote.ServiceGenerator.createService
import com.kustims.a6six.domain.model.LoginGoogleRequest
import com.kustims.a6six.domain.model.LoginSlothRequest
import com.kustims.a6six.domain.model.LoginSlothResponse

class LoginRepository {
    suspend fun fetchSlothAuthInfo(accessToken: String, socialType: String): LoginState<LoginSlothResponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl = BuildConfig.SLOTH_BASE_URL,
            authToken = accessToken
        ).createService(
            serviceClass = LoginService::class.java,
        ).fetchSlothAuthInfo(
            LoginSlothRequest(
                socialType = socialType
            )
        )?.run {
            return LoginState.Success(
                this.body() ?: LoginSlothResponse()
            )
        } ?: return LoginState.Error(Exception("Login Exception"))
    }

    suspend fun fetchGoogleAuthInfo(authCode: String): LoginState<LoginGoogleResponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl = BuildConfig.GOOGLE_BASE_URL
        ).createService(
            serviceClass = LoginService::class.java
        ).fetchGoogleAuthInfo(
            LoginGoogleRequest(
                grant_type = "authorization_code",
                client_id = BuildConfig.GOOGLE_CLIENT_ID,
                client_secret = BuildConfig.GOOGLE_CLIENT_SECRET,
                redirect_uri = "",
                code = authCode
            )
        )?.run {
            return LoginState.Success(
                this.body() ?: LoginGoogleResponse()
            )
        } ?: return LoginState.Error(Exception("Retrofit Exception"))
    }
}