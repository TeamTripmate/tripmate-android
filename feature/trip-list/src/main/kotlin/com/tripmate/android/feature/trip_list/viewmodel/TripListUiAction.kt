package com.tripmate.android.feature.trip_list.viewmodel

import com.tripmate.android.domain.entity.triplist.ApplicantInfoEntity

sealed interface TripListUiAction {
    data object OnBackClicked : TripListUiAction
    data class OnTabChanged(val index: Int) : TripListUiAction
    data class OnTicketClicked(val ticketId: Int, val userId: Long) : TripListUiAction
    data class OnClickViewMateList(val companionId: Long, val matesInfo: List<ApplicantInfoEntity>) : TripListUiAction
    data class OnTripStatusCardClicked(val openChatLink: String, val tripStyle: List<String>, val characterId: String) : TripListUiAction
    data object OnMateOpenChatClicked : TripListUiAction
    data object OnSelectMateClicked : TripListUiAction
}
