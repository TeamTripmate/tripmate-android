package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.network.request.LocationBasedSpotSearchRequest
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.domain.repository.MapRepository
import javax.inject.Inject

internal class MapRepositoryImpl @Inject constructor(
    private val service: TripmateService,
) : MapRepository {

    override suspend fun getNearbyTouristSpots(
        latitude: String,
        longitude: String,
        range: String,
        category: String,
    ): Result<List<SpotEntity>> = runSuspendCatching {
        service.getNearbyTouristSpots(
                latitude = latitude,
                longitude = longitude,
                range = range,
                category = category,
        ).data.spots.map {
            SpotEntity(
                id = it.id,
                title = it.title,
                description = it.description ?: "",
                thumbnailUrl = it.thumbnailUrl.replace("http:","https:"),
                latitude = it.latitude.toDouble(),
                longitude = it.longitude.toDouble(),
                distance = String.format("%.1f", it.distance.toDouble()).toDouble(),
            )
        }
    }
}
