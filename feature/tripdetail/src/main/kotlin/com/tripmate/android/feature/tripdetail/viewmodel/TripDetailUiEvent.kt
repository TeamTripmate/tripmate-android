package com.tripmate.android.feature.tripdetail.viewmodel

sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
}
