package com.tripmate.android.feature.mate_recruit_post.viewmodel

sealed interface MateRecruitPostUiEvent {
    data object NavigateBack : MateRecruitPostUiEvent
    data object Finish : MateRecruitPostUiEvent
}
