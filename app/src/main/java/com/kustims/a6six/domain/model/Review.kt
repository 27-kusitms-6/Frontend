package com.kustims.a6six.domain.model

import com.google.gson.annotations.SerializedName

data class Review (
    val name : String,
    val content: String,
    var dislikeCount: Int = 0,
    val id : Int = 1,
    val img : String,
    var likeCount: Int = 0,
    var starRating: Int = 0,
    var stickers: List<String>,
    val username: String
)

