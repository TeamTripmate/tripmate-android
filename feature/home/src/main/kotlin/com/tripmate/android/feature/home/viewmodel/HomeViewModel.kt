package com.tripmate.android.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
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
    private val mapRepository: MapRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent: Flow<HomeUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnBackClicked -> navigateBack()
            is HomeUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is HomeUiAction.OnClickChip -> {
                updateSelectedChips(action.chipName)
                updateSpotsList(action.chipName)
            }
        }
    }

    init {
        updateSpotsList("전체")
    }

    private fun getNearbyTouristSpots(spotTypeGroup: String, spotType: String) {
        viewModelScope.launch {
            mapRepository.getNearbyTouristSpots(
                searchType = "RANDOM",
                latitude = "37.751853",
                longitude = "128.8760574",
                range = "10000",
                spotTypeGroup = spotTypeGroup,
                spotType = spotType,
            )
                .onSuccess { spots ->
                    spots.let {
                        _uiState.update {
                            it.copy(
                                spotList = spots.toImmutableList(),
                            )
                        }
                    }
                }
                .onFailure { }
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

    private fun updateSelectedChips(chipName: String) {
        val updatedChips = persistentListOf(chipName)
        _uiState.update {
            if (it.selectedTabIndex == 0) {
                it.copy(activitySelectedChips = updatedChips)
            } else {
                it.copy(healingSelectedChips = updatedChips)
            }
        }
    }

    private fun updateSpotsList(chipName: String) {
        val currentState = _uiState.value
        val selectedChips = if (currentState.selectedTabIndex == 0)
            currentState.activitySelectedChips else currentState.healingSelectedChips

        val spotTypeGroup = if (currentState.selectedTabIndex == 0) "ACTIVITY" else "HEALING"

        if (selectedChips.contains("전체") || selectedChips.isEmpty()) {
            getNearbyTouristSpots(spotTypeGroup, "")
        } else {
            getNearbyTouristSpots(spotTypeGroup, chipName)
        }
    }
}
