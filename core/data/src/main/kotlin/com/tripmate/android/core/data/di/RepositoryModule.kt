package com.tripmate.android.core.data.di

import com.tripmate.android.core.data.repository.NotificationRepositoryImpl
import com.tripmate.android.core.data.repository.MapRepositoryImpl
import com.tripmate.android.core.data.repository.MateRepositoryImpl
import com.tripmate.android.core.data.repository.MyPageRepositoryImpl
import com.tripmate.android.domain.repository.PersonalizationRepository
import com.tripmate.android.core.data.repository.PersonalizationRepositoryImpl
import com.tripmate.android.domain.repository.NotificationRepository
import com.tripmate.android.domain.repository.MapRepository
import com.tripmate.android.domain.repository.MateRepository
import com.tripmate.android.domain.repository.MyPageRepository
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
    abstract fun bindPersonalizationRepository(personalizationRepositoryImpl: PersonalizationRepositoryImpl): PersonalizationRepository

    @Binds
    @Singleton
    abstract fun bindMapRepository(mapRepositoryImpl: MapRepositoryImpl): MapRepository

    @Binds
    @Singleton
    abstract fun bindNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository

    @Binds
    @Singleton
    abstract fun bindMateRepository(mateRepositoryImpl: MateRepositoryImpl): MateRepository

    @Binds
    @Singleton
    abstract fun bindMyPageRepository(myPageRepositoryImpl: MyPageRepositoryImpl): MyPageRepository
}
