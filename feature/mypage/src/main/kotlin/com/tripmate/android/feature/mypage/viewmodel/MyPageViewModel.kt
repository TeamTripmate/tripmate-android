package com.tripmate.android.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import com.tripmate.android.domain.repository.MateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    @Suppress("EmptyFunctionBlock", "UnusedParameter")
    fun onAction(action: MyPageUiAction) {}
}
