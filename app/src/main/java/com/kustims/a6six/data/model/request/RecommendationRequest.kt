package com.kustims.a6six.data.model.request

data class RecommendationRequest(
    val category2: String,
    val filters: List<String>,
    val orderBy: Int
)