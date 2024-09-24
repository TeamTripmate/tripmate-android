package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.MateRecruitPostEntity

interface MateRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
    suspend fun companionApply(companionId: Int, userId: Int): Result<Unit>
    suspend fun getCompanionsDetailInfo(companionId: Int): Result<MateRecruitPostEntity>
}
