package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.MateRecruitmentEntity

interface MateRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
    suspend fun companionApply(companionId: Long): Result<Unit>
    suspend fun getCompanionsDetailInfo(companionId: Long): Result<MateRecruitPostEntity>
    suspend fun createCompanionRecruitment(mateRecruitmentEntity: MateRecruitmentEntity): Result<Unit>
}
