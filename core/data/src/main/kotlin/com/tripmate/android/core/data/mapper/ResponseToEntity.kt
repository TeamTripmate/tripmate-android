package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.response.MyPageUserInfo
import com.tripmate.android.core.network.response.PersonalizedTestResult
import com.tripmate.android.domain.entity.MyPageUserInfoEntity
import com.tripmate.android.domain.entity.PersonalizedTestResultEntity

internal fun MyPageUserInfo.toEntity() =
    MyPageUserInfoEntity(
        selectedKeyword = selectedKeyword,
        characterId = characterId,
        tripStyle = tripStyle,
        nickname = nickname,
        thumbnailImageUrl = thumbnailImageUrl,
        profileImageUrl = profileImageUrl,
    )

internal fun PersonalizedTestResult.toEntity() =
    PersonalizedTestResultEntity(
        characterType = characterType
    )
