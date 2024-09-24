package com.tripmate.android.domain.entity

data class SpotEntity(
    var id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double,

    // 서버 요청 필요
    val isSearching: Boolean = false,
    val subCategory: String = "ALL",
    val address: String = "강원도 추천",
)
