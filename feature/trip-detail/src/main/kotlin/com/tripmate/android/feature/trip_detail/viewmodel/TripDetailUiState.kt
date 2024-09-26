package com.tripmate.android.feature.trip_detail.viewmodel

import com.kakao.vectormap.LatLng
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.feature.map.extension.cameraPosition
import com.tripmate.android.feature.map.model.MarkerInfo
import com.tripmate.android.feature.map.state.CameraPositionState
import com.tripmate.android.mate.viewmodel.CategoryType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class TripDetailUiState(
    val tabs: ImmutableList<String> = persistentListOf("상세정보", "동행모집"),
    val selectedTabIndex: Int = 0,
    val tripDetail: TripDetailEntity = TripDetailEntity(),
) {
    fun getCategoryTypeTips(): String {
        return CategoryType.entries.find { it.categoryCode == tripDetail.category }?.tips ?: ""
    }

    fun getMarkerInfo(): List<MarkerInfo> {
        return mutableListOf<MarkerInfo>().apply {
            add(
                MarkerInfo(
                    latitude = tripDetail.location.latitude.toDouble(),
                    longitude = tripDetail.location.longitude.toDouble(),
                    resourceId = R.drawable.ic_location_pin,
                ),
            )
        }
    }

    fun movePoiLocation(cameraPositionState: CameraPositionState) {
        val cameraPosition = cameraPosition {
            setPosition(
                LatLng.from(
                    tripDetail.location.latitude.toDouble(),
                    tripDetail.location.longitude.toDouble(),
                ),
            )
            setZoomLevel(12)
        }
        cameraPositionState.position = cameraPosition
    }

    fun getCharterImageResourceId(characterType: String): Int {
        return TripmateCharacterType.entries.find { characterType == it.characterType }?.resouceId ?: R.drawable.ic_penguin
    }
}

enum class TripmateCharacterType(val characterType: String, val resouceId: Int) {
    PENGUIN("PENGUIN", R.drawable.ic_penguin),
    HONEYBEE("HONEYBEE", R.drawable.ic_honeybee),
    ELEPHANT("ELEPHANT", R.drawable.ic_elephant),
    DOLPHIN("DOLPHIN", R.drawable.ic_dolphin),
    TURTLE("TURTLE", R.drawable.ic_turtle),
    PANDA("PANDA", R.drawable.ic_panda),
}
