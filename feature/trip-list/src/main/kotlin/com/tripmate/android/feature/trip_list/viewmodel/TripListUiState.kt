package com.tripmate.android.feature.trip_list.viewmodel

import com.tripmate.android.domain.entity.TicketEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class TripListUiState(
    val tabs: ImmutableList<String> = persistentListOf("신청한 동행", "작성한 동행"),
    val selectedTabIndex: Int = 0,
    val activitySelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val healingSelectedChips: ImmutableList<String> = persistentListOf("전체"),
    val tripList: ImmutableList<String> = persistentListOf(),
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
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 3,
            ),
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 4,
            ),
            TicketEntity(
                characterName = "이름",
                hashtag1 = "해시태그1",
                hashtag2 = "해시태그2",
                hashtag3 = "해시태그3",
                characterImgUrl = "https://picsum.photos/48",
                ticketId = 5,
            ),

        ),
    val isTicketClicked: ImmutableList<Boolean> = persistentListOf(false, false, false,false,false,false),
    val openChatUrl: String = "https://open.kakao.com/o/gObLOlQg",
    val characterName: String = "인스타 인생 맛집\n탐험러 펭귄",
    val characterTypeIntro: String = "펭귄은 내향적인 성향을 가지고 있고, 대부분의 시간을 집단 내에서 조용히 보내며, 개인적인 공간과 안정적인 환경을 선호해요.\n" +
        "\n 혼자보다는 집단과 함께 있는 것을 더 편안해하고, 사회적 상호작용보다 자신의 역할에 집중합니다. 매우 세부적으로 계획을 세우고 조직적인 행동을 하는 유형이에요",
    val tripStyleIntro: String = "펭귄은 여행을 떠나기 전에 철저한 계획을 세우는 것을 좋아해요. 여행의 주요 목적지와 일정, 활동을 미리 정해두고, 예상 가능한 상황에 대비해 준비를 철저히 할 때 안정감을 느끼며 편안하게 여행을 즐긴답니다.\n펭귄은 집단 내에서 협력하여 활동하는 것을 좋아하는데요. 여행 중에도 동행자와 함께 계획을 공유하고, 서로의 역할을 명확히 하여 협력적으로 움직이는 것을 선호하죠.",
)
