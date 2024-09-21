package com.tripmate.android.mate.viewmodel

sealed interface MateUiEvent {
    data object ClickCurrentLocation : MateUiEvent
    data object NavigateToTripDetail : MateUiEvent
}
