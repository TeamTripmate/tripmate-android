package com.tripmate.android.feature.personalization.viewmodel

import android.graphics.Bitmap
import com.tripmate.android.domain.entity.TripStyleEntity

sealed interface PersonalizationUiAction {
    data class OnQuestionAnswerSelected(val questionNumber: Int, val answer: Int) : PersonalizationUiAction
    data class OnTripStyleSelected(val tripStyle: TripStyleEntity) : PersonalizationUiAction
    data class OnTripStyleDeselected(val tripStyle: TripStyleEntity) : PersonalizationUiAction
    data class OnGenderSelected(val gender: String) : PersonalizationUiAction
    data class OnBirthDateUpdated(val birthDate: String) : PersonalizationUiAction
    data object OnClearIconClicked : PersonalizationUiAction
    data object OnUnderAgeDialogConfirmClick : PersonalizationUiAction
    data class OnSelectClick(val currentScreen: CurrentScreen) : PersonalizationUiAction
    data class OnShareMyTripStyle(val image: Bitmap) : PersonalizationUiAction
    data class OnShareMyTripStyleClicked(val isShared: Boolean) : PersonalizationUiAction
}

enum class CurrentScreen {
    QUESTION_1,
    QUESTION_2,
    QUESTION_3,
    QUESTION_4,
    TRIP_STYLE,
    USER_INFO,
    RESULT,
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
