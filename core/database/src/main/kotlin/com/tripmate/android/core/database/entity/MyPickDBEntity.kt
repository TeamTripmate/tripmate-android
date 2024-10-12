package com.tripmate.android.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "my_pick")
data class MyPickDBEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "spot_type")
    val spotType: String,
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
    @ColumnInfo(name = "is_liked")
    val isLiked: Boolean,
    @ColumnInfo(name = "tap_type")
    val tapType: String,
)
