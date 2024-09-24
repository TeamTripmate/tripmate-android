package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.mapper.toEntity
import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.PersonalizationDataSource
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.repository.MyPageRepository
import javax.inject.Inject

internal class MyPageRepositoryImpl @Inject constructor(
    private val tripmateService: TripmateService,
    private val personalizationDataSource: PersonalizationDataSource,
    private val tokenDataSource: TokenDataSource,
) : MyPageRepository {
    override suspend fun getUserInfo() = runSuspendCatching {
        val id = tokenDataSource.getId()
        tripmateService.getUserInfo(id).data.toEntity()
    }

    override suspend fun checkPersonalizationCompletion(): Boolean {
        return personalizationDataSource.checkPersonalizationCompletion()
    }

    override suspend fun completePersonalization(flag: Boolean) {
        personalizationDataSource.completePersonalization(flag)
    }
}
