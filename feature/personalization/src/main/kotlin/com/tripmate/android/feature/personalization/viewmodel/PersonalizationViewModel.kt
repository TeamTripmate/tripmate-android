package com.tripmate.android.feature.personalization.viewmodel

import androidx.lifecycle.ViewModel
import com.tripmate.android.core.data.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalizationUiState())
    val uiState: StateFlow<PersonalizationUiState> = _uiState.asStateFlow()

    //Todo: 온보딩을 체크해 유저가 온보딩을 완료했는지 확인하는 로직 작성
}
