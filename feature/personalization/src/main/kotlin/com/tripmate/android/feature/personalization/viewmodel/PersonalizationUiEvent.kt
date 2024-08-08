package com.tripmate.android.feature.personalization.viewmodel

sealed interface PersonalizationUiEvent {
    data object NavigateToQ2 : PersonalizationUiEvent
    data object NavigateToQ3 : PersonalizationUiEvent
    data object NavigateToQ4 : PersonalizationUiEvent
    data object NavigateToTripStyle : PersonalizationUiEvent
    data object NavigateToUserInfo: PersonalizationUiEvent
    data object NavigateToResult : PersonalizationUiEvent
    data object NavigateToMain : PersonalizationUiEvent
}
