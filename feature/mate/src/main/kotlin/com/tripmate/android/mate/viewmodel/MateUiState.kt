package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.feature.map.model.MarkerInfo

data class MateUiState(
    val isLoading: Boolean = false,
    val categoryType: CategoryType = CategoryType.None,
    val isShowingList: Boolean = false,
    val spotList: List<SpotEntity> = emptyList(),
    val selectPoiItem: SpotEntity? = null,
    val isShowRecruitList: Boolean = false,
) {
    fun getShowingSpotList(): List<SpotEntity> {
        return if (isShowRecruitList) spotList.filter { it.companionYn == isShowRecruitList } else spotList
    }

    fun getMarkerInfoList(): List<MarkerInfo> {
        return mutableListOf<MarkerInfo>().apply {
            spotList.forEach { spotItem ->
                val categoryType = CategoryType.entries.find { it.categoryCode == spotItem.spotType } ?: CategoryType.None
                val isSelect = spotItem == selectPoiItem
                add(
                    MarkerInfo(
                        latitude = spotItem.latitude,
                        longitude = spotItem.longitude,
                        resourceId = if (isSelect)
                            if (spotItem.companionYn) categoryType.mateSelectMarkerIcon else categoryType.selectMarkerIcon
                        else {
                            if (spotItem.companionYn) categoryType.mateUnselectMarkerIcon else categoryType.unselectMarkerIcon
                        } ?: R.drawable.img_unselect_default_marker,
                        poiId = spotItem.id,
                    ),
                )
            }
        }
    }
}
