package com.tripmate.android.domain.repository

import com.tripmate.android.domain.entity.PersonalizedTestEntity
import com.tripmate.android.domain.entity.PersonalizedTestResultEntity

interface PersonalizationRepository {
    suspend fun checkPersonalizationCompletion(): Boolean
    suspend fun completePersonalization(flag: Boolean)
    suspend fun submitPersonalizedTest(personalizedTestEntity: PersonalizedTestEntity): Result<PersonalizedTestResultEntity>
}
