package com.kustims.a6six.data.model.response

import com.google.gson.annotations.SerializedName

data class GetBookMarkResponse(
    val data: List<Data>,
    val message: String
) {
    data class Data(
        @SerializedName("id")
        val id: Int =0 ,
        @SerializedName("img")
        val img: String = "",
        @SerializedName("name")
        val name: String = "",
    )

}



