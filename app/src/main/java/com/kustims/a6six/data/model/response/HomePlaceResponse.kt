package com.kustims.a6six.data.model.response

data class HomePlaceResponse(
    val data: List<Data>,
    val message: String
) {
    data class Data(
        val id: Int,
        val img: String,
        val name: String,
        val rating: Float,
        val stickers: List<Sticker>
    )

    data class Sticker(
        val id: Int,
        val sticker: String,
        val url: String
    )
}