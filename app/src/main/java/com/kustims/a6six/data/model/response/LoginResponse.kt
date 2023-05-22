package com.kustims.a6six.data.model.response

class LoginResponse(
    var id: Int = 1,
    //Access Token
    var atk: String ="",
    //Refresh Token
    var rtk: String = "",
    //Sign Up
    var signUp: Boolean = true
)