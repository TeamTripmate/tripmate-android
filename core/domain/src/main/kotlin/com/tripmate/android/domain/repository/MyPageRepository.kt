package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.MyPageUserInfoEntity

interface MyPageRepository {
    suspend fun getUserInfo(): Result<MyPageUserInfoEntity>
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
