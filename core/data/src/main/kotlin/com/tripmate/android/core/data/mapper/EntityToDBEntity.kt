package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.database.entity.MyPickDBEntity
import com.tripmate.android.domain.entity.MyPickEntity
import com.tripmate.android.domain.entity.SpotEntity

internal fun SpotEntity.toDBEntity(tapType: String) =
    MyPickDBEntity(
        id = id,
        title = title,
        description = description,
        spotType = spotType,
        thumbnailUrl = thumbnailUrl,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        address = address,
        isLiked = true,
        tapType = tapType,
    )

internal fun MyPickEntity.toDBEntity() =
    MyPickDBEntity(
        id = id,
        title = title,
        description = description,
        spotType = spotType,
        thumbnailUrl = thumbnailUrl,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        address = address,
        isLiked = isLiked,
        tapType = tapType,
    )
