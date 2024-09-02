package com.tripmate.android.feature.mate_recruit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.entity.GenderAgeGroupEntity
import com.tripmate.android.domain.repository.MateRepository
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
class MateRecruitViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val mateRepository: MateRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MateRecruitUiState())
    val uiState: StateFlow<MateRecruitUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateRecruitUiEvent>()
    val uiEvent: Flow<MateRecruitUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MateRecruitUiAction) {
        when (action) {
            is MateRecruitUiAction.OnBackClicked -> navigateBack()
            is MateRecruitUiAction.OnMateRecruitTitleUpdated -> setMateRecruitTitle(action.title)
            is MateRecruitUiAction.OnScheduleDateClicked -> setDatePickerVisible(true)
            is MateRecruitUiAction.OnScheduleTimeClicked -> setTimePickerVisible(true)
            is MateRecruitUiAction.OnScheduleDateUpdated -> setMateRecruitDate(action.date)
            is MateRecruitUiAction.OnScheduleTimeUpdated -> setMateRecruitTime(action.time)
            is MateRecruitUiAction.OnDismiss -> {
                when (action.pickerType) {
                    PickerType.DATE -> setDatePickerVisible(false)
                    PickerType.TIME -> setTimePickerVisible(false)
                }
            }

            is MateRecruitUiAction.OnMateTypeSelected -> setMateType(action.mateType)
            is MateRecruitUiAction.OnGenderAgeGroupSelected -> addGenderAgeGroup(action.group)
            is MateRecruitUiAction.OnGenderAgeGroupDeselected -> removeSelectedTripStyle(action.group)
            is MateRecruitUiAction.OnMateRecruitContentUpdated -> setMateRecruitContent(action.content)
            is MateRecruitUiAction.OnOpenKakaoLinkUpdated -> setOpenKakaoLink(action.link)
            is MateRecruitUiAction.OnDoneClicked -> finish()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitUiEvent.NavigateBack)
        }
    }

    private fun setMateRecruitTitle(title: String) {
        _uiState.update { it.copy(mateRecruitTitle = title) }
    }

    private fun setTimePickerVisible(flag: Boolean) {
        _uiState.update { it.copy(isTimePickerVisible = flag) }
    }

    private fun setOpenKakaoLink(link: String) {
        _uiState.update { it.copy(openKakaoLink = link) }
    }

    private fun setMateRecruitContent(content: String) {
        _uiState.update { it.copy(mateRecruitContent = content) }
    }

    private fun setMateRecruitDate(date: String) {
        _uiState.update {
            it.copy(
                mateRecruitDate = date,
                isMateRecruitDateUpdated = true,
            )
        }
    }

    private fun setMateRecruitTime(time: String) {
        _uiState.update {
            it.copy(
                mateRecruitTime = time,
                isMateRecruitTimeUpdated = true,
            )
        }
    }

    private fun setMateType(mateType: MateType) {
        _uiState.update { it.copy(selectedMateType = mateType) }
    }

    private fun addGenderAgeGroup(genderAgeGroup: GenderAgeGroupEntity) {
        _uiState.update {
            it.copy(selectedGenderAgeGroups = it.selectedGenderAgeGroups.add(genderAgeGroup))
        }
    }

    private fun removeSelectedTripStyle(genderAgeGroup: GenderAgeGroupEntity) {
        _uiState.update {
            it.copy(selectedGenderAgeGroups = it.selectedGenderAgeGroups.remove(genderAgeGroup))
        }
    }

    private fun setDatePickerVisible(flag: Boolean) {
        _uiState.update { it.copy(isDatePickerVisible = flag) }
    }

    private fun finish() {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitUiEvent.Finish)
        }
    }
}
