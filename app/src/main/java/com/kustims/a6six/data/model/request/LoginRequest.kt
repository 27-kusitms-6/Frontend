package com.kustims.a6six.data.model.request


import com.google.gson.annotations.SerializedName

class LoginRequest (
    @SerializedName("idToken")
    private val idToken: String
)