package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class POISimpleListEntity(
    val poiId: Int,
    val title: String,
    val subCategory: String,
    val address: String,
    val distance: Double,
    val description: String,
    val imageRes: Int,
    val lon: Double = 0.0,
    val lat: Double = 0.0,
    val isSearching: Boolean = false,
)
