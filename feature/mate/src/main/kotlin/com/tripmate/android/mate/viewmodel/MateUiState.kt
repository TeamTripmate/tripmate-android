package com.tripmate.android.mate.viewmodel

import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.POISimpleListEntity
import com.tripmate.android.feature.map.model.MarkerInfo

data class MateUiState(
    val categoryType: CategoryType = CategoryType.All,
    val isShowingList: Boolean = false,
    val simpleList: List<POISimpleListEntity> = emptyList(),
    val selectPoiItem: POISimpleListEntity? = null,
) {
    fun getTestList(categoryType: CategoryType): List<POISimpleListEntity> {
        // Todo 서버 작업 완료 후 제거
        return when (categoryType) {
            CategoryType.All -> {
                listOf(
                    POISimpleListEntity(
                        poiId = 1,
                        "Searching Title 1",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 1",
                        R.drawable.img_camera_with_flash,
                        lat = 37.517235,
                        lon = 127.047325,
                        isSearching = true,
                    ),
                    POISimpleListEntity(
                        poiId = 2,
                        "Searching Title 2",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 2",
                        R.drawable.img_camera_with_flash,
                        lat = 37.5,
                        lon = 127.2,
                    ),
                    POISimpleListEntity(
                        poiId = 3,
                        "Searching Title 3",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 3",
                        R.drawable.img_camera_with_flash,
                        lat = 37.5,
                        lon = 127.3,
                        isSearching = true,
                    ),
                )
            }

            else -> {
                listOf(
                    POISimpleListEntity(
                        poiId = 4,
                        "Title 1",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 1",
                        R.drawable.img_camera_with_flash,
                        lat = 37.5,
                        lon = 127.0,
                        isSearching = true,
                    ),
                    POISimpleListEntity(
                        poiId = 5,
                        "Title 2",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 2",
                        R.drawable.img_camera_with_flash,
                        lat = 37.6,
                        lon = 127.0,
                    ),
                    POISimpleListEntity(
                        poiId = 6,
                        "Title 3",
                        "체험",
                        "강원도 춘천시",
                        11.11,
                        "This is the description for item 3",
                        R.drawable.img_camera_with_flash,
                        lat = 37.7,
                        lon = 127.0,
                        isSearching = true,
                    ),
                )
            }
        }
    }

    fun getMarkerInfoList(): List<MarkerInfo> {
        return mutableListOf<MarkerInfo>().apply {
            simpleList.forEach {
                val isSelect = it == selectPoiItem
                add(
                    MarkerInfo(
                        latitude = it.lat,
                        longitude = it.lon,
                        resourceId = if (isSelect)
                            if (it.isSearching) categoryType.mateSelectMarkerIcon else categoryType.selectMarkerIcon
                        else {
                            if (it.isSearching) categoryType.mateUnselectMarkerIcon else categoryType.unselectMarkerIcon
                        } ?: R.drawable.img_unselect_default_marker,
                        poiId = it.poiId,
                    ),
                )
            }
        }
    }
}
