package com.tripmate.android.feature.map.viewmodel

import android.location.Location
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.POISimpleListEntity


data class MapUiState(
    val categoryType: CategoryType = CategoryType.Searaching,
    val isShowingList: Boolean = false,
    val simpleList: List<POISimpleListEntity> = emptyList(),
) {
    fun getTestList (): List<POISimpleListEntity> {
        // Todo 서버 작업 완료 후 제거
        return when (categoryType) {
            CategoryType.Searaching -> {
                listOf(
                    POISimpleListEntity("Searching Title 1", "강원도 춘천시", "This is the description for item 1", R.drawable.img_camera_with_flash, lat = 37.517235, lon = 127.047325),
                    POISimpleListEntity("Searching Title 2", "강원도 춘천시", "This is the description for item 2", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.2),
                    POISimpleListEntity("Searching Title 3", "강원도 춘천시", "This is the description for item 3", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.3),
                )
            }

            else -> {
                listOf(
                    POISimpleListEntity("Title 1", "강원도 춘천시", "This is the description for item 1", R.drawable.img_camera_with_flash, lat = 37.5, lon = 127.0),
                    POISimpleListEntity("Title 2", "강원도 춘천시", "This is the description for item 2", R.drawable.img_camera_with_flash, lat = 37.6, lon = 127.0),
                    POISimpleListEntity("Title 3", "강원도 춘천시", "This is the description for item 3", R.drawable.img_camera_with_flash, lat = 37.7, lon = 127.0),
                )
            }
        }
    }
}

