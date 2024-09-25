package com.tripmate.trip_original.viewmodel

sealed interface TripOriginalUiEvent {
    data object NavigateBack : TripOriginalUiEvent
}
