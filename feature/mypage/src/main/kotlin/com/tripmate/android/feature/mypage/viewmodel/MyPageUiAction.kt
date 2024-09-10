package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.domain.entity.WithdrawReasonEntity

sealed interface MyPageUiAction {
    data object OnBackClicked : MyPageUiAction
    data class OnTicketClicked(val characterId: Long) : MyPageUiAction
    data object OnCharacterTypeReselectClicked : MyPageUiAction
    data object OnMyPickClicked : MyPageUiAction
    data class OnTabChanged(val index: Int) : MyPageUiAction
    data class OnWithdrawReasonSelected(val withdrawReason: WithdrawReasonEntity) : MyPageUiAction
    data class OnWithdrawReasonDeselected(val withdrawReason: WithdrawReasonEntity) : MyPageUiAction
    data class OnWithdrawReasonDescriptionUpdated(val withdrawReasonDescription: String) : MyPageUiAction
    data object OnLogoutClicked : MyPageUiAction
    data object OnWithdrawClicked : MyPageUiAction
    data object OnRealWithdrawClicked : MyPageUiAction
    data object OnUseMoreClicked : MyPageUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
