package com.tripmate.android.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.core.common.UiText
import com.tripmate.android.domain.repository.AuthRepository
import com.tripmate.android.domain.repository.PersonalizationRepository
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val personalizationRepository: PersonalizationRepository,
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
            authRepository.saveAuthToken(accessToken, refreshToken)
            val isPersonalizationCompleted = personalizationRepository.checkPersonalizationCompletion()
            if (isPersonalizationCompleted) {
                _uiEvent.send(LoginUiEvent.NavigateToMain)
            } else {
                _uiEvent.send(LoginUiEvent.NavigateToPersonalization)
            }
        }
    }

    fun setErrorMessage(message: UiText) {
        viewModelScope.launch {
            _uiEvent.send(LoginUiEvent.ShowToast(message))
        }
    }
}
