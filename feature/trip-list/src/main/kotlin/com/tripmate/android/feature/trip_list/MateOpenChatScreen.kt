package com.tripmate.android.feature.trip_list

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_list.component.MyTripStyle
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiAction
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiEvent
import com.tripmate.android.feature.trip_list.viewmodel.TripListUiState
import com.tripmate.android.feature.trip_list.viewmodel.TripListViewModel
import com.tripmate.android.feature.triplist.R
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun MateOpenChatRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: TripListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val systemUiController = rememberExSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()

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
            is TripListUiEvent.NavigateBack -> popBackStack()
            is TripListUiEvent.NavigateToKakaoOpenChat -> {
                openKakaoOpenChat(context, event.openChatUrl)
            }
            else -> {}
        }
    }

    MateOpenChatScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

private fun openKakaoOpenChat(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(context, intent, null)
}

@Composable
internal fun MateOpenChatScreen(
    innerPadding: PaddingValues,
    uiState: TripListUiState,
    onAction: (TripListUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = modifier
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
                modifier = Modifier.fillMaxWidth(),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TripmateButton(
                    onClick = {
                        onAction(TripListUiAction.OnMateOpenChatClicked)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                ) {
                    Text(
                        text = stringResource(R.string.navigate_to_mate_open_chat),
                        style = Medium16_SemiBold,
                    )
                }
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

@DevicePreview
@Composable
private fun MyTripCharacterInfoPreview() {
    TripmateTheme {
        MateOpenChatScreen(
            innerPadding = PaddingValues(),
            uiState = TripListUiState(),
            onAction = {},
        )
    }
}
