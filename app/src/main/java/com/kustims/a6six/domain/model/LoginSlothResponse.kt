package com.kustims.a6six.domain.model

data class LoginSlothResponse(
    var accessToken: String = "",
    var accessTokenExpireTime: String = "",
    var refreshToken: String = "",
    var refreshTokenExpireTime: String = ""
)