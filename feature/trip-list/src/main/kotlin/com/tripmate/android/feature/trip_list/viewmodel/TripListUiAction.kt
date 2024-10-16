package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiAction {
    data object OnBackClicked : TripListUiAction
    data class OnTabChanged(val index: Int) : TripListUiAction
    data class OnTicketClicked(val ticketId: Int, val userId: Long) : TripListUiAction
    data class OnClickViewMateList(val companionId: Long, val page: Int) : TripListUiAction
    data class OnTripStatusCardClicked(
        val openChatLink: String,
        val selectedKeyword: List<String>,
        val tripStyle: String,
        val characterId: String,
        val companionId: Long,
        val isMatched: Boolean,
    ) : TripListUiAction

    data class OnMateOpenChatClicked(val openKakaoChatLink: String) : TripListUiAction
    data class OnTripDetailClicked(val companionId: Long) : TripListUiAction
    data object OnSelectMateClicked : TripListUiAction
    data class OnCharacterDescriptionClicked(val characterId: String, val tag1: String, val tag2: String, val tag3: String) : TripListUiAction
}
