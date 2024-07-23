package com.tripmate.android.core.data.repository

interface OnboardingRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
