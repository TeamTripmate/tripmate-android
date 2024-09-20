package com.tripmate.android.feature.personalization

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.extension.externalShareForBitmap
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateOutlinedButton
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.personalization.component.MyTripStyle
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun ResultRoute(
    navigateToMain: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val systemUiController = rememberExSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    val context = LocalContext.current

    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true,
        )
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.White,
                darkIcons = !isDarkTheme,
            )
        }
    }

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToMain -> navigateToMain()
            is PersonalizationUiEvent.ShareMyTripStyle -> context.externalShareForBitmap(event.image)
            else -> {}
        }
    }

    ResultScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun ResultScreen(
    uiState: PersonalizationUiState,
    innerPadding: PaddingValues,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp.dp
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val heightPx = with(density) { 438.dp.toPx() }

    val gradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.0f to Color(0xFF9ABCFF),
            0.80f to Color(0xFFC4D8FF),
            1.0f to Color(0xFFFFFFFF),
        ),
        start = Offset(screenWidthPx / 2, 0f),
        end = Offset(screenWidthPx / 2, heightPx),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = innerPadding.calculateBottomPadding()),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Background02),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyTripStyle(
                characterName = uiState.characterName,
                characterImageRes = designSystemR.drawable.img_character_01,
                characterTypeIntro = uiState.characterTypeIntro,
                tripStyleIntro = uiState.tripStyleIntro,
                isShared = uiState.isMyTripStyleShared,
                gradient = gradient,
                modifier = Modifier.fillMaxWidth(),
                onAction = onAction,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.my_trip_style_check_description),
                style = Small14_Reg,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TripmateOutlinedButton(
                    onClick = {},
                    containerColor = Background02,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                ) {
                    Text(
                        text = stringResource(R.string.share_my_trip_style),
                        color = Primary01,
                        style = Medium16_SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TripmateButton(
                    onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.RESULT)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                ) {
                    Text(
                        text = stringResource(R.string.start_tripmate),
                        style = Medium16_SemiBold,
                    )
                }
            }
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
