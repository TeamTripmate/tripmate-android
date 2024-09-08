package com.tripmate.android.feature.detailtrip.viewmodel

sealed interface TripDetailUiAction {
    data class OnTabChanged(val index: Int) : TripDetailUiAction
}
