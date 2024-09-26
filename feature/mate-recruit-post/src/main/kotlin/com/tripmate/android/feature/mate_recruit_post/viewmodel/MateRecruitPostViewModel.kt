package com.tripmate.android.feature.mate_recruit_post.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.MateRepository
import com.tripmate.android.feature.mate_recruit_post.navigation.COMPANION_ID
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
class MateRecruitPostViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val mateRepository: MateRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val companionId: Int = requireNotNull(savedStateHandle.get<Int>(COMPANION_ID))

    private val _uiState = MutableStateFlow(MateRecruitPostUiState())
    val uiState: StateFlow<MateRecruitPostUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateRecruitPostUiEvent>()
    val uiEvent: Flow<MateRecruitPostUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MateRecruitPostUiAction) {
        when (action) {
            is MateRecruitPostUiAction.OnBackClicked -> navigateBack()
            is MateRecruitPostUiAction.OnCompanionApply -> onCompanionApply()
            is MateRecruitPostUiAction.GetCompanionsDetailInfo -> getCompanionsDetailInfo()
            is MateRecruitPostUiAction.OnKakaoOpenChat -> onKakaoOpenChat(action.chatLink)
        }
    }

    private fun onCompanionApply() {
        viewModelScope.launch {
            mateRepository.companionApply(
                companionId,
            ).onSuccess {
                navigateBack()
            }.onFailure { }
        }
    }

    private fun getCompanionsDetailInfo() {
        viewModelScope.launch {
            mateRepository.getCompanionsDetailInfo(
                companionId,
            ).onSuccess { respose ->
                _uiState.update {
                    it.copy(
                        mateRecruitPostEntity = respose,
                        isCompanionApplySuccess = respose.accompanyYn,
                    )
                }
            }.onFailure { }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitPostUiEvent.NavigateBack)
        }
    }

    private fun onKakaoOpenChat(chatLink: String) {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitPostUiEvent.NavigateToKakaoOpenChat(chatLink))
        }
    }
}
