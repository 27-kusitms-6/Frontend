package com.kustims.a6six.data.response

import com.kustims.a6six.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val user: User? = null,
    val message: String? = null,
    @Transient
    val error: Exception? = null
)