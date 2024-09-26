package com.tripmate.android.feature.trip_detail.viewmodel

import com.tripmate.android.domain.entity.ReportReasonEntity
import com.tripmate.android.feature.trip_detail.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class ReportUiState(
    val selectedTabIndex: Int = 0,
    val allReportReasons: ImmutableList<ReportReasonEntity> = persistentListOf(
        ReportReasonEntity(0, R.string.privacy, false),
        ReportReasonEntity(1, R.string.advertisement, false),
        ReportReasonEntity(2, R.string.no_mate_content, false),
        ReportReasonEntity(3, R.string.abuse, false),
        ReportReasonEntity(4, R.string.etc, false),
    ),
    val selectedReportReasons: PersistentList<ReportReasonEntity> = persistentListOf(),
    val reportReasonDescription: String = "",
    val isReportDialogVisible: Boolean = false,
)
