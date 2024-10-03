package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiEvent {
    data object NavigateBack : TripListUiEvent
    data class NavigateToMateList(val companionId: Long, val page: Int) : TripListUiEvent
    data object NavigateToMateOpenChat : TripListUiEvent
    data class NavigateToKakaoOpenChat(val openChatUrl: String) : TripListUiEvent
}
