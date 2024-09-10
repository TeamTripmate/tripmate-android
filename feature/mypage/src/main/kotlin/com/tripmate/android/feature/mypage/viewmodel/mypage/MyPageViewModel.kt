package com.tripmate.android.feature.mypage.viewmodel.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.entity.WithdrawReasonEntity
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
class MyPageViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val myPageRepository: MateRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    @Suppress("EmptyFunctionBlock")
    fun onAction(action: MyPageUiAction) {
        when (action) {
            is MyPageUiAction.OnBackClicked -> navigateBack()
            is MyPageUiAction.OnTicketClicked -> navigateToMyTripCharacterInfo(action.characterId)
            is MyPageUiAction.OnMyPickClicked -> navigateToMyPick()
            is MyPageUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is MyPageUiAction.OnLogoutClicked -> {}
            is MyPageUiAction.OnWithdrawClicked -> navigateToWithdraw()
            is MyPageUiAction.OnWithdrawReasonSelected -> addWithdrawReason(action.withdrawReason)
            is MyPageUiAction.OnWithdrawReasonDeselected -> removeWithdrawReason(action.withdrawReason)
            is MyPageUiAction.OnWithdrawReasonDescriptionUpdated -> setWithdrawReasonDescription(action.withdrawReasonDescription)
            is MyPageUiAction.OnRealWithdrawClicked -> {}
            is MyPageUiAction.OnUseMoreClicked -> {}
            else -> {}
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateBack)
        }
    }

    private fun navigateToMyTripCharacterInfo(characterId: Long) {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToMyTripCharacterInfo(characterId = characterId))
        }
    }

    private fun navigateToMyPick() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToMyPick)
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun navigateToWithdraw() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToWithdraw)
        }
    }

    private fun addWithdrawReason(withdrawReason: WithdrawReasonEntity) {
        _uiState.update {
            it.copy(selectedWithdrawReasons = it.selectedWithdrawReasons.add(withdrawReason))
        }
    }

    private fun removeWithdrawReason(withdrawReason: WithdrawReasonEntity) {
        _uiState.update {
            it.copy(selectedWithdrawReasons = it.selectedWithdrawReasons.remove(withdrawReason))
        }
    }

    private fun setWithdrawReasonDescription(withdrawReasonDescription: String) {
        _uiState.update {
            it.copy(withdrawReasonDescription = withdrawReasonDescription)
        }
    }
}
