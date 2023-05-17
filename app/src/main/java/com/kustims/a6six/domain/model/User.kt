package com.kustims.a6six.domain.model

data class User(
    var birthDate: String,
    var eamil: String,
    var filters: List<String>,
    var imgUrl : String,
    var name: String,
    var nickname: String,
    var phoneNum: String

)
