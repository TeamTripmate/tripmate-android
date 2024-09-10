package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.domain.repository.PersonalizationRepository
import javax.inject.Inject

internal class PersonalizationRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
) : PersonalizationRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }
}
