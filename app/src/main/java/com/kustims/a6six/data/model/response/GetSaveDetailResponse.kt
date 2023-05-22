package com.kustims.a6six.data.model.response

import com.google.gson.annotations.SerializedName
import com.kustims.a6six.domain.model.SaveDetail

data class GetSaveDetail(
    @SerializedName("id")
    val id : Int,
    @SerializedName("img")
    val img : String,
    @SerializedName("name")
    val name : String,
)

fun GetSaveDetail.toSaveDetail() = SaveDetail(
    id, img, name
)


