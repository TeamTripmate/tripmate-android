package com.tripmate.android.feature.trip_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateCheckBox
import com.tripmate.android.core.designsystem.component.TripmateOutlinedButton
import com.tripmate.android.core.designsystem.component.TripmateTextField
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.ReportReasonEntity
import com.tripmate.android.feature.trip_detail.component.ReportDialog
import com.tripmate.android.feature.trip_detail.viewmodel.ReportUiAction
import com.tripmate.android.feature.trip_detail.viewmodel.ReportUiEvent
import com.tripmate.android.core.designsystem.R as designSystemR
import com.tripmate.android.feature.trip_detail.viewmodel.ReportUiState
import com.tripmate.android.feature.trip_detail.viewmodel.ReportViewModel
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ReportRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: ReportViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is ReportUiEvent.NavigateBack -> popBackStack()
            is ReportUiEvent.Report -> popBackStack()
            else -> {}
        }
    }

    ReportScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun ReportScreen(
    uiState: ReportUiState,
    innerPadding: PaddingValues,
    onAction: (ReportUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(id = R.string.report),
                onNavigationClick = { onAction(ReportUiAction.OnBackClicked) },
            )
            ReportContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }

    if (uiState.isReportDialogVisible) {
        ReportDialog(
            onDismissRequest = { onAction(ReportUiAction.OnBackClicked) },
            onAction = onAction,
        )
    }
}

@Composable
internal fun ReportContent(
    uiState: ReportUiState,
    onAction: (ReportUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.report_description),
            style = Large20_Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(
            modifier = modifier.height((uiState.allReportReasons.size * 36).dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                count = uiState.allReportReasons.size,
                key = { index -> uiState.allReportReasons[index].id },
            ) { index ->
                TripmateCheckBox(
                    text = stringResource(id = uiState.allReportReasons[index].textResId),
                    isSelected = uiState.selectedReportReasons.contains(uiState.allReportReasons[index]),
                    onSelectedChange = {
                        if (uiState.selectedReportReasons.contains(uiState.allReportReasons[index])) {
                            onAction(ReportUiAction.OnReportReasonDeselected(uiState.allReportReasons[index]))
                        } else {
                            onAction(ReportUiAction.OnReportReasonSelected(uiState.allReportReasons[index]))
                        }
                    },
                    iconRes = designSystemR.drawable.ic_mate_type,
                    checkedIconRes = designSystemR.drawable.ic_mate_type_checked,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        TripmateTextField(
            text = uiState.reportReasonDescription,
            onTextChange = { text -> onAction(ReportUiAction.OnReportReasonDescriptionUpdated(text)) },
            searchTextHintRes = R.string.report_reason_hint,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            multiline = true,
            maxLength = 200,
        )
        Spacer(modifier = Modifier.height(84.dp))
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TripmateOutlinedButton(
                onClick = { onAction(ReportUiAction.OnCancelClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                enabled = uiState.selectedReportReasons.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = Medium16_SemiBold,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TripmateButton(
                onClick = { onAction(ReportUiAction.OnReportClicked) },
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                enabled = uiState.selectedReportReasons.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(R.string.report),
                    style = Medium16_SemiBold,
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@DevicePreview
@Composable
private fun ReportScreenPreview() {
    TripmateTheme {
        ReportScreen(
            uiState = ReportUiState(
                selectedReportReasons = persistentListOf(
                    ReportReasonEntity(0, R.string.abuse, false),
                ),
            ),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
