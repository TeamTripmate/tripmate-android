package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.domain.entity.MyPickEntity
import com.tripmate.android.domain.entity.WithdrawReasonEntity
import com.tripmate.android.feature.mypage.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class MyPageUiState(
    val isLoading: Boolean = false,
    val profileImgUrl: String = "",
    val nickname: String = "",
    val myPickList: ImmutableList<MyPickEntity> = persistentListOf(),
    val myPickActivityList: ImmutableList<MyPickEntity> = persistentListOf(),
    val myPickHealingList: ImmutableList<MyPickEntity> = persistentListOf(),
    val characterId: String = "",
    val tripStyle: String = "",
    val type1: String = "",
    val type2: String = "",
    val type3: String = "",
    val characterTypeIntro: String = "",
    val tripStyleIntro: String = "",
    val tabs: ImmutableList<String> = persistentListOf(),
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
