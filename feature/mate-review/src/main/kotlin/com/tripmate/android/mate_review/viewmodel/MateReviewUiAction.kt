package com.tripmate.android.mate_review.viewmodel

import com.tripmate.android.domain.entity.BadReviewEntity
import com.tripmate.android.domain.entity.GoodReviewEntity

sealed interface MateReviewUiAction {
    data object OnBackClicked : MateReviewUiAction
    data class OnGoodReviewSelected(val review: GoodReviewEntity) : MateReviewUiAction
    data class OnGoodReviewDeselected(val review: GoodReviewEntity) : MateReviewUiAction
    data class OnBadReviewSelected(val review: BadReviewEntity) : MateReviewUiAction
    data class OnBadReviewDeselected(val review: BadReviewEntity) : MateReviewUiAction
    data object OnDoneClicked : MateReviewUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
