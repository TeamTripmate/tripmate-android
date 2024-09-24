package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.feature.map.model.MarkerInfo

data class MateUiState(
    val categoryType: CategoryType = CategoryType.All,
    val isShowingList: Boolean = false,
    val spotList: List<SpotEntity> = emptyList(),
    val selectPoiItem: SpotEntity? = null,
) {
    fun getMarkerInfoList(): List<MarkerInfo> {
        return mutableListOf<MarkerInfo>().apply {
            spotList.forEach {
                val isSelect = it == selectPoiItem
                add(
                    MarkerInfo(
                        latitude = it.latitude,
                        longitude = it.longitude,
                        resourceId = if (isSelect)
                            if (it.isSearching) categoryType.mateSelectMarkerIcon else categoryType.selectMarkerIcon
                        else {
                            if (it.isSearching) categoryType.mateUnselectMarkerIcon else categoryType.unselectMarkerIcon
                        } ?: R.drawable.img_unselect_default_marker,
                        poiId = it.id,
                    ),
                )
            }
        }
    }
}
