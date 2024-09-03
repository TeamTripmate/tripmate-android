package com.tripmate.android.feature.home.viewmodel

import com.tripmate.android.core.common.extension.formatToDate
import com.tripmate.android.core.common.extension.formatToTime
import com.tripmate.android.domain.entity.GenderAgeGroupEntity
import com.tripmate.android.feature.mate_recruit.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

data class HomeUiState(
    val tabs: ImmutableList<String> = persistentListOf("액티비티", "힐링"),
    val selectedTabIndex: Int = 0,
    val activitySelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val healingSelectedChips: ImmutableList<String> = persistentListOf("전체"),
)
