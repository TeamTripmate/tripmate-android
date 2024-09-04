package com.tripmate.android.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class HomeViewModel @Inject constructor(
    // private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent: Flow<HomeUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnBackClicked -> navigateBack()
            is HomeUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is HomeUiAction.OnClickChip -> updateSelectedChipList(action.chipName)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(HomeUiEvent.NavigateBack)
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun updateSelectedChipList(chipName: String) {
        _uiState.update { currentState ->
            val currentChips = if (currentState.selectedTabIndex == 0)
                currentState.activitySelectedChips else currentState.healingSelectedChips

            val newChips = currentChips.toMutableList().apply {
                when {
                    chipName == "전체" -> {
                        clear()
                        add("전체")
                    }

                    contains("전체") && chipName != "전체" -> {
                        remove("전체")
                        add(chipName)
                    }

                    contains(chipName) -> {
                        remove(chipName)
                        if (isEmpty()) {
                            add("전체")
                        }
                    }

                    else -> {
                        add(chipName)
                    }
                }
            }.toImmutableList()
            if (currentState.selectedTabIndex == 0) {
                currentState.copy(activitySelectedChips = newChips)
            } else {
                currentState.copy(healingSelectedChips = newChips)
            }
        }
    }
}
