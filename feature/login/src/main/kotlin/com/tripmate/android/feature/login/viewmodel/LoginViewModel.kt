package com.tripmate.android.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.repository.LoginRepository
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
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent: Flow<LoginUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.OnLoginButtonClick -> kakaoLogin()
        }
    }

    private fun kakaoLogin() {
        viewModelScope.launch {
            _uiEvent.send(LoginUiEvent.KakaoLogin)
        }
    }

    fun saveAuthToken(accessToken: String, refreshToken: String) {
        viewModelScope.launch {
            loginRepository.saveAuthToken(accessToken, refreshToken)
            _uiEvent.send(LoginUiEvent.NavigateToMain)
        }
    }

    fun setErrorMessage(message: UiText) {
        viewModelScope.launch {
            _uiEvent.send(LoginUiEvent.ShowToast(message))
        }
    }
}
