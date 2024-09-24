package com.tripmate.android.domain.entity

data class SpotEntity(
    val id: Int,
    val title: String,
    val description: String,
    val spotType: String,
    val category: CategoryEntity,
    val thumbnailUrl: String,
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val address: String,
    val companionYn: Boolean,
    val isSearching: Boolean = false,
    val subCategory: String = "ALL"
)

data class CategoryEntity(
    val largeCategory: String,
    val mediumCategory: String,
    val smallCategory: String
)
