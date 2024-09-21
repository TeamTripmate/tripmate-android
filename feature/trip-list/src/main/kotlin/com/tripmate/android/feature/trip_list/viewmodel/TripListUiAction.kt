package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiAction {
    data object OnBackClicked : TripListUiAction
    data class OnTabChanged(val index: Int) : TripListUiAction
    data class OnTicketClicked(val ticketId: Int) : TripListUiAction
}
