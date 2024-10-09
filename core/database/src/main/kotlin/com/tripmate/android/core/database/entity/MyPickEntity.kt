package com.tripmate.android.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

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

@Serializable
@Entity(tableName = "my_pick")
data class MyPickEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "spot_type")
    val spotType: String,
    @ColumnInfo(name = "category_type")
    val category: Category,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "distance")
    val distance: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "companion_yn")
    val companionYn: Boolean,
    @ColumnInfo(name = "sub_category")
    val subCategory: String,
    @ColumnInfo(name = "is_liked")
    val isLiked: Boolean,
)

@Serializable
data class Category(
    val largeCategory: String,
    val mediumCategory: String,
    val smallCategory: String,
)
