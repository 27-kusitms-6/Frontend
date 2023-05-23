package com.kustims.a6six.Repository

import android.util.Log
import com.google.android.gms.auth.api.credentials.IdToken
import com.kustims.a6six.data.Constants.GOOGLE_BASE_URL
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_ID
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_SECRET
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.remote.ServiceGenerator.createService
import com.kustims.a6six.data.model.request.LoginGoogleRequest
import com.kustims.a6six.data.model.request.LoginRequest
import com.kustims.a6six.data.model.response.LoginResponse
import com.kustims.a6six.data.network.ApiProvider
import com.kustims.a6six.data.network.oAuthApi
import kotlinx.coroutines.flow.Flow


class LoginRepository {

    suspend fun fetchAuthInfo(accessToken: String): LoginState<LoginResponse> {
        ApiProvider.setBuilderOptions(
            targetUrl = LIKEIT_BASE_URL,
            authToken = accessToken
        ).createService(
            serviceClass = oAuthApi::class.java,
        ).fetchAuthInfo(
            LoginRequest(
                idToken = accessToken
            )
        )?.run {
            return LoginState.Success(
                this.body() ?: LoginResponse()
            )
        } ?: return LoginState.Error(Exception("Login Exception"))
    }

    suspend fun fetchGoogleAuthInfo(authCode: String): LoginState<LoginGoogleResponse> {
        ApiProvider.setBuilderOptions(
            targetUrl = GOOGLE_BASE_URL
        ).createService(
            serviceClass = oAuthApi::class.java
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