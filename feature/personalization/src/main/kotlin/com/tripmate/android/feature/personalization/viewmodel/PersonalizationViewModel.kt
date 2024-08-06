package com.tripmate.android.feature.personalization.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.data.repository.PersonalizationRepository
import com.tripmate.android.domain.entity.TripStyleEntity
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
class PersonalizationViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val personalizationRepository: PersonalizationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalizationUiState())
    val uiState: StateFlow<PersonalizationUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<PersonalizationUiEvent>()
    val uiEvent: Flow<PersonalizationUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: PersonalizationUiAction) {
        when (action) {
            is PersonalizationUiAction.OnTripStyleSelected -> addSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnTripStyleDeselected -> removeSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnSelectClick -> navigateToUserInfo()
        }
    }

    private fun addSelectedTripStyle(tripStyle: TripStyleEntity) {
        _uiState.update {
            it.copy(selectedTripStyles = it.selectedTripStyles.add(tripStyle))
        }
    }

    private fun removeSelectedTripStyle(tripStyle: TripStyleEntity) {
        _uiState.update {
            it.copy(selectedTripStyles = it.selectedTripStyles.remove(tripStyle))
        }
    }

    private fun navigateToUserInfo() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToUserInfo)
        }
    }
}
