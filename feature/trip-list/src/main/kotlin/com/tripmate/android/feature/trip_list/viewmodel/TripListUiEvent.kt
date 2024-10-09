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
    data class NavigateToCharacterDescription(val characterId: String, val tag1: String, val tag2: String, val tag3: String) : TripListUiEvent
    data class NavigateToDetailScreen(val companionId: Long) : TripListUiEvent
}
