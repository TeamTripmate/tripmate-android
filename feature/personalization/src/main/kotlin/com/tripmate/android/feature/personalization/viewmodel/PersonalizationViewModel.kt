package com.tripmate.android.feature.personalization.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.domain.repository.PersonalizationRepository
import com.tripmate.android.feature.personalization.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    private val personalizationRepository: PersonalizationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalizationUiState())
    val uiState: StateFlow<PersonalizationUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<PersonalizationUiEvent>()
    val uiEvent: Flow<PersonalizationUiEvent> = _uiEvent.receiveAsFlow()

    enum class BirthDateValidationResult {
        VALID,
        INVALID_DATE,
        UNDERAGE,
    }

    fun onAction(action: PersonalizationUiAction) {
        when (action) {
            is PersonalizationUiAction.OnQuestionAnswerSelected -> setQuestionAnswer(action.questionNumber, action.answer)
            is PersonalizationUiAction.OnTripStyleSelected -> addSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnTripStyleDeselected -> removeSelectedTripStyle(action.tripStyle)
            is PersonalizationUiAction.OnGenderSelected -> setGender(action.gender)
            is PersonalizationUiAction.OnBirthDateUpdated -> setBirthDate(action.birthDate)
            is PersonalizationUiAction.OnClearIconClicked -> clearText()
            is PersonalizationUiAction.OnUnderAgeDialogConfirmClick -> finish()
            is PersonalizationUiAction.OnSelectClick -> navigateToNextScreen(action.screenType)
        }
    }

    private fun setQuestionAnswer(questionNumber: Int, answer: Int) {
        when (questionNumber) {
            1 -> _uiState.update { it.copy(question1Answer = answer) }
            2 -> _uiState.update { it.copy(question2Answer = answer) }
            3 -> _uiState.update { it.copy(question3Answer = answer) }
            4 -> _uiState.update { it.copy(question4Answer = answer) }
        }
    }

    private fun addSelectedTripStyle(tripStyle: TripStyleEntity) {
        if (_uiState.value.selectedTripStyles.size >= 3) {
            viewModelScope.launch {
                _uiEvent.send(PersonalizationUiEvent.ShowToast(UiText.StringResource(R.string.trip_style_error_message)))
            }
            return
        }
        _uiState.update {
            it.copy(selectedTripStyles = it.selectedTripStyles.add(tripStyle))
        }
    }

    private fun removeSelectedTripStyle(tripStyle: TripStyleEntity) {
        _uiState.update {
            it.copy(selectedTripStyles = it.selectedTripStyles.remove(tripStyle))
        }
    }

    private fun setGender(gender: Gender) {
        _uiState.update { it.copy(selectedGender = gender) }
    }

    private fun setBirthDate(birthDate: String) {
        if (birthDate.length <= 6) {
            _uiState.update { it.copy(birthDate = birthDate) }
            if (birthDate.length == 6) {
                if (validateBirthDate(birthDate) == BirthDateValidationResult.INVALID_DATE) {
                    _uiState.update { it.copy(birthDateErrorText = UiText.StringResource(R.string.invalid_birth_date_error_message)) }
                } else {
                    _uiState.update { it.copy(birthDateErrorText = null) }
                }
            }
        }
    }

    private fun clearText() {
        _uiState.update {
            it.copy(
                birthDate = "",
                birthDateErrorText = null,
            )
        }
    }

    private fun finish() {
        _uiState.update {
            it.copy(isUnderAgeDialogVisible = false)
        }
        viewModelScope.launch {
            _uiEvent.send(PersonalizationUiEvent.Finish)
        }
    }

    private fun setUnderAgeDialogVisibility(flag: Boolean) {
        _uiState.update { it.copy(isUnderAgeDialogVisible = flag) }
    }

    private fun navigateToNextScreen(screenType: ScreenType) {
        when (screenType) {
            ScreenType.QUESTION_1 -> navigateToQuestion2()
            ScreenType.QUESTION_2 -> navigateToQuestion3()
            ScreenType.QUESTION_3 -> navigateToQuestion4()
            ScreenType.QUESTION_4 -> navigateToTripStyle()
            ScreenType.TRIP_STYLE -> navigateToUserInfo()
            ScreenType.USER_INFO -> {
                if (validateBirthDate(_uiState.value.birthDate) == BirthDateValidationResult.VALID) {
                    navigateToResult()
                } else {
                    if (validateBirthDate(_uiState.value.birthDate) == BirthDateValidationResult.UNDERAGE) {
                        setUnderAgeDialogVisibility(true)
                    }
                }
            }

            ScreenType.RESULT -> completePersonalization()
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

    @Suppress("SwallowedException")
    private fun validateBirthDate(birthDate: String): BirthDateValidationResult {
        // 숫자로 만 이루어져 있는지 체크
        if (!birthDate.matches(Regex("[0-9]+"))) return BirthDateValidationResult.INVALID_DATE

        // 유효한 날짜인지 체크
        val year = birthDate.substring(0, 2).toInt()
        val month = birthDate.substring(2, 4).toInt()
        val day = birthDate.substring(4, 6).toInt()

        if (month < 1 || month > 12 || day < 1 || day > 31) return BirthDateValidationResult.INVALID_DATE

        // 현재 날짜 기준으로 만 19세 이상인지 체크
        val currentYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year % 100
        val fullYear = if (year <= currentYear) 2000 + year else 1900 + year

        try {
            val birthDateFormatted = LocalDate(fullYear, month, day)
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            val age = today.year - birthDateFormatted.year

            return if (age > 19) {
                BirthDateValidationResult.VALID
            } else if (age == 19) {
                val birthDayThisYear = LocalDate(today.year, birthDateFormatted.month, birthDateFormatted.dayOfMonth)
                if (birthDayThisYear <= today) BirthDateValidationResult.VALID else BirthDateValidationResult.UNDERAGE
            } else {
                BirthDateValidationResult.UNDERAGE
            }
        } catch (e: IllegalArgumentException) {
            return BirthDateValidationResult.INVALID_DATE
        }
    }

    private fun completePersonalization() {
        viewModelScope.launch {
            personalizationRepository.completePersonalization(flag = true)
            navigateToMain()
        }
    }
}
