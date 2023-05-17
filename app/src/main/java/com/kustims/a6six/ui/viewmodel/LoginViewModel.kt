package com.kustims.a6six.app.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kustims.a6six.Repository.LoginRepository
import com.kustims.a6six.app.Base.BaseViewModel
import com.kustims.a6six.app.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : BaseViewModel() {
    private val loginRepository = LoginRepository()

    suspend fun fetchAuthInfo(
        accessToken: String,
        socialType: String,
        idToken :String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): LoginState<LoginResponse> = viewModelScope.async(
        context = context,
        start = start
    ) {
        loginRepository.fetchAuthInfo(
            accessToken = accessToken,
            idToken = idToken
//            socialType = socialType
        )
    }.await()

    suspend fun fetchGoogleAuthInfo(
        authCode: String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): LoginState<LoginGoogleResponse> = viewModelScope.async(
        context = context,
        start = start
    ) {
        loginRepository.fetchGoogleAuthInfo(
            authCode = authCode
        )
    }.await()

}