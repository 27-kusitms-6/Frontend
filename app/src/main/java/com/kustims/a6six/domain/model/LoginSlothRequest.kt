package com.kustims.a6six.domain.model


import com.google.gson.annotations.SerializedName

class LoginSlothRequest (
    @SerializedName("socialType")
    private val socialType: String
)