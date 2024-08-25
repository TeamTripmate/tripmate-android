package com.tripmate.android.feature.map.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.LatLng
import com.tripmate.android.domain.repository.MapRepository
import com.tripmate.android.feature.map.extension.cameraPosition
import com.tripmate.android.feature.map.state.CameraPositionDefaults
import com.tripmate.android.feature.map.state.CameraPositionState
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
class MapViewModel @Inject constructor(
    application: Application,
    @Suppress("UnusedPrivateProperty")
    private val mapRepository: MapRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState())
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MapUiEvent>()
    val uiEvent: Flow<MapUiEvent> = _uiEvent.receiveAsFlow()

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _currentLocation = MutableStateFlow<Location?>(null)
    private val currentLocation = _currentLocation.asStateFlow()

    fun onAction(action: MapUiAction) {
        when (action) {
            is MapUiAction.OnMapCategorySelected -> setMapCategoryType(action.categoryType)
            is MapUiAction.OnShowListClicked -> setShowListClicked(action.isShowing)
            is MapUiAction.OnCurrentLocationClicked -> setCurrentLocation()
            is MapUiAction.OnSearchingListClicked -> setSearchingList(action.isShowing)
        }
    }

    private fun setMapCategoryType(categoryType: CategoryType) {
        _uiState.update {
            it.copy(
                categoryType = categoryType,
                simpleList = it.getTestList(categoryType),
            )
        }
    }

    private fun setShowListClicked(isShowing: Boolean) {
        _uiState.update { it.copy(isShowingList = isShowing) }
    }

    private fun setCurrentLocation() {
        viewModelScope.launch {
            _uiEvent.send(MapUiEvent.ClickCurrentLocation)
        }
    }

    private fun setSearchingList(isShowing: Boolean) {
        _uiState.update {
            it.copy(
                simpleList = if (isShowing) it.simpleList.filter { it.isSearching } else it.simpleList)
        }
    }

    @SuppressLint("MissingPermission")
    fun fetchCurrentLocation() {
        viewModelScope.launch {
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    _currentLocation.value = location
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun moveCurrentLocation(cameraPositionState: CameraPositionState) {
        val cameraPosition = currentLocation.value?.let {
            cameraPosition {
                setPosition(
                    LatLng.from(
                        it.latitude,
                        it.longitude,
                    ),
                )
                setZoomLevel(16)
            }
        }?: run {
            CameraPositionDefaults.DefaultCameraPosition
        }
        cameraPositionState.position = cameraPosition
    }
}
