package com.tripmate.android.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.tripmate.android.core.datastore.di.PersonalizationDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class PersonalizationDataSourceImpl @Inject constructor(
    @PersonalizationDataStore private val dataStore: DataStore<Preferences>,
) : PersonalizationDataSource {
    private companion object {
        private val KEY_PERSONALIZATION_COMPLETE = booleanPreferencesKey("personalization_complete")
    }

    override suspend fun checkPersonalizationCompletion(): Boolean = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.first()[KEY_PERSONALIZATION_COMPLETE] ?: false

    override suspend fun completePersonalization(flag: Boolean) {
        dataStore.edit { preferences -> preferences[KEY_PERSONALIZATION_COMPLETE] = flag }
    }
}
