package com.kustims.a6six.data.model.response


data class GetUserInfoResponse(
    val data: Data,
    val message: String
) {

    data class Data(
        val birthDate: String = "",
        val email: String = "",
        val filters: List<String> = emptyList(),
        val imgUrl: String = "",
        val name: String = "",
        val nickname: String = "",
        val phoneNum: String = "",
    )
    {
        companion object {
            val Empty = GetUserInfoResponse(Data(), "")
        }

    }

}