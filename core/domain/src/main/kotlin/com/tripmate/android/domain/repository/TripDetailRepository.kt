package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.TripDetailEntity

interface TripDetailRepository {
    suspend fun getTripDetail(spotId: String): Result<TripDetailEntity>
}
