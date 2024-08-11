package com.tripmate.android.domain.repository

interface PersonalizationRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
