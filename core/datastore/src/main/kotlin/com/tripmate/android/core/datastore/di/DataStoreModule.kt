package com.tripmate.android.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val PERSONALIZATION_DATASTORE = "personalization_datastore"
private val Context.personalizationDataStore: DataStore<Preferences> by preferencesDataStore(name = PERSONALIZATION_DATASTORE)

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @PersonalizationDataStore
    @Singleton
    @Provides
    internal fun providePersonalizationDataStore(@ApplicationContext context: Context) = context.personalizationDataStore
}
