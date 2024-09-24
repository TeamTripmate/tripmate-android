package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.response.MyPageUserInfo
import com.tripmate.android.domain.entity.MyPageUserInfoEntity

internal fun MyPageUserInfo.toEntity() =
    MyPageUserInfoEntity(
        selectedKeyword = selectedKeyword,
        characterId = characterId,
        tripStyle = tripStyle,
    )
