package com.tripmate.android.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.ErrorHandlerActions
import com.tripmate.android.core.common.UiText
import com.tripmate.android.core.common.handleException
import com.tripmate.android.domain.repository.AuthRepository
import com.tripmate.android.domain.repository.MyPageRepository
import com.tripmate.android.feature.mypage.R
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
    private val myPageRepository: MyPageRepository,
    private val authRepository: AuthRepository,
) : ViewModel(), ErrorHandlerActions {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            myPageRepository.getUserInfo()
                .onSuccess { result ->
                    _uiState.update {
                        it.copy(
                            type1 = result.tripStyle,
                            type2 = result.selectedKeyword,
                            type3 = result.characterId.toString(),
                        )
                    }
                }
                .onFailure { exception ->
                    handleException(exception, this@MyPageViewModel)
                }
        }
    }

    fun onAction(action: MyPageUiAction) {
        when (action) {
            is MyPageUiAction.OnBackClicked -> navigateBack()
            is MyPageUiAction.OnTicketClicked -> navigateToMyTripCharacterInfo(action.characterId)
            is MyPageUiAction.OnMyPickClicked -> navigateToMyPick()
            is MyPageUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is MyPageUiAction.OnLogoutClicked -> logout()
            is MyPageUiAction.OnWithdrawClicked -> navigateToWithdraw()
            is MyPageUiAction.OnPrivacyPolicyClicked -> navigateToPrivacyPolicy()
            is MyPageUiAction.OnTermOfUseClicked -> navigateToTermOfUse()
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

    private fun logout() {
        viewModelScope.launch {
            authRepository.clearAuthToken()
            _uiEvent.send(MyPageUiEvent.NavigateToLogin)
            _uiEvent.send(MyPageUiEvent.ShowToast(UiText.StringResource(R.string.logout_complete)))
        }
    }

    private fun navigateToWithdraw() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToWithdraw)
        }
    }

    private fun navigateToPrivacyPolicy() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToPrivacyPolicy)
        }
    }

    private fun navigateToTermOfUse() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToTermOfUse)
        }
    }

    override fun setServerErrorDialogVisible(flag: Boolean) {
        TODO("Not yet implemented")
    }

    override fun setNetworkErrorDialogVisible(flag: Boolean) {
        TODO("Not yet implemented")
    }
}
