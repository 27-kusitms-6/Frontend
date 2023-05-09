package com.kustims.a6six.app.di

import androidx.datastore.core.DataStore
import com.kustims.a6six.data.repository.AuthRepositoryImpl
import com.kustims.a6six.domain.repository.AuthRepository
import com.kustims.a6six.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore: DataStore<Preferences>): AuthRepository {
        return AuthRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserRepository(ktorApi: KtorApi): UserRepository {
        return UserRepositoryImpl(ktorApi)
    }
}