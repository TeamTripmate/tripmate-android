package com.tripmate.android.feature.tripdetail.viewmodel

sealed interface ReportUiEvent {
    data object NavigateBack : ReportUiEvent
    data object NavigateToReport : ReportUiEvent
    data object Report : ReportUiEvent
    data object NavigateToMain : ReportUiEvent
}
