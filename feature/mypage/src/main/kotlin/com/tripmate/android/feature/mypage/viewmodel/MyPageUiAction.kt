package com.tripmate.android.feature.mypage.viewmodel

import com.tripmate.android.domain.entity.GenderAgeGroupEntity

sealed interface MyPageUiAction {
    data object OnBackClicked : MyPageUiAction
    data class OnMyPageTitleUpdated(val title: String) : MyPageUiAction
    data object OnDoneClicked : MyPageUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
