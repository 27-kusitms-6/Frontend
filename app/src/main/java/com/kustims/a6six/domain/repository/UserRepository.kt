package com.kustims.a6six.domain.repository

import com.kustims.a6six.data.model.UserUpdate
import com.kustims.a6six.data.request.ApiRequest
import com.kustims.a6six.data.response.ApiResponse

interface UserRepository {
    suspend fun verifyUserOnBackend(request: ApiRequest): ApiResponse
    suspend fun getUserInfo(): ApiResponse
    suspend fun updateUser(userUpdate: UserUpdate): ApiResponse
    suspend fun deleteUser(): ApiResponse
    suspend fun clearSession(): ApiResponse
}