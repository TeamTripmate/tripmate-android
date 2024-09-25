package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.triplist.CompanionEntity
import com.tripmate.android.domain.entity.triplist.CompanionInfoEntity

interface TripListRepository {
    suspend fun getTripsCreatedByUser(userId:String): Result<CompanionEntity>
    suspend fun getTripsParticipatedByUser(userId:String): Result<CompanionInfoEntity>
}
