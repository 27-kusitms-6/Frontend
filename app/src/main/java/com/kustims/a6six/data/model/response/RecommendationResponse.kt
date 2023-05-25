package com.kustims.a6six.data.model.response

data class RecommendationResponse(
    val data: Data,
    val message: String
) {
    data class Data(
        val places: List<Place>
    )

    data class Place(
        val content: String,
        val id: Int,
        val isBookmarked: String,
        val name: String,
        val openingHours: String,
        val placeImg: String,
        val reviewCount: Int,
        val starRating: Float,
        val top2stickers: List<RecommendationResponse.Top2sticker>
    )

    data class Top2sticker(
        val id: Int,
        val sticker: String,
        val url: String
    )
}

