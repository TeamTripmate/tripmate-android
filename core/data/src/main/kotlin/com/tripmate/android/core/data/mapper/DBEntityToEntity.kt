package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.database.entity.MyPickDBEntity
import com.tripmate.android.domain.entity.MyPickEntity

internal fun MyPickDBEntity.toEntity() =
    MyPickEntity(
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
