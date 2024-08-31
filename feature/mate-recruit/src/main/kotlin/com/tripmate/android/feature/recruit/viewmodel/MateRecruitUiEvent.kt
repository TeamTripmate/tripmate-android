package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface MateRecruitUiEvent {
    data object Finish : MateRecruitUiEvent
    data class ShowToast(val message: UiText) : MateRecruitUiEvent
}
