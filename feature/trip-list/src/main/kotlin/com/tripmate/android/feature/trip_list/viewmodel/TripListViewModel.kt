package com.tripmate.android.feature.trip_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.repository.TripListRepository
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
) : ViewModel(), ErrorHandlerActions {
    private val _uiState = MutableStateFlow(TripListUiState())
    val uiState: StateFlow<TripListUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<TripListUiEvent>()
    val uiEvent: Flow<TripListUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: TripListUiAction) {
        when (action) {
            is TripListUiAction.OnBackClicked -> navigateBack()
            is TripListUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is TripListUiAction.OnTicketClicked -> ticketClicked(action.ticketId)
            is TripListUiAction.OnClickViewMateList -> navigateToMateList() // todo:로직추가
            is TripListUiAction.OnTripStatusCardClicked -> navigateToMateOpenChat()
            is TripListUiAction.OnMateOpenChatClicked -> navigateToKakaoOpenChat()
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
                        it.copy(participatedCompanionList = result.toImmutableList())
                    }
                }.onFailure { exception ->
                    handleException(exception, this@TripListViewModel)
                }
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun ticketClicked(ticketId: Int) {
        _uiState.update {
            it.copy(
                isTicketClicked = it.isTicketClicked.mapIndexed { index, _ -> index == ticketId }.toImmutableList(),
            )
        }
    }

    private fun navigateToMateList() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToMateList)
        }
    }

    private fun navigateToMateOpenChat() {
        // item entity 에 포함 되어있는 openChatUrl 을 통해 uiState update
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToMateOpenChat)
        }
    }

    private fun navigateToKakaoOpenChat() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToKakaoOpenChat(_uiState.value.openChatUrl))
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        //
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        //
    }
}
