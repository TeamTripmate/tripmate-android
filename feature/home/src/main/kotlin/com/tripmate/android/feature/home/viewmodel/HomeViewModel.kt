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

    // 필터 매핑
    private val filterMapping = mapOf(
        "전체" to "",
        "체험" to "EXPERIENCE",
        "문화∙예술" to "CULTURE_AND_ART",
        "축제∙공연" to "FESTIVAL_AND_PERFORMANCE",
        "자연∙휴양" to "NATURE_AND_REST",
        "역사" to "HISTORY",
        "레포츠" to "LEISURE_SPORTS",
        "숙박" to "ACCOMMODATION",
        "쇼핑" to "SHOPPING",
        "맛집∙카페" to "RESTAURANT_AND_CAFE",
        "Trip Original" to "TripOriginal",
    )

    init {
        // 기본으로 "전체" 칩 선택 후 리스트 업데이트
        updateSpotsList("전체")
    }

    fun onAction(action: HomeUiAction) {
        when (action) {
            is HomeUiAction.OnBackClicked -> navigateBack()
            is HomeUiAction.OnTabChanged -> {
                updateSelectedTab(action.index)
                updateSpotsList("전체") // 탭 변경 시에도 전체로 초기화
            }

            is HomeUiAction.OnClickChip -> {
                updateSelectedChips(action.chipName) // 선택된 칩 업데이트
                updateSpotsList(action.chipName) // 선택된 칩 기반으로 스팟 업데이트
            }
        }
    }

    // 스팟 리스트 업데이트
    private fun updateSpotsList(chipName: String) {
        val currentState = _uiState.value

        // 탭에 따라 spotTypeGroup 설정
        val spotTypeGroup = if (currentState.selectedTabIndex == 0) "ACTIVITY" else "HEALING"

        // 선택된 칩을 서버 필터 값으로 변환
        val spotType = filterMapping[chipName] ?: ""

        // 서버에서 필터된 스팟 가져오기
        getNearbyTouristSpots(spotTypeGroup, spotType)
    }

    private fun getNearbyTouristSpots(spotTypeGroup: String, spotType: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            mapRepository.getNearbyTouristSpots(
                searchType = "RANDOM",
                latitude = "37.751853",
                longitude = "128.8760574",
                range = "10000",
                spotTypeGroup = spotTypeGroup,
                spotType = spotType,
            ).onSuccess { spots ->
                _uiState.update {
                    it.copy(
                        spotList = spots.toImmutableList(),
                    )
                }
            }.onFailure {
                // 실패 처리 로직
            }
            _uiState.update { it.copy(isLoading = false) }
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
        if (chipName == "Trip Original") {
            _uiState.update { it.copy(showTripOriginal = true) }
        } else {
            _uiState.update { it.copy(showTripOriginal = false) }
        }
        val updatedChips = persistentListOf(chipName) // 하나만 선택 가능하므로 새롭게 설정
        _uiState.update {
            if (it.selectedTabIndex == 0) {
                it.copy(activitySelectedChips = updatedChips)
            } else {
                it.copy(healingSelectedChips = updatedChips)
            }
        }
    }
}
