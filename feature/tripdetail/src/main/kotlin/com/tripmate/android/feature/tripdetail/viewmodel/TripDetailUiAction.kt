package com.tripmate.android.feature.tripdetail.viewmodel

sealed interface TripDetailUiAction {
    data class OnTabChanged(val index: Int) : TripDetailUiAction
    data object GetTripDetailInfo : TripDetailUiAction
    data object OnClickMateRecruit : TripDetailUiAction
    data class OnClickMateReviewPost(val companionId: Int) : TripDetailUiAction
}
