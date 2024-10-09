package com.tripmate.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tripmate.android.core.database.entity.MyPickEntity

@Database(
    entities = [MyPickEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(CategoryConverter::class)
abstract class MyPickDatabase : RoomDatabase() {
    abstract fun myPickDao(): MyPickDao
}
