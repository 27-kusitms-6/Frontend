package com.kustims.a6six.Repository

import com.kustims.a6six.data.util.Constants.GOOGLE_BASE_URL
import com.kustims.a6six.data.util.Constants.GOOGLE_CLIENT_ID
import com.kustims.a6six.data.util.Constants.GOOGLE_CLIENT_SECRET
import com.kustims.a6six.data.util.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.response.LoginGoogleResponse

import com.kustims.a6six.data.remote.ServiceGenerator
import com.kustims.a6six.app.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.remote.LoginService
import com.kustims.a6six.data.remote.ServiceGenerator.createService
import com.kustims.a6six.data.model.request.LoginGoogleRequest
import com.kustims.a6six.data.model.request.LoginRequest
import com.kustims.a6six.data.model.response.LoginResponse

class LoginRepository {
    suspend fun fetchAuthInfo(accessToken: String, idToken:String):
            LoginState<LoginResponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl = LIKEIT_BASE_URL,
            authToken = accessToken
        ).createService(
            serviceClass = LoginService::class.java,
        ).fetchAuthInfo(
            LoginRequest(
                idToken = idToken
//                socialType = socialType
            )
        )?.run {
            return LoginState.Success(
                this.body() ?: LoginResponse()
            )
        } ?: return LoginState.Error(Exception("Login Exception"))
    }

    suspend fun fetchGoogleAuthInfo(authCode: String): LoginState<LoginGoogleResponse> {
        ServiceGenerator.setBuilderOptions(
            targetUrl = GOOGLE_BASE_URL
        ).createService(
            serviceClass = LoginService::class.java
        ).fetchGoogleAuthInfo(
            LoginGoogleRequest(
                grant_type = "authorization_code",
                client_id = GOOGLE_CLIENT_ID,
                client_secret = GOOGLE_CLIENT_SECRET,
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