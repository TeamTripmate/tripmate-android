package com.tripmate.android.feature.home.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface HomeUiEvent {
    data class ShowToast(val message: UiText) : HomeUiEvent
}
