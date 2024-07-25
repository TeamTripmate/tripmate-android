package com.tripmate.android.core.datastore.di

import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.PersonalizationDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindPersonalizationDataSource(personalizationDataSourceImpl: PersonalizationDataSourceImpl): PersonalizationDataSource
}
