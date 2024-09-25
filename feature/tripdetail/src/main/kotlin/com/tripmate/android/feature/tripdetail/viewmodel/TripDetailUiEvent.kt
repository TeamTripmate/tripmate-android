package com.tripmate.android.feature.tripdetail.viewmodel

sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
    data object NavigateMateRecruit : TripDetailUiEvent
    data class NavigateToMateReviewPost(val companionId: Int) : TripDetailUiEvent
}
