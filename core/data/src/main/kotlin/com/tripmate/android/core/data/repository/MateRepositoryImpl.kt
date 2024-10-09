package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toRequest
import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.network.request.CompanionApplyRequest
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.MateRecruitmentEntity
import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.MateReviewType
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity
import com.tripmate.android.domain.entity.UserInfoEntity
import com.tripmate.android.domain.repository.MateRepository
import javax.inject.Inject

internal class MateRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
    private val service: TripmateService,
    private val tokenDataSource: TokenDataSource,
) : MateRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }

    override suspend fun getCompanionsDetailInfo(companionId: Long): Result<MateRecruitPostEntity> = runSuspendCatching {
        val response = service.getCompanionsDetailInfo(companionId).data

        MateRecruitPostEntity(
            spotId = response.spotId,
            title = response.title,
            date = response.date,
            chatLink = response.chatLink,
            accompanyYn = response.accompanyYn,
            description = response.description,
            gender = response.gender,
            ageRange = response.ageRange,
            matchingRatio = response.hostInfo.matchingRatio.toString(),
            hostInfo = UserInfoEntity(
                profileImage = response.hostInfo.profileImage.replace("http:", "https:"),
                kakaoNickname = response.hostInfo.kakaoNickname,
                characterName = response.hostInfo.characterName,
                styleType = response.hostInfo.selectedKeyword,
            ),
            reviewRanks = response.reviewRanks.map { reviewRanks ->
                MateReviewType.entries.find { it.code == reviewRanks }?.reviewText ?: ""
            },
            mateRecruitPostReviewList = arrayListOf<TripDetailMateReviewEntity>().apply {
                response.reviewInfos.forEach { reviewInfo ->
                    this.add(
                        TripDetailMateReviewEntity(
                            userInfo = UserInfoEntity(
                                profileImage = reviewInfo.userInfo.profileImage.replace("http:", "https:"),
                                kakaoNickname = reviewInfo.userInfo.kakaoNickname,
                                characterName = reviewInfo.userInfo.characterName,
                            ),
                            reviewDate = reviewInfo.reviewDate,
                            likeList = reviewInfo.likeList.map { reviewRanks ->
                                MateReviewType.entries.find { it.code == reviewRanks }?.reviewText ?: ""
                            },
                        ),
                    )
                }
            },
        )
    }

    override suspend fun companionApply(companionId: Long): Result<Unit> = runSuspendCatching {
        service.companionsApply(CompanionApplyRequest(companionId, tokenDataSource.getId()))
    }

    override suspend fun createCompanionRecruitment(mateRecruitmentEntity: MateRecruitmentEntity) = runSuspendCatching {
        service.createCompanionRecruitment(mateRecruitmentEntity.toRequest())
    }
}
