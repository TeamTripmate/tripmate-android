package com.tripmate.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tripmate.android.core.database.entity.MyPickDBEntity

@Database(
    entities = [MyPickDBEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class MyPickDatabase : RoomDatabase() {
    abstract fun myPickDao(): MyPickDao
}
