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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType

@Composable
internal fun ResultRoute(
    navigateToMain: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToMain -> {
                navigateToMain()
            }

            else -> {}
        }
    }

    ResultScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Suppress("UnusedParameter")
@Composable
internal fun ResultScreen(
    uiState: PersonalizationUiState,
    innerPadding: PaddingValues,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = "나의 여행스타일은",
                style = Small14_Reg,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "춤추는 아기판다",
                style = Large20_Bold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(52.dp))
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.RESULT)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
        ) {
            Text(
                stringResource(R.string.start_tripmate),
                style = Medium16_SemiBold,
            )
        }
    }
}

@DevicePreview
@Composable
private fun ResultScreenPreview() {
    TripmateTheme {
        ResultScreen(
            uiState = PersonalizationUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
