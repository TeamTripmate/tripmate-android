package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiEvent {
    data object NavigateBack : TripListUiEvent
}
