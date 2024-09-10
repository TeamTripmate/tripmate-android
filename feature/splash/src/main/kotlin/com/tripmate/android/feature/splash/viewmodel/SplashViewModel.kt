package com.tripmate.android.feature.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.LoginRepository
import com.tripmate.android.domain.repository.PersonalizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val personalizationRepository: PersonalizationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkLoginAndPersonalizationComplete()
    }

    private fun checkLoginAndPersonalizationComplete() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val isLoggedIn = loginRepository.getAccessToken().isNotEmpty()
            if (isLoggedIn) {
                checkPersonalizationCompletion()
            } else {
                _uiState.update {
                    it.copy(navigateTo = "login", isLoading = false)
                }
            }
        }
    }

    private fun checkPersonalizationCompletion() {
        viewModelScope.launch {
            val destination = if (personalizationRepository.checkPersonalizationCompletion()) {
                "main"
            } else {
                "personalization"
            }
            _uiState.update {
                it.copy(navigateTo = destination, isLoading = false)
            }
        }
    }
}
