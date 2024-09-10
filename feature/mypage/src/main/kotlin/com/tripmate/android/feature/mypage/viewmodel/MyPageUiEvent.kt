package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface MyPageUiEvent {
    data object Logout : MyPageUiEvent
    data object Withdraw : MyPageUiEvent
    data class ShowToast(val message: UiText) : MyPageUiEvent
}
