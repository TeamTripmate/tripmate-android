package com.tripmate.android.feature.home.viewmodel

sealed interface HomeUiEvent {
    data object NavigateBack : HomeUiEvent
}
