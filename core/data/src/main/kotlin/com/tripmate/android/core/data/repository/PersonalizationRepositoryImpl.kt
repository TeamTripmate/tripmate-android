package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toEntity
import com.tripmate.android.core.data.mapper.toRequest
import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.PersonalizedTestEntity
import com.tripmate.android.domain.entity.PersonalizedTestResultEntity
import com.tripmate.android.domain.repository.PersonalizationRepository
import javax.inject.Inject

internal class PersonalizationRepositoryImpl @Inject constructor(
    private val personalizationDataSource: PersonalizationDataSource,
    private val tokenDataSource: TokenDataSource,
    private val tripmateService: TripmateService,
) : PersonalizationRepository {
    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }

    override suspend fun submitPersonalizedTest(personalizedTestEntity: PersonalizedTestEntity): Result<PersonalizedTestResultEntity> = runSuspendCatching {
        val userId = tokenDataSource.getId()
        tripmateService.submitPersonalizedTest(userId, personalizedTestEntity.toRequest()).data.toEntity()
    }
}
