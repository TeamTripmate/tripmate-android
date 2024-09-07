package com.tripmate.android.feature.mypage.viewmodel.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.MateRepository
import com.tripmate.android.feature.mypage.navigation.CHARACTER_ID
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
class MyTripCharacterInfoViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val myPageRepository: MateRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val characterId: Long = requireNotNull(savedStateHandle.get<Long>(CHARACTER_ID)) { "characterId is required." }

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    @Suppress("EmptyFunctionBlock",)
    fun onAction(action: MyPageUiAction) {
        when(action) {
            is MyPageUiAction.OnTicketClicked -> navigateToMyTripCharacterInfo(action.characterId)
            else -> {}
        }
    }

    private fun navigateToMyTripCharacterInfo(characterId: Long) {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToMyTripCharacterInfo(characterId = characterId))
        }
    }
}
