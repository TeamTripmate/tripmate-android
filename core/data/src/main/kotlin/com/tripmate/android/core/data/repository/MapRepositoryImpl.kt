package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.CategoryEntity
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.domain.repository.MapRepository
import javax.inject.Inject
import java.util.Locale

internal class MapRepositoryImpl @Inject constructor(
    private val service: TripmateService,
) : MapRepository {

    override suspend fun getNearbyTouristSpots(
        searchType: String,
        latitude: String,
        longitude: String,
        range: String,
        spotTypeGroup: String,
        spotType: String,
    ): Result<List<SpotEntity>> = runSuspendCatching {
        service.getNearbyTouristSpots(
            searchType = searchType,
            latitude = latitude,
            longitude = longitude,
            range = range,
            spotTypeGroup = spotTypeGroup,
            spotType = spotType,
        ).data.spots.map { spot ->
            SpotEntity(
                id = spot.id,
                title = spot.title,
                description = spot.description,
                spotType = spot.spotType,
                category = CategoryEntity(
                    largeCategory = spot.category.largeCategory,
                    mediumCategory = spot.category.mediumCategory,
                    smallCategory = spot.category.smallCategory,
                ),
                thumbnailUrl = spot.thumbnailUrl.replace("http:", "https:"),
                latitude = spot.location.latitude.toDouble(),
                longitude = spot.location.longitude.toDouble(),
                distance = formatDistance(spot.distance.toDouble()),
                address = "${spot.location.address.address1} ${spot.location.address.address2}".trim(),
                companionYn = spot.companionYn,
                subCategory = spot.category.mediumCategory,
            )
        }
    }

    private fun formatDistance(distanceInMeters: Double): String {
        return if (distanceInMeters >= 1000) {
            String.format(Locale.KOREA, "%.1f km", distanceInMeters / 1000)
        } else {
            String.format(Locale.KOREA, "%.0f m", distanceInMeters)
        }
    }
}
