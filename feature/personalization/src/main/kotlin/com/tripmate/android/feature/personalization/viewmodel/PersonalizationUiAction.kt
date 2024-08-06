package com.tripmate.android.feature.personalization.viewmodel

import com.tripmate.android.domain.entity.TripStyleEntity

sealed interface PersonalizationUiAction {
    data class OnTripStyleSelected(val tripStyle: TripStyleEntity) : PersonalizationUiAction
    data class OnTripStyleDeselected(val tripStyle: TripStyleEntity) : PersonalizationUiAction
    data object OnSelectClick : PersonalizationUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
