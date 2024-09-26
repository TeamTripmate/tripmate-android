package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.MateRecruitmentEntity

interface MateRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
    suspend fun companionApply(companionId: Int): Result<Unit>
    suspend fun getCompanionsDetailInfo(companionId: Int): Result<MateRecruitPostEntity>
    suspend fun createCompanionRecruitment(mateRecruitmentEntity: MateRecruitmentEntity): Result<Unit>
}
