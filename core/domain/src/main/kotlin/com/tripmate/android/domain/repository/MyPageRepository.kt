package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.UserInfoEntity

interface MyPageRepository {
    suspend fun getUserInfo(): Result<UserInfoEntity>
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
