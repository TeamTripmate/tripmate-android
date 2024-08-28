package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.core.designsystem.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class MateRecruitUiState(
    val selectedTripStyles: PersistentList<TripStyleEntity> = persistentListOf(),
    val selectedGender: Gender = Gender.NOT_SPECIFIED,
    val birthDate: String = "",
    val birthDateErrorText: UiText? = null,
)

enum class Gender {
    MALE, FEMALE, NOT_SPECIFIED
}
