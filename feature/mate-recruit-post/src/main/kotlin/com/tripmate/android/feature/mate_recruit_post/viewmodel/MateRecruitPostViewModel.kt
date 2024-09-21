package com.tripmate.android.feature.mate_recruit_post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.MateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MateRecruitPostViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val mateRepository: MateRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MateRecruitPostUiState())
    val uiState: StateFlow<MateRecruitPostUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateRecruitPostUiEvent>()
    val uiEvent: Flow<MateRecruitPostUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MateRecruitPostUiAction) {
        when (action) {
            is MateRecruitPostUiAction.OnMateRecruitTitleUpdated -> navigateBack()
            is MateRecruitPostUiAction.OnBackClicked -> TODO()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MateRecruitPostUiEvent.NavigateBack)
        }
    }
}
