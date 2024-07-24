package com.tripmate.android.core.data.di

import com.tripmate.android.core.data.repository.OnboardingRepository
import com.tripmate.android.core.data.repository.OnboardingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(onboardingRepositoryImpl: OnboardingRepositoryImpl): OnboardingRepository
}
