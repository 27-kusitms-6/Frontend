package com.kustims.a6six.Repository

import com.kustims.a6six.data.DataResult

interface AuthenticationRepository {
    suspend fun authenticateWithBackend(googleToken: String): DataResult<String>
}