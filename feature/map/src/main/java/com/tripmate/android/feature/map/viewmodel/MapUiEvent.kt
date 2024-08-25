package com.tripmate.android.feature.map.viewmodel

sealed interface MapUiEvent {
    data object ClickCurrentLocation : MapUiEvent
}
