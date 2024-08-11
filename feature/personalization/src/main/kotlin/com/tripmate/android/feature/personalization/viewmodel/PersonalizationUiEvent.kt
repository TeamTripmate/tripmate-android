package com.tripmate.android.feature.personalization.viewmodel

sealed interface PersonalizationUiEvent {
    data object NavigateToQuestion2 : PersonalizationUiEvent
    data object NavigateToQuestion3 : PersonalizationUiEvent
    data object NavigateToQuestion4 : PersonalizationUiEvent
    data object NavigateToTripStyle : PersonalizationUiEvent
    data object NavigateToUserInfo: PersonalizationUiEvent
    data object NavigateToResult : PersonalizationUiEvent
    data object NavigateToMain : PersonalizationUiEvent
}
