package com.tripmate.android.feature.trip_detail.viewmodel

sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
    data class NavigateMateRecruit(val spotId: String, val spotTitle: String, val spotAddress: String) : TripDetailUiEvent
    data class NavigateToMateReviewPost(val companionId: Int) : TripDetailUiEvent
    data object NavigateToReport : TripDetailUiEvent
}
