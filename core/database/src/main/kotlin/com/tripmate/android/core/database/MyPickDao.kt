package com.unifest.android.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tripmate.android.core.database.entity.MyPickEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPickDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyPick(userInfo: MyPickEntity)

    @Delete
    suspend fun deleteMyPick(userInfo: MyPickEntity)

    @Query("SELECT * FROM my_pick")
    fun getMyPickList(): Flow<List<MyPickEntity>>
}
