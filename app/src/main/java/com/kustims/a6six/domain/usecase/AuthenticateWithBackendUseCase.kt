package com.kustims.a6six.data.usecase

import com.kustims.a6six.Repository.AuthenticationRepository
import com.kustims.a6six.data.DataResult

class AuthenticateWithBackendUseCase(private val authenticateRepository: AuthenticationRepository) {
    suspend operator fun invoke(googleToken: String): DataResult<String>? =
        authenticateRepository.authenticateWithBackend(googleToken).checkResultAndReturn(
            onSuccess = { DataResult.Success(it) },
            onError = { DataResult.Error(it) }
        )
}