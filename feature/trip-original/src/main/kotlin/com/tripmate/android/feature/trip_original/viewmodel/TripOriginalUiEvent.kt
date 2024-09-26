package com.tripmate.android.feature.trip_original.viewmodel

sealed interface TripOriginalUiEvent {
    data object NavigateBack : TripOriginalUiEvent
}
