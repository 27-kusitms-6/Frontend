package com.kustims.a6six.Repository

import com.kustims.a6six.data.Constants.GOOGLE_BASE_URL
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_ID
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_SECRET
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.response.LoginGoogleResponse

import com.kustims.a6six.data.remote.ServiceGenerator
import com.kustims.a6six.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.remote.LoginService
import com.kustims.a6six.data.remote.ServiceGenerator.createService
import com.kustims.a6six.data.model.request.LoginGoogleRequest
import com.kustims.a6six.data.model.request.LoginRequest
import com.kustims.a6six.data.model.response.LoginResponse
import com.kustims.a6six.data.network.oAuthApi
import com.kustims.a6six.data.util.AuthDataStore
import com.kustims.a6six.data.util.UserPreferencesRepository
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val oAuthApi: oAuthApi,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val authDataStore:AuthDataStore
) {

//    suspend fun login(idToken: String): String {
//        val jwt = oAuthApi.login(LoginRequest(idToken)).token
//        if(jwt.isN)
//    }

    suspend fun logOut() {
        clearUserCache()
    }

    private suspend fun clearUserCache() {
        userPreferencesRepository.clear()
        authDataStore.authToken = ""
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