package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.SpotEntity

interface MapRepository {
    suspend fun getNearbyTouristSpots(
        latitude: String,
        longitude: String,
        range: String,
        category: String,
    ): Result<List<SpotEntity>>
}
