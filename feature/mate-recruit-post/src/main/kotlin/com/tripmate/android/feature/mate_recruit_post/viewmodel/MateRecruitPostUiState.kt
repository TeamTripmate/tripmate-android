package com.tripmate.android.feature.mate_recruit_post.viewmodel

import com.tripmate.android.domain.entity.MateRecruitPostEntity

data class MateRecruitPostUiState(
    var mateRecruitPostEntity: MateRecruitPostEntity = MateRecruitPostEntity(),
    var isCompanionApplySuccess: Boolean = false,
    var requestYn: Boolean = false,
)
