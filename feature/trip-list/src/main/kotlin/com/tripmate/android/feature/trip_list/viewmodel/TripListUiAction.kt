package com.tripmate.android.feature.trip_list.viewmodel

import com.tripmate.android.domain.entity.triplist.ApplicantInfoEntity

sealed interface TripListUiAction {
    data object OnBackClicked : TripListUiAction
    data class OnTabChanged(val index: Int) : TripListUiAction
    data class OnTicketClicked(val ticketId: Int, val userId: Long) : TripListUiAction
    data class OnClickViewMateList(val companionId: Long, val page: Int) : TripListUiAction
    data class OnTripStatusCardClicked(val openChatLink: String, val selectedKeyword: List<String>,val tripStyle:String, val characterId: String) : TripListUiAction
    data class OnMateOpenChatClicked(val openKakaoChatLink: String) : TripListUiAction
    data object OnSelectMateClicked : TripListUiAction
}
