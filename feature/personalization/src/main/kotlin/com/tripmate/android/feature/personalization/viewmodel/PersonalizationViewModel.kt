package com.tripmate.android.feature.personalization.viewmodel

import androidx.lifecycle.ViewModel
import com.tripmate.android.core.data.repository.PersonalizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PersonalizationViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val personalizationRepository: PersonalizationRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonalizationUiState())
    val uiState: StateFlow<PersonalizationUiState> = _uiState.asStateFlow()
}
