package com.tripmate.android.core.database.di

import com.tripmate.android.core.database.MyPickDao
import com.tripmate.android.core.database.MyPickDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideMyPickDao(
        database: MyPickDatabase,
    ): MyPickDao = database.myPickDao()
}
