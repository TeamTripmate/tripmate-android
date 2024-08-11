package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType

@Composable
fun UserInfoRoute(
    navigateToResult: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToResult -> {
                navigateToResult()
            }

            else -> {}
        }
    }

    UserInfoScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}


@Composable
fun UserInfoScreen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "당신의 여행 스타일을 알려주세요.",
                style = Large20_Bold,
                color = Gray001,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            )
            Text(
                text = "최대 6개",
                style = Small14_Reg,
                color = Gray001,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            )
            Spacer(modifier = Modifier.height(18.dp))
            AllTripStyles(
                tripStyles = uiState.allTripStyles,
                onAction = onAction,
                selectedTripStyles = uiState.selectedTripStyles,
                modifier = Modifier.weight(1f),
            )
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.TRIP_STYLE)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = uiState.selectedTripStyles.isNotEmpty(),
        ) {
            Text(
                stringResource(R.string.select),
                style = Medium16_SemiBold,
            )
        }
    }
}
