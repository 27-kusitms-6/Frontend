package com.kustims.a6six.ui.Login


data class LoginViewModelState(
    val loginStatus: LoginStatus = LoginStatus.Initial
)

enum class LoginStatus {
    Initial,
    Success,
    Loading,
    Failure
}