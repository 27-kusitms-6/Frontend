package com.kustims.a6six.data

import com.kustims.a6six.Repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource,
) : LoginRepository {

    override suspend fun authenticateWithBackend(googleToken: String): GoogleLoginResult<String> =
        withContext(ioDispatcher) {
            try {
                val apiToken = authenticationRemoteDataSource.authenticateWithBackend(googleToken)
                GoogleLoginResult.Success(apiToken)
            } catch (e: Exception) {
                GoogleLoginResult.Error(CustomError.AUTHENTICATION_ERROR)
            }
        }
}