package com.tripmate.android.feature.mypage.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.MyPageRepository
import com.tripmate.android.feature.mypage.navigation.CHARACTER_ID
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
class MyTripCharacterInfoViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val myPageRepository: MyPageRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @Suppress("UnusedPrivateProperty")
    private val characterId: Long = requireNotNull(savedStateHandle.get<Long>(CHARACTER_ID)) {
        "characterId is required."
    }

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: MyPageUiAction) {
        when (action) {
            is MyPageUiAction.OnBackClicked -> navigateBack()
            is MyPageUiAction.OnCharacterTypeReselectClicked -> navigateToPersonalization()
            is MyPageUiAction.OnShareMyTripStyle -> shareMyTripStyle(action.image)
            is MyPageUiAction.OnShareMyTripStyleClicked -> setMyTripStyleShare(action.isShared)
            else -> {}
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateBack)
        }
    }

    private fun navigateToPersonalization() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToPersonalization)
        }
    }

    private fun shareMyTripStyle(image: Bitmap) {
        viewModelScope.launch {
            setMyTripStyleShare(false)
            _uiEvent.send(MyPageUiEvent.ShareMyTripStyle(image))
        }
    }

    private fun setMyTripStyleShare(isShared: Boolean) {
        _uiState.update {
            it.copy(isMyTripStyleShared = isShared)
        }
    }
}
