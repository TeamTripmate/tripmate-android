package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class POISimpleListEntity(
    val title: String,
    val address: String,
    val description: String,
    val imageRes: Int,
    val lon: Double = 0.0,
    val lat: Double = 0.0,
    val isSearching: Boolean = false)
