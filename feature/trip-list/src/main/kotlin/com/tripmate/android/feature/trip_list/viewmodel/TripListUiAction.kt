package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiAction {
    data object OnBackClicked : TripListUiAction
    data class OnTabChanged(val index: Int) : TripListUiAction
    data class OnTicketClicked(val ticketId: Int, val userId: Long) : TripListUiAction
    data class OnClickViewMateList(val companionId: Long, val page: Int) : TripListUiAction
    data class OnTripStatusCardClicked(val openChatLink: String, val selectedKeyword: List<String>, val tripStyle: String, val characterId: String, val companionId: Long) : TripListUiAction
    data class OnMateOpenChatClicked(val openKakaoChatLink: String) : TripListUiAction
    data class OnTripDetailClicked(val companionId: Long) : TripListUiAction
    data object OnSelectMateClicked : TripListUiAction
    data class OnReviewItemClicked(val companionId: Long, val title: String, val date: String) : TripListUiAction
}
