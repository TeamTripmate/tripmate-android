package com.tripmate.android.core.data.repository

import com.tripmate.android.domain.repository.MapRepository
import javax.inject.Inject

internal class MapRepositoryImpl @Inject constructor() : MapRepository {

    override suspend fun getNearbyTouristSpots(): Boolean {
        TODO("Not yet implemented")
    }
}
