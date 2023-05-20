package com.kustims.a6six.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kustims.a6six.Repository.LoginRepository
import com.kustims.a6six.app.Base.BaseViewModel
import com.kustims.a6six.ui.viewmodelstate.LoginState
import com.kustims.a6six.data.model.response.LoginGoogleResponse
import com.kustims.a6six.data.model.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): BaseViewModel() {

    private val _loginDetailText = MutableStateFlow<String?>(null)
    val loginDetailText = _loginDetailText.asStateFlow()

    fun login(loginToken: String) {
        loginRepository.login(loginToken)
    }
}