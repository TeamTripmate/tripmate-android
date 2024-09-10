package com.tripmate.android.core.data.repository

import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.repository.TripDetailRepository
import javax.inject.Inject

internal class TripDetailRepositoryImpl @Inject constructor() : TripDetailRepository {

    override suspend fun getTripDetail(): TripDetailEntity {
        TODO("Not yet implemented")
    }
}
