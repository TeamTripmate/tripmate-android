package com.tripmate.android.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.tripmate.android.core.datastore.di.PersonalizationDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    @PersonalizationDataStore private val dataStore: DataStore<Preferences>,
) : TokenDataSource {
    private companion object {
        private val KEY_ID = longPreferencesKey("id")
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val KEY_JWT_TOKEN = stringPreferencesKey("jwt_token")
    }

    override suspend fun saveAuthToken(id: Long, accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_ID] = id
            preferences[KEY_ACCESS_TOKEN] = accessToken
            preferences[KEY_REFRESH_TOKEN] = refreshToken
        }
    }

    override suspend fun saveJwtToken(jwtToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_JWT_TOKEN] = jwtToken
        }
    }

    override suspend fun getAccessToken(): String = getToken(KEY_ACCESS_TOKEN)

    override suspend fun getRefreshToken(): String = getToken(KEY_REFRESH_TOKEN)

    override suspend fun getId(): Long = getId(KEY_ID)

    override suspend fun getJwtToken(): String = getToken(KEY_JWT_TOKEN)

    private suspend fun getId(key: Preferences.Key<Long>): Long =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences -> preferences[key] ?: 0L }
            .first()

    private suspend fun getToken(key: Preferences.Key<String>): String =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences -> preferences[key] ?: "" }
            .first()

    override suspend fun clearAuthToken() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_ACCESS_TOKEN)
            preferences.remove(KEY_REFRESH_TOKEN)
        }
    }
}
