package com.tripmate.android.feature.mypage.viewmodel

sealed interface MyPageUiAction {
    data object OnBackClicked : MyPageUiAction
    data class OnMyPageTitleUpdated(val title: String) : MyPageUiAction
    data object OnDoneClicked : MyPageUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
