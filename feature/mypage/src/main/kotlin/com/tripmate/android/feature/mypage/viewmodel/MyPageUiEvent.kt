package com.tripmate.android.feature.mypage.viewmodel

import android.graphics.Bitmap
import com.tripmate.android.core.common.UiText

sealed interface MyPageUiEvent {
    data object NavigateBack : MyPageUiEvent
    data class NavigateToMyTripCharacterInfo(val characterId: String, val tripStyle: String) : MyPageUiEvent
    data class ShareMyTripStyle(val image: Bitmap) : MyPageUiEvent
    data object NavigateToPersonalization : MyPageUiEvent
    data object NavigateToMyPick : MyPageUiEvent
    data object NavigateToLogin : MyPageUiEvent
    data object NavigateToWithdraw : MyPageUiEvent
    data object Withdraw : MyPageUiEvent
    data object NavigateToMain : MyPageUiEvent
    data object NavigateToPrivacyPolicy : MyPageUiEvent
    data object NavigateToTermOfUse : MyPageUiEvent
    data class ShowToast(val message: UiText) : MyPageUiEvent
}
