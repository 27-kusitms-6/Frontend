package com.kustims.a6six.data.model.response


data class HomeTop2Response(
    val data: Data,
    val message: String
) {
    data class Cafe(
        val id: Int,
        val img: String,
        val name: String
    )

    data class Play(
        val id: Int,
        val img: String,
        val name: String
    )

    data class Restaurant(
        val id: Int,
        val img: String,
        val name: String
    )

    data class Data(
        val cafe: List<Cafe>,
        val play: List<Play>,
        val restaurant: List<Restaurant>
    )
}

