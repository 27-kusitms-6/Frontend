package com.kustims.a6six.Repository

import android.util.Log
import com.kustims.a6six.data.Constants.GOOGLE_BASE_URL
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_ID
import com.kustims.a6six.data.Constants.GOOGLE_CLIENT_SECRET
import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
import com.kustims.a6six.data.model.response.LoginGoogleResponse

import com.kustims.a6six.data.remote.ServiceGenerator
import com.kustims.a6six.ui.viewmodelstate.LoginState
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

    fun login(oAuthToken: String): String {
        val jwt = oAuthApi.login(LoginRequest(oAuthToken)).token
        if(jwt.isNotEmpty()) {
            authDataStore.authToken = jwt
            Log.d("GOOGLE_LOGIN", "$oAuthToken")
            Log.d("GOOGLE_LOGIN", "$jwt: $jwt")
        } else {
            throw IllegalStateException("Token is Empty.")
        }
        return jwt
    }
    suspend fun logOut() {
        clearUserCache()
    }

    private suspend fun clearUserCache() {
        userPreferencesRepository.clear()
        authDataStore.authToken = ""
    }

}