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

    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "distance")
    val distance: Double,
)

