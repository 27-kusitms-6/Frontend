package com.kustims.a6six.data.model.response

import com.kustims.a6six.domain.model.Review

data class DetailPlaceResponse(
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
        val placeImg: String,
        val reviewCount: Int,
        val reviews: List<Review>,
        val starRating: Double,
        val top2NegativeStickerCount: List<Int>,
        val top2NegativeStickers: List<String>,
        val top2PositiveStickerCount: List<Int>,
        val top2PositiveStickers: List<String>,
        val top2PositiveStickerName: List<String>,
        val top2NegativeStickerName: List<String>,
    )

    data class Review(
        val content: String,
        val dislikeCount: Int,
        val id: Int,
        val img: String,
        val likeCount: Int,
        val starRating: Double,
        val stickers: List<Sticker>,
        val username: String
    )

    data class Sticker(
        val id: Int,
        val sticker: String,
        val url: String
    )

}