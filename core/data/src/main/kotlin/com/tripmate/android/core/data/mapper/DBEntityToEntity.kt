package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.database.entity.Category
import com.tripmate.android.core.database.entity.MyPickEntity
import com.tripmate.android.domain.entity.CategoryEntity
import com.tripmate.android.domain.entity.SpotEntity

internal fun MyPickEntity.toEntity() =
    SpotEntity(
        id = id,
        title = title,
        description = description,
        spotType = spotType,
        category = category.toEntity(),
        thumbnailUrl = thumbnailUrl,
        latitude = latitude,
        longitude = longitude,
        distance = distance,
        address = address,
        companionYn = companionYn,
        subCategory = subCategory,
    )

internal fun Category.toEntity() =
    CategoryEntity(
        largeCategory = largeCategory,
        mediumCategory = mediumCategory,
        smallCategory = smallCategory,
    )
