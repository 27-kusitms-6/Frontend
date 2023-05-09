package com.kustims.a6six.data.request

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val tokenId: String
)