package com.tripmate.android.core.datastore

interface OnboardingDataSource {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
