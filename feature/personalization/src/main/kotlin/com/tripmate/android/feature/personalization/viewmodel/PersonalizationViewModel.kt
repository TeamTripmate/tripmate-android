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
            is PersonalizationUiAction.OnQuestionAnswerSelected -> {
                when (action.questionNumber) {
                    1 -> _uiState.update { it.copy(question1Answer = action.answer) }
                    2 -> _uiState.update { it.copy(question2Answer = action.answer) }
                    3 -> _uiState.update { it.copy(question3Answer = action.answer) }
                    4 -> _uiState.update { it.copy(question4Answer = action.answer) }
                }
            }

            is PersonalizationUiAction.OnTripStyleSelected -> addSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnTripStyleDeselected -> removeSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnSelectClick -> {
                when (action.screenType) {
                    ScreenType.QUESTION_1 -> navigateToQuestion2()
                    ScreenType.QUESTION_2 -> navigateToQuestion3()
                    ScreenType.QUESTION_3 -> navigateToQuestion4()
                    ScreenType.QUESTION_4 -> navigateToTripStyle()
                    ScreenType.TRIP_STYLE -> navigateToUserInfo()
                    ScreenType.USER_INFO -> navigateToResult()
                    ScreenType.RESULT -> navigateToMain()
                }
            }
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

    private fun navigateToTripStyle() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToTripStyle)
        }
    }

    private fun navigateToQuestion2() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToQuestion2)
        }
    }

    private fun navigateToQuestion3() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToQuestion3)
        }
    }

    private fun navigateToQuestion4() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToQuestion4)
        }
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToMain)
        }
    }

    private fun navigateToResult() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToResult)
        }
    }

    private fun navigateToUserInfo() {
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.NavigateToUserInfo)
        }
    }
}
