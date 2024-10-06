package com.tripmate.android.feature.trip_detail.viewmodel

sealed interface TripDetailUiAction {
    data object OnBackClicked : TripDetailUiAction
    data class OnTabChanged(val index: Int) : TripDetailUiAction
    data object GetTripDetailInfo : TripDetailUiAction
    data object OnClickMateRecruit : TripDetailUiAction
    data class OnClickMateReviewPost(val companionId: Int) : TripDetailUiAction
    data object OnClickReport : TripDetailUiAction
}
