package com.tripmate.android.feature.mate_recruit_post.viewmodel

sealed interface MateRecruitPostUiAction {
    data object OnBackClicked : MateRecruitPostUiAction
    data class OnMateRecruitTitleUpdated(val title: String) : MateRecruitPostUiAction
}
