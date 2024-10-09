package com.tripmate.android.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tripmate.android.core.database.entity.MyPickDBEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPickDao {
    @Query("SELECT * FROM my_pick")
    fun getMyPickList(): Flow<List<MyPickDBEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyPick(userInfo: MyPickDBEntity)

    @Delete
    suspend fun deleteMyPick(userInfo: MyPickDBEntity)
}
