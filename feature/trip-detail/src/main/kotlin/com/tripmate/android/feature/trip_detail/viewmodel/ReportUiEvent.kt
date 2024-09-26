package com.tripmate.android.feature.trip_detail.viewmodel

sealed interface ReportUiEvent {
    data object NavigateBack : ReportUiEvent
    data object NavigateToReport : ReportUiEvent
    data object Report : ReportUiEvent
    data object NavigateToMain : ReportUiEvent
}
