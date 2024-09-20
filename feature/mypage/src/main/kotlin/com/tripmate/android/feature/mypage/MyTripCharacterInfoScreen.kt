package com.tripmate.android.feature.mypage

import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.extension.externalShareForBitmap
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateOutlinedButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.MyTripStyle
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.MyTripCharacterInfoViewModel
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun MyTripCharacterInfoRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: MyTripCharacterInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateBack -> popBackStack()
            is MyPageUiEvent.ShareMyTripStyle -> context.externalShareForBitmap(event.image)
            is MyPageUiEvent.NavigateToPersonalization -> navigateToPersonalization()
            else -> {}
        }
    }

    MyTripCharacterInfoScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun MyTripCharacterInfoScreen(
    innerPadding: PaddingValues,
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Close,
                title = stringResource(id = R.string.my_trip_character_info),
                onNavigationClick = {
                    onAction(MyPageUiAction.OnBackClicked)
                },
            )
            MyTripCharacterInfoContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Composable
internal fun MyTripCharacterInfoContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
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
            isShared = uiState.isMyTripStyleShared,
            gradient = gradient,
            onAction = onAction,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            Text(
//                text = stringResource(id = R.string.trip_location_recommend),
//                style = Medium16_SemiBold,
//                color = Primary01,
//            )
//            Spacer(modifier = Modifier.height(18.dp))
//            Text(
//                text = uiState.tripLocationRecommend,
//                style = Small14_Reg,
//                color = Gray002,
//            )
            Spacer(modifier = Modifier.height(32.dp))
            TripmateOutlinedButton(
                onClick = {
                    onAction(MyPageUiAction.OnCharacterTypeReselectClicked)
                },
                containerColor = Background02,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text(
                    text = stringResource(R.string.reselect_character_type),
                    color = Primary01,
                    style = Medium16_SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TripmateOutlinedButton(
                onClick = {
                    onAction(MyPageUiAction.OnShareMyTripStyleClicked(true))
                },
                containerColor = Background02,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            ) {
                Text(
                    text = stringResource(R.string.share_my_trip_style),
                    color = Primary01,
                    style = Medium16_SemiBold,
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun MyTripCharacterInfoPreview() {
    TripmateTheme {
        MyTripCharacterInfoScreen(
            innerPadding = PaddingValues(),
            uiState = MyPageUiState(),
            onAction = {},
        )
    }
}
