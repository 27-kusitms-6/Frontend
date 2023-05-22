package com.kustims.a6six.data.model.response

data class GetUserInfoResponse(
    var birthDate: String,
    var email: String,
    var filters: List<String>,
    var imgUrl : String,
    var name: String,
    var nickname: String,
    var phoneNum: String
)
