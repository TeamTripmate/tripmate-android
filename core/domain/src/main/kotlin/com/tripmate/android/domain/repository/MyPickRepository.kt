package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.MyPickEntity
import com.tripmate.android.domain.entity.SpotEntity
import kotlinx.coroutines.flow.Flow

interface MyPickRepository {
    fun getMyPickList(): Flow<List<MyPickEntity>>
    suspend fun registerMyPick(spot: SpotEntity, tapType: String)
    suspend fun unregisterMyPick(spot: MyPickEntity)
}
