package com.tripmate.android.core.data.repository

interface PersonalizationRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
