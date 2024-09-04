package com.tripmate.android.feature.home.viewmodel

sealed interface HomeUiAction {
    data object OnBackClicked : HomeUiAction
    data class OnTabChanged(val index: Int) : HomeUiAction
    data class OnClickChip(val chipName: String) : HomeUiAction
}
