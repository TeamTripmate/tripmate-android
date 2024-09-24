package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.response.UserInfo
import com.tripmate.android.domain.entity.UserInfoEntity

internal fun UserInfo.toEntity() =
    UserInfoEntity(
        selectedKeyword = selectedKeyword,
        characterId = characterId,
        tripStyle = tripStyle,
    )
