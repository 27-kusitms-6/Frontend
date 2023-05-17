package com.kustims.a6six.app.domain.model


import com.google.gson.annotations.SerializedName

class LoginRequest (
    @SerializedName("idToken")
    private val idToken: String
)