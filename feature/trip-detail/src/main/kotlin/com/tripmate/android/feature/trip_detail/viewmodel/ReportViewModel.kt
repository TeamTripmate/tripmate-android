package com.tripmate.android.feature.trip_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripmate.android.domain.entity.ReportReasonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<ReportUiEvent>()
    val uiEvent: Flow<ReportUiEvent> = _uiEvent.receiveAsFlow()

    fun onAction(action: ReportUiAction) {
        when (action) {
            is ReportUiAction.OnBackClicked -> navigateBack()
            is ReportUiAction.OnReportReasonSelected -> addReportReason(action.reportReason)
            is ReportUiAction.OnReportReasonDeselected -> removeReportReason(action.reportReason)
            is ReportUiAction.OnReportReasonDescriptionUpdated -> setReportReasonDescription(action.reportReasonDescription)
            is ReportUiAction.OnReportClicked -> setReportDialogVisible(true)
            is ReportUiAction.OnDialogCloseClicked -> setReportDialogVisible(false)
            is ReportUiAction.OnCancelClicked -> navigateBack()
            is ReportUiAction.OnConfirmClicked -> completeReport()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isReportDialogVisible = false)
            }
            _uiEvent.send(ReportUiEvent.NavigateBack)
        }
    }

    private fun addReportReason(reportReason: ReportReasonEntity) {
        _uiState.update {
            it.copy(selectedReportReasons = it.selectedReportReasons.add(reportReason))
        }
    }

    private fun removeReportReason(reportReason: ReportReasonEntity) {
        _uiState.update {
            it.copy(selectedReportReasons = it.selectedReportReasons.remove(reportReason))
        }
    }

    private fun setReportReasonDescription(reportReasonDescription: String) {
        _uiState.update {
            it.copy(reportReasonDescription = reportReasonDescription)
        }
    }

    private fun setReportDialogVisible(flag: Boolean) {
        _uiState.update {
            it.copy(isReportDialogVisible = flag)
        }
    }

    private fun completeReport() {
        setReportDialogVisible(false)
        navigateBack()
    }
}
