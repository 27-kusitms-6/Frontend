package com.kustims.a6six.app.domain.model

data class LoginResponse(
    var id: String = "",
    //Access Token
    var atk: String = "",
    //Refresh Token
    var rtk: String = "",
    //Sign Up
    var signUp: String = "",
)