package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.OnboardingDataSource
import javax.inject.Inject

internal class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource,
) : OnboardingRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return onboardingDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        onboardingDataSource.completePersonalization(flag)
    }
}
