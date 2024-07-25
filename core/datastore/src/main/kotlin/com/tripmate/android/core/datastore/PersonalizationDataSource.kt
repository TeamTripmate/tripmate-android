package com.tripmate.android.core.datastore

interface PersonalizationDataSource {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
}
