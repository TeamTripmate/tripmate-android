package com.tripmate.android.feature.detailtrip.viewmodel

sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
}
