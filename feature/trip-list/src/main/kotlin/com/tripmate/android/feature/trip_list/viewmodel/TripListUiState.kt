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
    val ticket: ImmutableList<TicketEntity> =
        persistentListOf(
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 0,
            ),
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 1,
            ),
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 2,
            ),
        ),
    val isTicketClicked: ImmutableList<Boolean> = persistentListOf(false, true, false),
)
