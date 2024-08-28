package com.tripmate.android.feature.recruit.viewmodel

import androidx.lifecycle.ViewModel
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
            is MateRecruitUiAction.OnMateRecruitTitleUpdated -> setMateRecruitTitle(action.title)
            is MateRecruitUiAction.OnGenderAgeGroupSelected -> addGenderAgeGroup(action.group)
            is MateRecruitUiAction.OnGenderAgeGroupDeselected -> removeSelectedTripStyle(action.group)
            is MateRecruitUiAction.OnMateTypeSelected -> setMateType(action.mateType)
            is MateRecruitUiAction.OnMateRecruitContentUpdated -> setMateRecruitContent(action.content)
            is MateRecruitUiAction.OnOpenKakaoLinkUpdated -> setOpenKakaoLink(action.link)
            is MateRecruitUiAction.OnDoneClick -> {}
        }
    }

    private fun setMateRecruitTitle(title: String) {
        _uiState.update { it.copy(mateRecruitTitle = title) }
    }

    private fun setMateRecruitContent(content: String) {
        _uiState.update { it.copy(mateRecruitContent = content) }
    }

    private fun setMateType(mateType: MateType) {
        _uiState.update { it.copy(mateType = mateType) }
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

    private fun setOpenKakaoLink(link: String) {
        _uiState.update { it.copy(openKakaoLink = link) }
    }
}
