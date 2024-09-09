package com.tripmate.android.feature.mypage.viewmodel.mypage

import com.tripmate.android.core.common.UiText

sealed interface MyPageUiEvent {
    data class NavigateToMyTripCharacterInfo(val characterId: Long) : MyPageUiEvent
    data object NavigateToMyPick: MyPageUiEvent
    data object Logout : MyPageUiEvent
    data object Withdraw : MyPageUiEvent
    data object NavigateBack : MyPageUiEvent
    data class ShowToast(val message: UiText) : MyPageUiEvent
}
