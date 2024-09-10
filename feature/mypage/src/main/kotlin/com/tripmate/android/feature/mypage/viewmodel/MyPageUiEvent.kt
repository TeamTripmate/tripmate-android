package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface MyPageUiEvent {
    data object NavigateBack : MyPageUiEvent
    data class NavigateToMyTripCharacterInfo(val characterId: Long) : MyPageUiEvent
    data object NavigateToPersonalization : MyPageUiEvent
    data object NavigateToMyPick : MyPageUiEvent
    data object NavigateToLogin : MyPageUiEvent
    data object NavigateToWithdraw : MyPageUiEvent
    data object Withdraw : MyPageUiEvent
    data object NavigateToMain : MyPageUiEvent
    data class ShowToast(val message: UiText) : MyPageUiEvent
}
