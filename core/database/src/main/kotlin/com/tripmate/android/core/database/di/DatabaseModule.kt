package com.tripmate.android.core.database.di

import android.content.Context
import androidx.room.Room
import com.tripmate.android.core.database.MyPickDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMyPickDatabase(@ApplicationContext context: Context): MyPickDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MyPickDatabase::class.java,
            "my_pick_database",
        ).build()
}
