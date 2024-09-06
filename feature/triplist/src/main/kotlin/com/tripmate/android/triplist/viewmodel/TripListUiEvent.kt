package com.tripmate.android.triplist.viewmodel

sealed interface TripListUiEvent {
    data object NavigateBack : TripListUiEvent
}
