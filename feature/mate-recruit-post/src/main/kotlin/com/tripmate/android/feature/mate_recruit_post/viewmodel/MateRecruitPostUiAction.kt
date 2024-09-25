package com.tripmate.android.feature.mate_recruit_post.viewmodel

sealed interface MateRecruitPostUiAction {
    data object OnBackClicked : MateRecruitPostUiAction
    data object OnCompanionApply : MateRecruitPostUiAction
    data object GetCompanionsDetailInfo : MateRecruitPostUiAction
    data class OnKakaoOpenChat(val chatLink: String) : MateRecruitPostUiAction
}
