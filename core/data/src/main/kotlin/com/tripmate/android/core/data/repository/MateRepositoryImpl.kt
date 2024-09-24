package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.network.request.CompanionApplyRequest
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity
import com.tripmate.android.domain.entity.UserInfoEntity
import com.tripmate.android.domain.repository.MateRepository
import javax.inject.Inject

internal class MateRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
    private val service: TripmateService,
) : MateRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }

    override suspend fun getCompanionsDetailInfo(companionId: Int): Result<MateRecruitPostEntity> = runSuspendCatching {
        val response = service.getCompanionsDetailInfo(companionId)

        MateRecruitPostEntity(
            spotId = response.spotId,
            title = response.title,
            date = response.date,
            chatLink = response.chatLink,
            accompanyYn = response.accompanyYn,
            description = response.description,
            gender = response.gender,
            ageRange = response.ageRange,
            matchingRatio = response.matchingRatio,
            hostInfo = UserInfoEntity(
                profileImage = response.hostInfo.profileImage,
                kakaoNickname = response.hostInfo.kakaoNickname,
                characterName = response.hostInfo.characterName,
                styleType = response.hostInfo.styleType,
            ),
            reviewRanks = response.reviewRanks,
            mateRecruitPostReviewList = emptyList<TripDetailMateReviewEntity>().apply {
                response.reviewInfos.forEach { reviewInfo ->
                    TripDetailMateReviewEntity(
                        userInfo = UserInfoEntity(
                            profileImage = reviewInfo.userInfo.profileImage,
                            kakaoNickname = reviewInfo.userInfo.kakaoNickname,
                            characterName = reviewInfo.userInfo.characterName,
                        ),
                        reviewDate = reviewInfo.reviewDate,
                        likeList = reviewInfo.likeList,
                    )
                }
            },
        )
    }

    override suspend fun companionApply(companionId: Int, userId: Int): Result<Unit> = runSuspendCatching {
        service.companionsApply(CompanionApplyRequest(companionId, userId))
    }
}
