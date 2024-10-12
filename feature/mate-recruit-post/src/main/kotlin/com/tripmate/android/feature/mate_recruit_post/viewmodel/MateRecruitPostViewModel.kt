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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MateRecruitPostViewModel @Inject constructor(
    private val mateRepository: MateRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val companionId: Long = requireNotNull(savedStateHandle.get<Long>(COMPANION_ID))

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
                _uiEvent.send(MateRecruitPostUiEvent.Finish)
            }.onFailure { }
        }
    }

    private fun getCompanionsDetailInfo() {
        viewModelScope.launch {
            mateRepository.getCompanionsDetailInfo(
                companionId,
            ).onSuccess { response ->
                _uiState.update {
                    it.copy(
                        mateRecruitPostEntity = response.copy(date = parseAndFormatDate(response.date)),
                        isCompanionApplySuccess = response.accompanyYn,
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

    private fun parseAndFormatDate(inputDate: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(inputDate, inputFormatter)

        val outputFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분")
        return dateTime.format(outputFormatter)
    }
}
