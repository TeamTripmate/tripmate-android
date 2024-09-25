package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toEntity
import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.network.request.CompanionApplyRequest
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.repository.TripListRepository
import javax.inject.Inject


internal class TripListRepositoryImpl @Inject constructor(
    private val service: TripmateService,
) : TripListRepository {
    override suspend fun getTripsCreatedByUser(userId: String) = runSuspendCatching {
        service.getTripsCreatedByUser(userId).data.toEntity()
    }

    override suspend fun getTripsParticipatedByUser(userId: String): Boolean {
        TODO("Not yet implemented")
    }

}
