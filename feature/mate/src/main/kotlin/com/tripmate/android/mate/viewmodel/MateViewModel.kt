package com.tripmate.android.mate.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.LatLng
import com.tripmate.android.domain.entity.SpotEntity
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
            is MateUiAction.OnMapCategorySelected -> getNearbyTouristSpots(action.categoryType)
            is MateUiAction.OnShowListClicked -> setShowListClicked(action.isShowing)
            is MateUiAction.OnCurrentLocationClicked -> setCurrentLocation()
            is MateUiAction.OnSearchingListClicked -> setSearchingList(action.isShowing)
            is MateUiAction.OnMarkerClicked -> setSelectPoi(action.poiId)
            is MateUiAction.OnTripCardClicked -> navigateToTripDetail(action.spotId)
        }
    }

    private fun getNearbyTouristSpots(categoryType: CategoryType) {
        _currentLocation.value?.let {
            val latitude = it.latitude
            val longitude = it.longitude
            viewModelScope.launch {
                mapRepository.getNearbyTouristSpots(
                    searchType = "AROUND_ME",
                    latitude = latitude.toString(),
                    longitude = longitude.toString(),
                    range = "10000",
                    spotType = categoryType.categoryCode ?: "",
                    spotTypeGroup = "",
                )
                    .onSuccess { spots ->
                        spots.let {
                            _uiState.update { uiState ->
                                uiState.copy(
                                    categoryType = categoryType,
                                    spotList = spots,
                                    selectPoiItem = spots.first(),
                                )
                            }
                        }
                    }
                    .onFailure { }
            }
        }
    }

    private fun setShowListClicked(isShowing: Boolean) {
        _uiState.update {
            it.copy(
                isShowingList = isShowing,
                isShowRecruitList = false,
            )
        }
    }

    private fun setCurrentLocation() {
        viewModelScope.launch {
            _uiEvent.send(MateUiEvent.ClickCurrentLocation)
        }
    }

    private fun navigateToTripDetail(spotId: String) {
        viewModelScope.launch {
            _uiEvent.send(MateUiEvent.NavigateToTripDetail(spotId))
        }
    }

    private fun setSearchingList(isShowing: Boolean) {
        _uiState.update { uiState ->
            uiState.copy(
                isShowRecruitList = isShowing,
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
                setZoomLevel(14)
            }
        } ?: run {
            CameraPositionDefaults.DefaultCameraPosition
        }
        cameraPositionState.position = cameraPosition
    }

    fun movePoiLocation(cameraPositionState: CameraPositionState, selectPoiItem: SpotEntity) {
        val cameraPosition = selectPoiItem.let {
            cameraPosition {
                setPosition(
                    LatLng.from(
                        it.latitude,
                        it.longitude,
                    ),
                )
                setZoomLevel(14)
            }
        }
        cameraPositionState.position = cameraPosition
    }

    private fun getMarkerInfo(markerID: Int): SpotEntity? {
        return uiState.value.spotList.find { it.id == markerID }
    }
}
