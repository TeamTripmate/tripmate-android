package com.tripmate.android.feature.trip_original.viewmodel

sealed interface TripOriginalUiAction {
    data object OnBackClicked : TripOriginalUiAction
    data object OnMateClicked : TripOriginalUiAction
    data object OnMateClosed : TripOriginalUiAction
}
