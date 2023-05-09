package com.kustims.a6six.data.repository

import androidx.datastore.core.DataStore
import java.util.prefs.Preferences
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
}