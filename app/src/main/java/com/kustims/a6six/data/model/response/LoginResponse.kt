package com.kustims.a6six.data.model.response


data class LoginResponse(
    val data: Data,
    val message: String
) {
    data class Data(
        val atk: String ="",
        val id: Int = 0,
        val rtk: String = "",
        val signUp:Boolean = false
    )
}