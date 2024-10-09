package com.tripmate.android.feature.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.entity.SpotEntity
import com.tripmate.android.domain.repository.MyPickRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
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
class MyPickViewModel @Inject constructor(
    private val myPickRepository: MyPickRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState: StateFlow<MyPageUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MyPageUiEvent>()
    val uiEvent: Flow<MyPageUiEvent> = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            myPickRepository.getMyPickList().collect { myPickList ->
                _uiState.update { it.copy(myPickList = myPickList.toImmutableList()) }
            }
        }
    }

    fun onAction(action: MyPageUiAction) {
        when (action) {
            is MyPageUiAction.OnBackClicked -> navigateBack()
            is MyPageUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is MyPageUiAction.OnMyPickItemClicked -> navigateToMyTripDetail(action.spotId)
            is MyPageUiAction.OnHeartClicked -> unregisterMyPick(action.spot)
            else -> {}
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateBack)
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun navigateToMyTripDetail(spotId: Int) {
        viewModelScope.launch {
            _uiEvent.send(MyPageUiEvent.NavigateToTripDetail(spotId))
        }
    }

    private fun unregisterMyPick(spot: SpotEntity) {
        viewModelScope.launch {
            myPickRepository.unregisterMyPick(spot)
        }
    }
}
