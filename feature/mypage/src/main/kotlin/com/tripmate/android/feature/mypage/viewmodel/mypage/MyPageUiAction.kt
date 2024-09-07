package com.tripmate.android.feature.mypage.viewmodel.mypage

sealed interface MyPageUiAction {
    data object OnBackClicked : MyPageUiAction
    data class OnTicketClicked(val characterId: Long) : MyPageUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
