package com.kustims.a6six.app.viewmodel

import android.preference.PreferenceManager
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.Repository.LoginRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kustims.a6six.app.viewmodelstate.LoginState
import com.kustims.a6six.domain.model.LoginGoogleResponse
import com.kustims.a6six.domain.model.LoginSlothResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : BaseViewModel() {
    private val loginRepository = LoginRepository()

    suspend fun fetchSlothAuthInfo(
        accessToken: String,
        socialType: String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): LoginState<LoginSlothResponse> = viewModelScope.async(
        context = context,
        start = start
    ) {
        loginRepository.fetchSlothAuthInfo(
            accessToken = accessToken,
            socialType = socialType
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