package com.tripmate.android.mate.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.LatLng
import com.tripmate.android.domain.entity.POISimpleListEntity
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
class MateViewModel @Inject constructor(
    application: Application,
    @Suppress("UnusedPrivateProperty")
    private val mapRepository: MapRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MateUiState())
    val uiState: StateFlow<MateUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<MateUiEvent>()
    val uiEvent: Flow<MateUiEvent> = _uiEvent.receiveAsFlow()

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _currentLocation = MutableStateFlow<Location?>(null)
    private val currentLocation = _currentLocation.asStateFlow()

    fun onAction(action: MateUiAction) {
        when (action) {
            is MateUiAction.OnMapCategorySelected -> setMapCategoryType(action.categoryType)
            is MateUiAction.OnShowListClicked -> setShowListClicked(action.isShowing)
            is MateUiAction.OnCurrentLocationClicked -> setCurrentLocation()
            is MateUiAction.OnSearchingListClicked -> setSearchingList(action.isShowing)
            is MateUiAction.OnMarkerClicked -> setSelectPoi(action.poiId)
            is MateUiAction.OnTripCardClicked -> navigateToTripDetail()
        }
    }

    private fun setMapCategoryType(categoryType: CategoryType) {
        _uiState.update {
            it.copy(
                categoryType = categoryType,
                simpleList = it.getTestList(categoryType),
                selectPoiItem = it.getTestList(categoryType).first(),
            )
        }
    }

    private fun setShowListClicked(isShowing: Boolean) {
        _uiState.update { it.copy(isShowingList = isShowing) }
    }

    private fun setCurrentLocation() {
        viewModelScope.launch {
            _uiEvent.send(MateUiEvent.ClickCurrentLocation)
        }
    }

    private fun navigateToTripDetail() {
        viewModelScope.launch {
            _uiEvent.send(MateUiEvent.NavigateToTripDetail)
        }
    }

    private fun setSearchingList(isShowing: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(
                simpleList = if (isShowing) uiState.simpleList.filter { it.isSearching } else uiState.simpleList,
            )
        }
    }

    private fun setSelectPoi(poiId: Int) {
        _uiState.update { uiState ->
            uiState.copy(
                selectPoiItem = getMarkerInfo(poiId),
            )
        }
    }

    @SuppressLint("MissingPermission")
    @Suppress("TooGenericExceptionCaught")
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
        } ?: run {
            CameraPositionDefaults.DefaultCameraPosition
        }
        cameraPositionState.position = cameraPosition
    }

    fun movePoiLocation(cameraPositionState: CameraPositionState, selectPoiItem: POISimpleListEntity) {
        val cameraPosition = selectPoiItem.let {
            cameraPosition {
                setPosition(
                    LatLng.from(
                        it.lat,
                        it.lon,
                    ),
                )
            }
        }
        cameraPositionState.position = cameraPosition
    }

    private fun getMarkerInfo(markerID: Int): POISimpleListEntity? {
        return uiState.value.simpleList.find { it.poiId == markerID }
    }
}
