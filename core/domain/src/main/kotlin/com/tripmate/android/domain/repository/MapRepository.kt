package com.tripmate.android.domain.repository

interface MapRepository {
    suspend fun getNearbyTouristSpots(): Boolean
}
