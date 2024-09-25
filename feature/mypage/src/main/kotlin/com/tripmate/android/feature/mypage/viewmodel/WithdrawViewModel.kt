package com.tripmate.android.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.entity.WithdrawReasonEntity
import com.tripmate.android.domain.repository.AuthRepository
import com.tripmate.android.domain.repository.MyPageRepository
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
class WithdrawViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val myPageRepository: MyPageRepository,
) : ViewModel(), ErrorHandlerActions {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MyPageUiAction) {
        when (action) {
            is MyPageUiAction.OnBackClicked -> navigateBack()
            is MyPageUiAction.OnWithdrawReasonSelected -> addWithdrawReason(action.withdrawReason)
            is MyPageUiAction.OnWithdrawReasonDeselected -> removeWithdrawReason(action.withdrawReason)
            is MyPageUiAction.OnWithdrawReasonDescriptionUpdated -> setWithdrawReasonDescription(action.withdrawReasonDescription)
            is MyPageUiAction.OnRealWithdrawClicked -> setWithdrawDialogVisible(true)
            is MyPageUiAction.OnDialogCloseClicked -> setWithdrawDialogVisible(false)
            is MyPageUiAction.OnRealRealWithdrawClicked -> withdraw()
            is MyPageUiAction.OnUseMoreClicked -> navigateToMain()
            else -> {}
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isWithdrawDialogVisible = false)
            }
            _uiEvent.send(MyPageUiEvent.NavigateBack)
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

    private fun setWithdrawDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isWithdrawDialogVisible = flag)
        }
    }

    private fun withdraw() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isWithdrawDialogVisible = false)
            }
            authRepository.withdrawal()
                .onSuccess {
                    authRepository.clearAuthToken()
                    myPageRepository.completePersonalization(false)
                    _uiEvent.send(MyPageUiEvent.NavigateToLogin)
                }.onFailure { exception ->
                    handleException(exception, this@WithdrawViewModel)
                }
        }
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToMain)
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        //
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        //
    }
}
