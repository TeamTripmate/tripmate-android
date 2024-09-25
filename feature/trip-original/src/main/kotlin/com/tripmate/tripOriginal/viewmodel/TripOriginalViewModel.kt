package com.tripmate.trip_original.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.trip_original.navigation.SPOT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
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
class TripOriginalViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val spotId: Int = requireNotNull(savedStateHandle.get<Int>(SPOT_ID))

    private val _uiState = MutableStateFlow(TripOriginalUiState())
    val uiState: StateFlow<TripOriginalUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<TripOriginalUiEvent>()
    val uiEvent: Flow<TripOriginalUiEvent> = _uiEvent.receiveAsFlow()

    init {
        _uiState.update {
            it.copy(
                spotId = spotId,
            )
        }
    }

    fun onAction(action: TripOriginalUiAction) {
        when (action) {
            is TripOriginalUiAction.OnBackClicked -> navigateBack()
            is TripOriginalUiAction.OnMateClicked -> onMateClicked()
            is TripOriginalUiAction.OnMateClosed -> onMateClosed()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(TripOriginalUiEvent.NavigateBack)
        }
    }

    private fun onMateClicked() {
        _uiState.update {
            it.copy(
                isShowPopup = true,
            )
        }
    }

    private fun onMateClosed() {
        _uiState.update {
            it.copy(
                isShowPopup = false,
            )
        }
    }
}
