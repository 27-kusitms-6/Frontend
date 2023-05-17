package com.kustims.a6six.data.model.response

data class LoginResponse(
    var id: Int,
    //Access Token
    var atk: String,
    //Refresh Token
    var rtk: String,
    //Sign Up
    var signUp: Boolean
)