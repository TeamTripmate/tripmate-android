package com.tripmate.android.feature.trip_list.viewmodel

sealed interface TripListUiEvent {
    data object NavigateBack : TripListUiEvent
    data class NavigateToMateList(val companionId: Long, val page: Int) : TripListUiEvent
    data class NavigateToMateOpenChat(
        val companionId: Long,
        val openChatLink: String,
        val selectedKeyword1: String,
        val selectedKeyword2: String,
        val selectedKeyword3: String,
        val tripStyle: String,
        val characterId: String,
    ) : TripListUiEvent
    data class NavigateToKakaoOpenChat(val openChatUrl: String) : TripListUiEvent
    data class NavigateToReviewScreen(val companionId: Long, val title: String, val date: String) : TripListUiEvent
    data class NavigateToDetailScreen(val companionId: Long) : TripListUiEvent
}
