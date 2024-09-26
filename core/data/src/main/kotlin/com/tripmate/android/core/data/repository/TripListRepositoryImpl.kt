package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toEntity
import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.network.request.MateSelectRequest
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.repository.TripListRepository
import javax.inject.Inject

internal class TripListRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val service: TripmateService,
) : TripListRepository {
    override suspend fun getCreatedTripListByUser() = runSuspendCatching {
        val userId = tokenDataSource.getId()
        service.getCreatedTripList(userId).data.companions.map { it.toEntity() }
    }

    override suspend fun getTripsParticipatedByUser() = runSuspendCatching {
        val userId = tokenDataSource.getId()
        service.getParticipatedTripList(userId).data.companions.map { it.toEntity() }
    }

    override suspend fun selectMate(userId: Long, companionId: Long) = runSuspendCatching {
        service.selectMate(MateSelectRequest(companionId, userId))
    }
}
