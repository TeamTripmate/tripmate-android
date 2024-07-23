package com.tripmate.android.core.datastore.di

import com.tripmate.android.core.datastore.OnboardingDataSource
import com.tripmate.android.core.datastore.OnboardingDataSourceImpl
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
    abstract fun bindOnboardingDataSource(onboardingDataSourceImpl: OnboardingDataSourceImpl): OnboardingDataSource
}
