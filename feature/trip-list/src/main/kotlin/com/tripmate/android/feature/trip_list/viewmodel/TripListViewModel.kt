package com.tripmate.android.feature.trip_list.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.repository.TripListRepository
import com.tripmate.android.feature.trip_list.navigation.COMPANION_ID
import com.tripmate.android.feature.trip_list.navigation.PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripListViewModel @Inject constructor(
    private val tripListRepository: TripListRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ErrorHandlerActions {
    private val companionId: Long = savedStateHandle.get<Long>(COMPANION_ID) ?: 0L
    private val page: Int = savedStateHandle.get<Int>(PAGE) ?: 0

    private val _uiState = MutableStateFlow(TripListUiState())
    val uiState: StateFlow<TripListUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<TripListUiEvent>()
    val uiEvent: Flow<TripListUiEvent> = _uiEvent.receiveAsFlow()

    init {
        _uiState.update {
            it.copy(
                companionId = companionId,
                page = page,
            )
        }
        getCreatedTripList()
        getParticipatedTripList()
    }

    fun onAction(action: TripListUiAction) {
        when (action) {
            is TripListUiAction.OnBackClicked -> navigateBack()
            is TripListUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is TripListUiAction.OnTicketClicked -> ticketClicked(action.ticketId, action.userId)
            is TripListUiAction.OnClickViewMateList -> navigateToMateList(action.companionId, action.page)
            is TripListUiAction.OnTripStatusCardClicked -> navigateToMateOpenChat(
                action.openChatLink,
                action.selectedKeyword,
                action.tripStyle,
                action.characterId,
                action.companionId,
            )

            is TripListUiAction.OnMateOpenChatClicked -> navigateToKakaoOpenChat(action.openKakaoChatLink)
            is TripListUiAction.OnSelectMateClicked -> selectMate()
            is TripListUiAction.OnTripDetailClicked -> navigateToDetailScreen(action.companionId)
            is TripListUiAction.OnCharacterDescriptionClicked -> navigateToCharacterDescription(
                action.characterId,
                action.tag1,
                action.tag2,
                action.tag3,
            )
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateBack)
        }
    }

    fun getCreatedTripList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            tripListRepository.getCreatedTripListByUser()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(createdCompanionList = result.toImmutableList())
                    }
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun getParticipatedTripList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            tripListRepository.getTripsParticipatedByUser()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            participatedCompanionList = result.toImmutableList(),
                            hostOpenChatUrl = result.toImmutableList().firstOrNull()?.openChatLink ?: "https://open.kakao.com/o/gObLOlQg",
                        )
                    }
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun ticketClicked(ticketId: Int, userId: Long) {
        _uiState.update {
            it.copy(
                selectedTicketIndex = ticketId,
                selectedUserId = userId,
                selectedCompanionId = it.createdCompanionList.getOrNull(it.page)?.companionId ?: 0,
            )
        }
    }

    private fun navigateToMateList(companionId: Long, page: Int) {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToMateList(companionId = companionId, page = page))
        }
    }

    private fun navigateToMateOpenChat(
        openChatLink: String,
        selectedKeyword: List<String>,
        tripStyle: String,
        characterId: String,
        companionId: Long,
    ) {
        viewModelScope.launch {
            val keyword1 = selectedKeyword.getOrNull(0) ?: ""
            val keyword2 = selectedKeyword.getOrNull(1) ?: ""
            val keyword3 = selectedKeyword.getOrNull(2) ?: ""

            _uiEvent.send(
                TripListUiEvent.NavigateToMateOpenChat(
                    companionId = companionId,
                    openChatLink = openChatLink,
                    selectedKeyword1 = keyword1,
                    selectedKeyword2 = keyword2,
                    selectedKeyword3 = keyword3,
                    tripStyle = tripStyle,
                    characterId = characterId,
                ),
            )
        }
    }

    private fun navigateToDetailScreen(companionId: Long) {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToDetailScreen(companionId))
        }
    }

    private fun navigateToCharacterDescription(characterId: String, tag1: String, tag2: String, tag3: String) {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToCharacterDescription(characterId, tag1, tag2, tag3))
        }
    }

    private fun navigateToKakaoOpenChat(openKakaoChatLink: String) {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToKakaoOpenChat(openKakaoChatLink))
        }
    }

    private fun selectMate() {
        viewModelScope.launch {
            tripListRepository.selectMate(_uiState.value.selectedUserId, _uiState.value.selectedCompanionId)
                .onSuccess {
                    _uiEvent.send(TripListUiEvent.NavigateBack)
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        //
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        //
    }
}
