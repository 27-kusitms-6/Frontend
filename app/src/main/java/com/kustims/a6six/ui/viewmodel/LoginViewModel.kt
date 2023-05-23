package com.kustims.a6six.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kustims.a6six.Repository.LoginRepository
import com.kustims.a6six.app.Base.BaseViewModel
import com.kustims.a6six.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import com.kustims.a6six.data.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel: BaseViewModel() {
    private val loginRepository = LoginRepository()

    suspend fun fetchAuthInfo(
        accessToken: String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): LoginState<LoginResponse> = viewModelScope.async(
        context = context,
        start = start
    ) {
        loginRepository.fetchAuthInfo(
            accessToken = accessToken
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

    suspend fun saveAuthToken(pm: PreferenceManager, accessToken: String, refreshToken: String) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pm.putAuthToken(accessToken, refreshToken)
            }
        }

}