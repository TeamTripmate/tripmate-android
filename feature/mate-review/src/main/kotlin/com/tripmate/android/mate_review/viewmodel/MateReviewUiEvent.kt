package com.tripmate.android.mate_review.viewmodel

import com.tripmate.android.core.common.UiText

sealed interface MateReviewUiEvent {
    data object Finish : MateReviewUiEvent
    data class ShowToast(val message: UiText) : MateReviewUiEvent
}
