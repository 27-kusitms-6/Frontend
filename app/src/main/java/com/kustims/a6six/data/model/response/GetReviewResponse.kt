package com.kustims.a6six.data.model.response

import com.google.gson.annotations.SerializedName
import com.kustims.a6six.domain.model.Review

data class GetReviewResponse(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("dislikeCount")
    var dislikeCount: Int = 0,
    @SerializedName("id")
    val id : Int = 1,
    @SerializedName("img")
    val img : String ="",
    @SerializedName("likeCount")
    var likeCount: Int = 0,
    @SerializedName("starRating")
    var starRating: Int = 0,
    @SerializedName("stickers")
    var stickers: List<String>,
    @SerializedName("username")
    val username: String =""
)

fun GetReviewResponse.toReview() = Review(
    name,
    content,
    dislikeCount,
    id,
    img,
    likeCount,
    starRating,
    stickers,
    username
)
