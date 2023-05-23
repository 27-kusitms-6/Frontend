package com.kustims.a6six.data.model.request


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class LoginRequest (
    @SerializedName("idToken")
    private val idToken: String
)