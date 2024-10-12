package com.tripmate.android.feature.home.viewmodel

import com.tripmate.android.domain.entity.SpotEntity

sealed interface HomeUiAction {
    data class OnTabChanged(val index: Int) : HomeUiAction
    data class OnClickChip(val chipName: String) : HomeUiAction
    data class OnHeartClicked(val spot: SpotEntity) : HomeUiAction
}
