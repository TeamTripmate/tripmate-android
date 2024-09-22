package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiEvent {
    data object NavigateBack : TripListUiEvent
    data object NavigateToMateList : TripListUiEvent
    data object NavigateToMateOpenChat : TripListUiEvent
    data class NavigateToKakaoOpenChat(val openChatUrl: String) : TripListUiEvent
}
