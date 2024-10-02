package com.tripmate.android.feature.trip_detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.repository.TripDetailRepository
import com.tripmate.android.feature.trip_detail.navigation.SPOT_ID
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
class TripDetailViewModel @Inject constructor(
    @Suppress("UnusedPrivateProperty")
    private val tripDetailRepository: TripDetailRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val spotId: String = requireNotNull(savedStateHandle.get<String>(SPOT_ID))

    private val _uiState = MutableStateFlow(TripDetailUiState())
    val uiState: StateFlow<TripDetailUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<TripDetailUiEvent>()
    val uiEvent: Flow<TripDetailUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: TripDetailUiAction) {
        when (action) {
            is TripDetailUiAction.OnTabChanged -> updateSelectedTab(action.index)
            is TripDetailUiAction.GetTripDetailInfo -> getTripDetailInfo(spotId)
            is TripDetailUiAction.OnClickMateRecruit -> navigateMateRecruit()
            is TripDetailUiAction.OnClickMateReviewPost -> navigateMateReviewPost(action.companionId)
            is TripDetailUiAction.OnClickReport -> navigateToReport()
        }
    }

    private fun updateSelectedTab(tab: Int) {
        _uiState.update { it.copy(selectedTabIndex = tab) }
    }

    private fun getTripDetailInfo(spotId: String) {
        viewModelScope.launch {
            tripDetailRepository.getTripDetail(spotId)
                .onSuccess { response ->
                    _uiState.update {
                        it.copy(
                            tripDetail = response,
                        )
                    }
                }
                .onFailure { }
        }
    }

    private fun navigateMateRecruit() {
        viewModelScope.launch {
            _uiEvent.send(
                TripDetailUiEvent.NavigateMateRecruit(
                    spotId,
                    _uiState.value.tripDetail.title,
                    _uiState.value.tripDetail.location.address.address1,
                ),
            )
        }
    }

    private fun navigateMateReviewPost(companionId: Int) {
        viewModelScope.launch {
            _uiEvent.send(TripDetailUiEvent.NavigateToMateReviewPost(companionId))
        }
    }

    private fun navigateToReport() {
        viewModelScope.launch {
            _uiEvent.send(TripDetailUiEvent.NavigateToReport)
        }
    }
}
