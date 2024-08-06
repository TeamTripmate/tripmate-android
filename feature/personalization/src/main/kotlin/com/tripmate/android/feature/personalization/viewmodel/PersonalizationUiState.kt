package com.tripmate.android.feature.personalization.viewmodel

import com.tripmate.android.domain.entity.TripStyleEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class PersonalizationUiState(
    val allTripStyles: ImmutableList<TripStyleEntity> = persistentListOf(),
    val selectedTripStyles: PersistentList<TripStyleEntity> = persistentListOf(),
)
