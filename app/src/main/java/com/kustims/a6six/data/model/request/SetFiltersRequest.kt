package com.kustims.a6six.data.model.request

import com.google.gson.annotations.SerializedName

data class SetFiltersRequest(
    @SerializedName("filters")
    var filters: List<String>
)
