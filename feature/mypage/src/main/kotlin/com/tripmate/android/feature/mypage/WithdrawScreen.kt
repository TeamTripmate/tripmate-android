package com.tripmate.android.feature.mypage

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
import com.tripmate.android.domain.entity.WithdrawReasonEntity
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageViewModel
import kotlinx.collections.immutable.persistentListOf
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun WithdrawRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateBack -> popBackStack()
            is MyPageUiEvent.Withdraw -> popBackStack()
            else -> {}
        }
    }

    WithdrawScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun WithdrawScreen(
    uiState: MyPageUiState,
    innerPadding: PaddingValues,
    onAction: (MyPageUiAction) -> Unit,
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
                title = stringResource(id = R.string.withdraw),
                onNavigationClick = { onAction(MyPageUiAction.OnBackClicked) },
            )
            MateRecruitContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Composable
internal fun MateRecruitContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
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
            text = stringResource(id = R.string.withdraw_description),
            style = Large20_Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(
            modifier = modifier.height((uiState.allWithdrawReasons.size * 36).dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                count = uiState.allWithdrawReasons.size,
                key = { index -> uiState.allWithdrawReasons[index].id },
            ) { index ->
                TripmateCheckBox(
                    text = stringResource(id = uiState.allWithdrawReasons[index].textResId),
                    isSelected = uiState.selectedWithdrawReasons.contains(uiState.allWithdrawReasons[index]),
                    onSelectedChange = {
                        if (uiState.selectedWithdrawReasons.contains(uiState.allWithdrawReasons[index])) {
                            onAction(MyPageUiAction.OnWithdrawReasonDeselected(uiState.allWithdrawReasons[index]))
                        } else {
                            onAction(MyPageUiAction.OnWithdrawReasonSelected(uiState.allWithdrawReasons[index]))
                        }
                    },
                    iconRes = designSystemR.drawable.ic_mate_type,
                    checkedIconRes = designSystemR.drawable.ic_mate_type_checked,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        TripmateTextField(
            text = uiState.withdrawReasonDescription,
            onTextChange = { text -> onAction(MyPageUiAction.OnWithdrawReasonDescriptionUpdated(text)) },
            searchTextHintRes = R.string.withdraw_reason_hint,
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
                onClick = { onAction(MyPageUiAction.OnRealWithdrawClicked) },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                enabled = uiState.selectedWithdrawReasons.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(R.string.real_withdraw),
                    style = Medium16_SemiBold,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TripmateButton(
                onClick = { onAction(MyPageUiAction.OnUseMoreClicked) },
                modifier = Modifier
                    .weight(2f)
                    .height(56.dp),
                contentPadding = PaddingValues(horizontal = 0.dp),
                enabled = uiState.selectedWithdrawReasons.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(R.string.use_more),
                    style = Medium16_SemiBold,
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@DevicePreview
@Composable
private fun WithdrawScreenPreview() {
    TripmateTheme {
        WithdrawScreen(
            uiState = MyPageUiState(
                selectedWithdrawReasons = persistentListOf(
                    WithdrawReasonEntity(0, R.string.no_use, false),
                ),
            ),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
