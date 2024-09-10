package com.tripmate.android.feature.map.model

import com.kakao.vectormap.LatLng

data class MarkerInfo(
    val poiId: Int = 0,
    val name: String = "",
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val resourceId: Int = 0,
    val isSelected: Boolean = false,
) {
    fun getLatLng(): LatLng {
        return LatLng.from(latitude, longitude)
    }
}
