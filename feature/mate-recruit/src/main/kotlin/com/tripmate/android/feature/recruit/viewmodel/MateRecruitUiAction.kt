package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.domain.entity.TripStyleEntity

sealed interface MateRecruitUiAction {
    data class OnTripStyleSelected(val tripStyle: TripStyleEntity) : MateRecruitUiAction
    data class OnTripStyleDeselected(val tripStyle: TripStyleEntity) : MateRecruitUiAction
    data class OnGenderSelected(val gender: Gender) : MateRecruitUiAction
    data class OnBirthDateUpdated(val birthDate: String) : MateRecruitUiAction
    data object OnClearIconClicked : MateRecruitUiAction
    data class OnSelectClick(val screenType: ScreenType) : MateRecruitUiAction
}

enum class ScreenType {
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
