package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.triplist.CreatedCompanionInfoEntity
import com.tripmate.android.domain.entity.triplist.ParticipatedCompanionInfoEntity

interface TripListRepository {
    suspend fun getCreatedTripListByUser(): Result<List<CreatedCompanionInfoEntity>>
    suspend fun getTripsParticipatedByUser(): Result<List<ParticipatedCompanionInfoEntity>>
    suspend fun selectMate(userId: Long, companionId: Long): Result<Unit>
}
