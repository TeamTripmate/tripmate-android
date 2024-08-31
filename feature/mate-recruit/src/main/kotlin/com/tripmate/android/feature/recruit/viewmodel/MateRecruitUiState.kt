package com.tripmate.android.feature.recruit.viewmodel

import com.tripmate.android.core.common.extension.formatToDate
import com.tripmate.android.core.common.extension.formatToTime
import com.tripmate.android.domain.entity.GenderAgeGroupEntity
import com.tripmate.android.feature.mate_recruit.R
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

data class MateRecruitUiState(
    val mateRecruitTitle: String = "",
    val tripLocation: String = "서퍼비치",
    val tripLocationAddress: String = "강원도 양양군 현북면 하조대해안길 119",
    val mateRecruitContent: String = "",
    val mateRecruitDate: String = LocalDate.now(ZoneId.of("Asia/Seoul")).formatToDate(),
    val isMateRecruitDateUpdated: Boolean = false,
    val mateRecruitTime: String = LocalTime.now(ZoneId.of("Asia/Seoul")).formatToTime(),
    val isMateRecruitTimeUpdated: Boolean = false,
    val selectedMateType: MateType = MateType.ALL,
    val allGenderAgeGroups: PersistentList<GenderAgeGroupEntity> = persistentListOf(
        GenderAgeGroupEntity(id = 1, textResId = R.string.same_gender, isSelected = false),
        GenderAgeGroupEntity(id = 2, textResId = R.string.same_age, isSelected = false),
        GenderAgeGroupEntity(id = 3, textResId = R.string.no_matter, isSelected = false),
    ),
    val selectedGenderAgeGroups: PersistentList<GenderAgeGroupEntity> = persistentListOf(),
    val openKakaoLink: String = "",
    val isDatePickerVisible: Boolean = false,
    val isTimePickerVisible: Boolean = false,
)
