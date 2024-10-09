package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.domain.entity.MyPickEntity
import com.tripmate.android.domain.entity.WithdrawReasonEntity
import com.tripmate.android.feature.mypage.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class MyPageUiState(
    val profileImgUrl: String = "https://picsum.photos/72",
    val nickname: String = "나트립",
    val myPickList: ImmutableList<MyPickEntity> = persistentListOf(),
    val myPickActivityList: ImmutableList<MyPickEntity> = persistentListOf(),
    val myPickHealingList: ImmutableList<MyPickEntity> = persistentListOf(),
    val characterId: String = "PENGUIN",
    val tripStyle: String = "여행가 아기 펭귄",
    val type1: String = "안생사진",
    val type2: String = "맛집방문",
    val type3: String = "인스타",
    val characterTypeIntro: String = "펭귄은 내향적인 성향을 가지고 있고, 대부분의 시간을 집단 내에서 조용히 보내며, 개인적인 공간과 안정적인 환경을 선호해요.\n" +
        "\n 혼자보다는 집단과 함께 있는 것을 더 편안해하고, 사회적 상호작용보다 자신의 역할에 집중합니다. 매우 세부적으로 계획을 세우고 조직적인 행동을 하는 유형이에요",
    val tripStyleIntro: String = "펭귄은 여행을 떠나기 전에 철저한 계획을 세우는 것을 좋아해요. 여행의 주요 목적지와 일정, 활동을 미리 정해두고, 예상 가능한 상황에 대비해 준비를 철저히 할 때 안정감을 느끼며 편안하게 여행을 즐긴답니다.\n펭귄은 집단 내에서 협력하여 활동하는 것을 좋아하는데요. 여행 중에도 동행자와 함께 계획을 공유하고, 서로의 역할을 명확히 하여 협력적으로 움직이는 것을 선호하죠.",
    val tabs: ImmutableList<String> = persistentListOf("액티비티", "힐링"),
    val selectedTabIndex: Int = 0,
    val allWithdrawReasons: ImmutableList<WithdrawReasonEntity> = persistentListOf(
        WithdrawReasonEntity(0, R.string.no_use, false),
        WithdrawReasonEntity(1, R.string.little_benefit, false),
        WithdrawReasonEntity(2, R.string.no_feature, false),
        WithdrawReasonEntity(3, R.string.uncomfortable_trip_mate, false),
        WithdrawReasonEntity(4, R.string.etc, false),
    ),
    val selectedWithdrawReasons: PersistentList<WithdrawReasonEntity> = persistentListOf(),
    val withdrawReasonDescription: String = "",
    val isWithdrawDialogVisible: Boolean = false,
    val isMyTripStyleShared: Boolean = false,
)
