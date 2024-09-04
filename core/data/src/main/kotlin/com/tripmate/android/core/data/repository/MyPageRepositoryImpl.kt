package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.domain.repository.MyPageRepository
import javax.inject.Inject

internal class MyPageRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
) : MyPageRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }
}
