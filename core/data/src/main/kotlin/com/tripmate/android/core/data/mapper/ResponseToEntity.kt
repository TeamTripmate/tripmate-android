package com.tripmate.android.core.data.mapper

import com.tripmate.android.core.network.response.ApplicantInfo
import com.tripmate.android.core.network.response.CreatedCompanionInfo
import com.tripmate.android.core.network.response.ParticipatedCompanionInfo
import com.tripmate.android.core.network.response.MyPageUserInfo
import com.tripmate.android.core.network.response.PersonalizedTestResult
import com.tripmate.android.core.network.response.TripHostInfo
import com.tripmate.android.domain.entity.MyPageUserInfoEntity
import com.tripmate.android.domain.entity.PersonalizedTestResultEntity
import com.tripmate.android.domain.entity.triplist.ApplicantInfoEntity
import com.tripmate.android.domain.entity.triplist.CreatedCompanionInfoEntity
import com.tripmate.android.domain.entity.triplist.ParticipatedCompanionInfoEntity
import com.tripmate.android.domain.entity.triplist.TripHostInfoEntity

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
        characterType = characterType,
    )

internal fun CreatedCompanionInfo.toEntity() =
    CreatedCompanionInfoEntity(
        companionId = companionId,
        title = title,
        date = date,
        companionStatus = companionStatus,
        applicantInfoEntityInfo = applicantInfo.toEntity(),
    )

internal fun ApplicantInfo.toEntity() =
    ApplicantInfoEntity(
        userId = userId,
        selectedKeyword = selectedKeyword,
        tripStyle = tripStyle,
        characterId = characterId,
    )

internal fun ParticipatedCompanionInfo.toEntity() =
    ParticipatedCompanionInfoEntity(
        companionId = companionId,
        title = title,
        date = date,
        openChatLink = openChatLink,
        reviewYn = reviewYn,
        matchingStatus = matchingStatus,
        tripHostInfoEntity = tripHostInfo.toEntity(),
    )

internal fun TripHostInfo.toEntity() =
    TripHostInfoEntity(
        selectedKeyword = selectedKeyword,
        tripStyle = tripStyle,
        characterId = characterId,
    )
