package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.database.entity.Category
import com.tripmate.android.core.database.entity.MyPickEntity
import com.tripmate.android.domain.entity.CategoryEntity
import com.tripmate.android.domain.entity.SpotEntity

internal fun SpotEntity.toDBEntity() =
    MyPickEntity(
        id = id,
        title = title,
        description = description,
        spotType = spotType,
        category = category.toDBEntity(),
        thumbnailUrl = thumbnailUrl,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        address = address,
        companionYn = companionYn,
        subCategory = subCategory,
        isLiked = true,
    )

internal fun CategoryEntity.toDBEntity() =
    Category(
        largeCategory = largeCategory,
        mediumCategory = mediumCategory,
        smallCategory = smallCategory,
    )
