package com.tripmate.android.feature.trip_detail.viewmodel

sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
    data object NavigateMateRecruit : TripDetailUiEvent
    data class NavigateToMateReviewPost(val companionId: Int) : TripDetailUiEvent
    data object NavigateToReport : TripDetailUiEvent
}
