package com.tripmate.android.feature.trip_list.viewmodel

import com.tripmate.android.domain.entity.TicketEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class TripListUiState(
    val tabs: ImmutableList<String> = persistentListOf("내가 신청한", "내가 모으는"),
    val selectedTabIndex: Int = 0,
    val activitySelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val healingSelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val tripList : ImmutableList<String> = persistentListOf(),
    val characterImgUrl: String = "https://picsum.photos/48",
    val ticket: ImmutableList<TicketEntity> = persistentListOf(),
    val isTicketClicked: ImmutableList<Boolean> = persistentListOf(),
)
