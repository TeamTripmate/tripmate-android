package com.tripmate.android.feature.detailtrip.viewmodel

import androidx.lifecycle.ViewModel
import com.tripmate.android.domain.repository.TripDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val tripDetailRepository: TripDetailRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TripDetailUiState())
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<TripDetailUiEvent>()
    val uiEvent: Flow<TripDetailUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: TripDetailUiAction) {
        when (action) {
            is TripDetailUiAction.OnTabChanged -> updateSelectedTab(action.index)
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }
}
