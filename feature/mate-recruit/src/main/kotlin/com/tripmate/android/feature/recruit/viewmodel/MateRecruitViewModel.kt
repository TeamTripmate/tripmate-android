package com.tripmate.android.feature.recruit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.UiText
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.domain.repository.PersonalizationRepository
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
class MateRecruitViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val personalizationRepository: PersonalizationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MateRecruitUiState())
    val uiState: StateFlow<MateRecruitUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateRecruitUiEvent>()
    val uiEvent: Flow<MateRecruitUiEvent> = _uiEvent.receiveAsFlow()

    enum class BirthDateValidationResult {
        VALID,
        INVALID_DATE,
        UNDERAGE,
    }

    fun onAction(action: MateRecruitUiAction) {
        when (action) {
            is MateRecruitUiAction.OnTripStyleSelected -> addSelectedTripStyle(action.tripStyle)
            is MateRecruitUiAction.OnTripStyleDeselected -> removeSelectedTripStyle(action.tripStyle)
            is MateRecruitUiAction.OnGenderSelected -> setGender(action.gender)
            is MateRecruitUiAction.OnBirthDateUpdated -> setBirthDate(action.birthDate)
            is MateRecruitUiAction.OnClearIconClicked -> clearText()
            is MateRecruitUiAction.OnSelectClick -> {}
        }
    }

    private fun addSelectedTripStyle(tripStyle: TripStyleEntity) {
        if (_uiState.value.selectedTripStyles.size >= 3) {
            viewModelScope.launch {}
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
                    _uiState.update { it.copy(birthDateErrorText = null) }
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
}
