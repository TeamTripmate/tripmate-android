package com.tripmate.android.core.datastore.di

import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.PersonalizationDataSourceImpl
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.datastore.TokenDataSourceImpl
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

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(tokenDataSourceImpl: TokenDataSourceImpl): TokenDataSource
}
