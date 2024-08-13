package com.tripmate.android.feature.personalization.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface PersonalizationUiEvent {
    data object NavigateToQuestion2 : PersonalizationUiEvent
    data object NavigateToQuestion3 : PersonalizationUiEvent
    data object NavigateToQuestion4 : PersonalizationUiEvent
    data object NavigateToTripStyle : PersonalizationUiEvent
    data object NavigateToUserInfo : PersonalizationUiEvent
    data object NavigateToResult : PersonalizationUiEvent
    data object NavigateToMain : PersonalizationUiEvent
    data class ShowToast(val message: UiText) : PersonalizationUiEvent
    data object Finish : PersonalizationUiEvent
}
