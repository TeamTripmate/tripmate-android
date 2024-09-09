package com.tripmate.android.feature.mypage.viewmodel.mypage

sealed interface MyPageUiAction {
    data object OnBackClicked : MyPageUiAction
    data class OnTicketClicked(val characterId: Long) : MyPageUiAction
    data object OnMyPickClicked : MyPageUiAction
    data class OnTabChanged(val index: Int) : MyPageUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
