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
    savedStateHandle: SavedStateHandle,
    private val tripListRepository: TripListRepository,
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
            is TripListUiAction.OnTripStatusCardClicked -> navigateToMateOpenChat(action.openChatLink, action.characterId, action.tripStyle)
            is TripListUiAction.OnMateOpenChatClicked -> navigateToKakaoOpenChat()
            is TripListUiAction.OnSelectMateClicked -> selectMate()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateBack)
        }
    }

    fun getCreatedTripList() {
        viewModelScope.launch {
            tripListRepository.getCreatedTripListByUser()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(createdCompanionList = result.toImmutableList())
                    }
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
        }
    }

    fun getParticipatedTripList() {
        viewModelScope.launch {
            tripListRepository.getTripsParticipatedByUser()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            participatedCompanionList = result.toImmutableList(),
                            // Todo 임시코드
                            hostOpenChatUrl = result.toImmutableList().firstOrNull()?.openChatLink ?: "https://open.kakao.com/o/gObLOlQg",
                        )
                    }
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun ticketClicked(ticketId: Int, userId: Long) {
        _uiState.update {
            it.copy(
                isTicketClicked = it.isTicketClicked.mapIndexed { index, _ -> index == ticketId }.toImmutableList(),
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

    private fun navigateToMateOpenChat(openChatLink: String, characterId: String, tripStyle: List<String>) {
        // item entity 에 포함 되어있는 openChatUrl 을 통해 uiState update
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    hostOpenChatUrl = openChatLink,
                    hostTripStyle = tripStyle,
                    hostCharacterId = characterId,
                )
            }
            _uiEvent.send(TripListUiEvent.NavigateToMateOpenChat)
        }
    }

    private fun navigateToKakaoOpenChat() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToKakaoOpenChat(_uiState.value.hostOpenChatUrl))
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
