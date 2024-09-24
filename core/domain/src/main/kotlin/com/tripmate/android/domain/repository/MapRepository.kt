package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.SpotEntity

interface MapRepository {
    suspend fun getNearbyTouristSpots(
        searchType: String,
        latitude: String,
        longitude: String,
        range: String,
        spotTypeGroup: String,
        spotType: String,
    ): Result<List<SpotEntity>>
}
