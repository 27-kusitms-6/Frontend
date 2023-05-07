package com.kustims.a6six.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.data.usecase.AuthenticateWithBackendUseCase
import com.kustims.a6six.ui.Login.LoginStatus
import com.kustims.a6six.ui.Login.LoginViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticateWithBackUseCase: AuthenticateWithBackendUseCase
) : ViewModel() {
    private val loginViewModelState = MutableStateFlow(LoginViewModelState())

    val uiState = loginViewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            loginViewModelState.value
        )
    fun authenticateWithBackend(googleToken : String) {
        loginViewModelState.update {
            it.copy(loginStatus = LoginStatus.Loading)
        }
        viewModelScope.launch {
            authenticateWithBackUseCase(googleToken).checkResult(
                onSuccess =  {loginViewModelState.update { it.copy(loginStatus = LoginStatus.Success) }},
                onError = {loginViewModelState.update { it.copy(loginStatus = LoginStatus.Failure) }}

            )
        }
    }

    fun onLoginError(){
        loginViewModelState.update { it.copy(loginStatus = LoginStatus.Failure) }
    }
}