package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.request.PersonalizedTestRequest
import com.tripmate.android.domain.entity.PersonalizedTestEntity

internal fun PersonalizedTestEntity.toRequest() =
    PersonalizedTestRequest(
        mbti = mbti,
        gender = gender,
        birthDate = birthDate,
        keywords = keywords,
    )
