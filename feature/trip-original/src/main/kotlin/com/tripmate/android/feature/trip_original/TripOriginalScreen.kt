package com.tripmate.android.feature.trip_original

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.feature.trip_original.component.TripOriginalDialog
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.trip_original.viewmodel.TripOriginalUiAction
import com.tripmate.android.feature.trip_original.viewmodel.TripOriginalUiEvent
import com.tripmate.android.feature.trip_original.viewmodel.TripOriginalUiState
import com.tripmate.android.feature.trip_original.viewmodel.TripOriginalViewModel
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun TripOriginalRoute(
    innerPadding: PaddingValues,
    viewModel: TripOriginalViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            else -> {}
        }
    }
    TripOriginalScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun TripOriginalScreen(
    uiState: TripOriginalUiState,
    innerPadding: PaddingValues,
    onAction: (TripOriginalUiAction) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White)
            .verticalScroll(scrollState),
    ) {
        val context = LocalContext.current

        val resourceName = "trip_original_${uiState.spotId}"
        val resourceId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

        if (resourceId != 0) { // 유효한 리소스 ID인지 확인
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = null,
            )
        } else {
            Image(
                painter = painterResource(id = designSystemR.drawable.trip_original_0),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = "Trip Original Icon",
            )
        }

        TripmateButton(
            onClick = {
                onAction.invoke(TripOriginalUiAction.OnMateClicked)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .height(56.dp),
        ) {
            Text(
                text = stringResource(designSystemR.string.trip_mate_request),
                style = Medium16_SemiBold,
            )
        }
    }

    if (uiState.isShowPopup) {
        TripOriginalDialog(
            onDismissRequest = { onAction(TripOriginalUiAction.OnBackClicked) },
            onAction = onAction,
        )
    }
}

@DevicePreview
@Composable
internal fun TripOriginalPreview() {
    TripmateTheme {
        TripOriginalScreen(
            innerPadding = PaddingValues(0.dp),
            uiState = TripOriginalUiState(),
            onAction = {},
        )
    }
}
