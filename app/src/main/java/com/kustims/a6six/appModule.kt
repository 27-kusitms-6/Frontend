package com.kustims.a6six

import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kustims.a6six.Repository.AuthenticationRepository
import com.kustims.a6six.data.AuthenticationRemoteDataSource
import com.kustims.a6six.data.AuthenticationRemoteDataSourceImpl
import com.kustims.a6six.data.AuthenticationRepositoryImpl
import com.kustims.a6six.app.viewmodel.LoginViewModel
import com.kustims.a6six.data.usecase.AuthenticateWithBackendUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//Complete the CLIENT_ID with your own client id
const val CLIENT_ID = "306778587401-0s3ko48g2c90id59aqphpqrsakh452j3.apps.googleusercontent.com"

val appModule = module {
    viewModel { LoginViewModel(get()) }

    factory { AuthenticateWithBackendUseCase(get()) }
    factory<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    factory<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl() }

    single { Dispatchers.IO }

    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(CLIENT_ID)
            .build()

        return GoogleSignIn.getClient(context, signInOptions)
    }

    single { getGoogleSignInClient(androidContext()) }
}