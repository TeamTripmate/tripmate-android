package com.tripmate.android.feature.tripdetail.viewmodel

import com.tripmate.android.domain.entity.ReportReasonEntity

sealed interface ReportUiAction {
    data object OnBackClicked : ReportUiAction
    data class OnReportReasonSelected(val reportReason: ReportReasonEntity) : ReportUiAction
    data class OnReportReasonDeselected(val reportReason: ReportReasonEntity) : ReportUiAction
    data class OnReportReasonDescriptionUpdated(val reportReasonDescription: String) : ReportUiAction
    data object OnDialogCloseClicked : ReportUiAction
    data object OnReportClicked : ReportUiAction
    data object OnCancelClicked : ReportUiAction
    data object OnConfirmClicked: ReportUiAction
}

enum class ErrorType {
    NETWORK,
    SERVER,
}
