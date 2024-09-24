package com.tripmate.android.feature.tripdetail.viewmodel

sealed interface TripDetailUiAction {
    data class OnTabChanged(val index: Int) : TripDetailUiAction
}
