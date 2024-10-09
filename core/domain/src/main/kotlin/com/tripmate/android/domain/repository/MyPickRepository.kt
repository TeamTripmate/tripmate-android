package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.SpotEntity
import kotlinx.coroutines.flow.Flow

interface MyPickRepository {
    fun getMyPickList(): Flow<List<SpotEntity>>
    suspend fun registerMyPick(spot: SpotEntity)
    suspend fun unregisterMyPick(spot: SpotEntity)
}
