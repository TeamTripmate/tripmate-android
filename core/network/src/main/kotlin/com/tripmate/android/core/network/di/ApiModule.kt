package com.tripmate.android.core.network.di

import com.tripmate.android.core.network.TripmateApi
import com.tripmate.android.core.network.service.TripmateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    internal fun provideTripmateService(
        @TripmateApi retrofit: Retrofit,
    ): TripmateService {
        return retrofit.create(TripmateService::class.java)
    }
}
