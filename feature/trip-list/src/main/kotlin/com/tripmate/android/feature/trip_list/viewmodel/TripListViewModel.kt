package com.tripmate.android.feature.trip_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class TripListViewModel @Inject constructor() : ViewModel() {
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
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateBack)
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun ticketClicked(ticketId: Int) {
        _uiState.update {
            it.copy(
                isTicketClicked = it.isTicketClicked.mapIndexed
                    { index, _ -> index == ticketId }.toImmutableList(),
            )
        }
    }

    private fun navigateToMateList() {
        viewModelScope.launch {
            _uiEvent.send(TripListUiEvent.NavigateToMateList)
        }
    }
}
