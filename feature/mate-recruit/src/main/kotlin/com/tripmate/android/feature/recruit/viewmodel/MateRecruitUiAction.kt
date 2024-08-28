package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.domain.entity.GenderAgeGroupEntity

sealed interface MateRecruitUiAction {
    data class OnMateRecruitTitleUpdated(val title: String) : MateRecruitUiAction
    data class OnMateRecruitContentUpdated(val content: String) : MateRecruitUiAction
    data class OnGenderAgeGroupSelected(val group: GenderAgeGroupEntity) : MateRecruitUiAction
    data class OnGenderAgeGroupDeselected(val group: GenderAgeGroupEntity) : MateRecruitUiAction
    data class OnMateTypeSelected(val mateType: MateType) : MateRecruitUiAction
    data class OnOpenKakaoLinkUpdated(val link: String) : MateRecruitUiAction
    data object OnDoneClick : MateRecruitUiAction
}

enum class MateType {
    SIMILAR,
    ALL,
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
