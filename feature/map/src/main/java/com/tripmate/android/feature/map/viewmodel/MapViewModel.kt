package com.tripmate.android.feature.map.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.tripmate.android.domain.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject



@HiltViewModel
class MapViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val mapRepository: MapRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent: Flow<MapUiEvent> = _uiEvent.receiveAsFlow()


//    private val fusedLocationClient: FusedLocationProviderClient =
//
//    private val _currentLocation = MutableStateFlow<Location?>(null)
//    val currentLocation = _currentLocation.asStateFlow()


    fun onAction(action: MapUiAction) {
        when (action) {
            is MapUiAction.OnMapCategorySelected -> setMapCategoryType(action.categoryType)
            is MapUiAction.OnShowListClicked -> setShowListClicked(action.isShowing)
        }
    }

    private fun setMapCategoryType(categoryType: CategoryType) {
        _uiState.update {
            it.copy(categoryType = categoryType)
            it.copy(simpleList = it.getTestList())
        }
    }

    private fun setShowListClicked(isShowing: Boolean) {
        _uiState.update { it.copy(isShowingList = isShowing) }
    }
}
