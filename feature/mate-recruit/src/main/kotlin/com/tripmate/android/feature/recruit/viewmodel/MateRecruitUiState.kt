package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.domain.entity.GenderAgeGroupEntity
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class MateRecruitUiState(
    val mateRecruitTitle: String = "",
    val tripLocation: String = "서퍼비치",
    val tripLocationAddress: String = "강원도 양양군 현북면 하조대해안길 119",
    val mateRecruitContent: String = "",
    val mateType: MateType = MateType.ALL,
    val selectedGenderAgeGroups: PersistentList<GenderAgeGroupEntity> = persistentListOf(),
    val openKakaoLink: String = "",
)
