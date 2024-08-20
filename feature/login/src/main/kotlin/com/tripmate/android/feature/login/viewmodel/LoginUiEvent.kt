package com.tripmate.android.feature.login.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface LoginUiEvent {
    data object KakaoLogin : LoginUiEvent
    data class ShowToast(val message: UiText) : LoginUiEvent
    data object NavigateToMain : LoginUiEvent
    data object NavigateToPersonalization : LoginUiEvent
}
