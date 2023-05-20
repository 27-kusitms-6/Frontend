package com.kustims.a6six.data.util

import android.content.Context
import javax.inject.Inject
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.io.IOException

class UserPreferencesRepository @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
    }

    private val Context.userDataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )


    suspend fun clear() {
        context.userDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private object PreferencesKeys {
        val KEY_USER_ID = intPreferencesKey("KEY_USER_ID")
        val KEY_NICKNAME = stringPreferencesKey("KEY_NICKNAME")
        val KEY_PROFILE_IMAGE_INDEX = intPreferencesKey("KEY_PROFILE_IMAGE_INDEX")
    }

    val userPrefFlow: Flow<UserPref?> = context.userDataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            // Get our show completed value, defaulting to false if not set:
            val userId = preferences[PreferencesKeys.KEY_USER_ID]
            val nickname = preferences[PreferencesKeys.KEY_NICKNAME]
            val profileImageIndex = preferences[PreferencesKeys.KEY_PROFILE_IMAGE_INDEX]
            UserPref(
                id = userId ?: return@map null,
                profileImageIndex = profileImageIndex,
                nickname = nickname,
            ).also {
                Log.i("UserPreferencesRepository","+++ UserPref: $it")
            }
        }


    fun getSyncUserPref(): UserPref {
        var userPref: UserPref? = null
        runBlocking {
            context.userDataStore.data.first { preferences ->
                val userId = preferences[PreferencesKeys.KEY_USER_ID]
                val nickname = preferences[PreferencesKeys.KEY_NICKNAME]
                val profileImageIndex = preferences[PreferencesKeys.KEY_PROFILE_IMAGE_INDEX]
                userPref = UserPref(
                    id = userId!!,
                    profileImageIndex = profileImageIndex,
                    nickname = nickname,
                )
                true
            }
        }
        return userPref!!
    }

    suspend fun updateUserPreferences(user: UserPref) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_USER_ID] = user.id
            user.nickname?.let {
                preferences[PreferencesKeys.KEY_NICKNAME] = it
            }
            user.profileImageIndex?.let {
                preferences[PreferencesKeys.KEY_PROFILE_IMAGE_INDEX] = it
            }
        }
    }

    suspend fun saveProfileImage(profileImageIndex: Int) {
        context.userDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_PROFILE_IMAGE_INDEX] = profileImageIndex
        }
    }
}