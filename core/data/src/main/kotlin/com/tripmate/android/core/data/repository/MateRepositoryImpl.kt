package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.domain.repository.MateRepository
import javax.inject.Inject

internal class MateRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
) : MateRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }
}
