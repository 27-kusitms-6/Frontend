package com.kustims.a6six.data.model.response

data class GetReviewResponse(
    var content: String,
    var dislikeCount: Int = 0,
    var img: String,
    var likeCount: Int = 0,
    var nickname: String,
    var placeName: String,
    var starRating: Int = 0,
    var stickers: List<String>

)
