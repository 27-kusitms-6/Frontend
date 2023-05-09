package com.kustims.a6six.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveSignedInState(signedInState: Boolean)
    fun readSignedInState(): Flow<Boolean>
}