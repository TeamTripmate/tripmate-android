package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.request.CompanionRecruitmentRequest
import com.tripmate.android.core.network.request.PersonalizedTestRequest
import com.tripmate.android.domain.entity.MateRecruitmentEntity
import com.tripmate.android.domain.entity.PersonalizedTestEntity

internal fun PersonalizedTestEntity.toRequest() =
    PersonalizedTestRequest(
        mbti = mbti,
        gender = gender,
        birthDate = birthDate,
        keywords = keywords,
    )

internal fun MateRecruitmentEntity.toRequest() =
    CompanionRecruitmentRequest(
        spotId = spotId,
        date = date,
        title = title,
        description = description,
        type = type,
        sameGenderYn = sameGenderYn,
        sameAgeYn = sameAgeYn,
        openChatLink = openChatLink,
        creatorId = creatorId,
    )
