package com.kustims.a6six.data

interface AuthenticationRemoteDataSource {
    suspend fun authenticateWithBackend(googleToken: String): String
}