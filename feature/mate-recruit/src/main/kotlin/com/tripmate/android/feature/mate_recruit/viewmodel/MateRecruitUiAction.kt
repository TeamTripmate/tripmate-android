package com.tripmate.android.feature.mate_recruit.viewmodel

import com.tripmate.android.domain.entity.GenderAgeGroupEntity

sealed interface MateRecruitUiAction {
    data class OnMateRecruitTitleUpdated(val title: String) : MateRecruitUiAction
    data object OnScheduleDateClicked : MateRecruitUiAction
    data object OnScheduleTimeClicked : MateRecruitUiAction
    data class OnScheduleDateUpdated(val date: String) : MateRecruitUiAction
    data class OnScheduleTimeUpdated(val time: String) : MateRecruitUiAction
    data class OnDismiss(val pickerType: PickerType) : MateRecruitUiAction
    data class OnMateRecruitContentUpdated(val content: String) : MateRecruitUiAction
    data class OnMateTypeSelected(val mateType: MateType) : MateRecruitUiAction
    data class OnGenderAgeGroupSelected(val group: GenderAgeGroupEntity) : MateRecruitUiAction
    data class OnGenderAgeGroupDeselected(val group: GenderAgeGroupEntity) : MateRecruitUiAction
    data class OnOpenKakaoLinkUpdated(val link: String) : MateRecruitUiAction
    data object OnDoneClicked : MateRecruitUiAction
}

enum class MateType {
    SIMILAR,
    ALL,
}

enum class PickerType {
    DATE,
    TIME,
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
