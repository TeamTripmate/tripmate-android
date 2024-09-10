package com.tripmate.android.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateOutlinedButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Secondary02
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.TicketType
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyTripCharacterInfoViewModel

@Composable
internal fun MyTripCharacterInfoRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: MyTripCharacterInfoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateBack -> popBackStack()
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

@Suppress("UnusedParameter")
@Composable
internal fun MyTripCharacterInfoContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Background02)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.my_trip_style),
            style = Medium16_SemiBold,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = uiState.characterName,
            style = Large20_Bold,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(22.dp))
        NetworkImage(
            imgUrl = uiState.profileImgUrl,
            contentDescription = stringResource(id = R.string.profile_image),
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(70.dp)),
        )
        Spacer(modifier = Modifier.height(22.dp))
        Row {
            TicketType(
                text = uiState.type1,
                containerColor = Secondary02,
                contentColor = Primary01,
            )
            Spacer(modifier = Modifier.width(4.dp))
            TicketType(
                text = uiState.type2,
                containerColor = Secondary02,
                contentColor = Primary01,
            )
            Spacer(modifier = Modifier.width(4.dp))
            TicketType(
                text = uiState.type3,
                containerColor = Secondary02,
                contentColor = Primary01,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.character_type_intro),
                style = Medium16_SemiBold,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = uiState.characterTypeIntro,
                style = Small14_Reg,
                color = Gray002,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.trip_style_intro),
                style = Medium16_SemiBold,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = uiState.tripStyleIntro,
                style = Small14_Reg,
                color = Gray002,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.trip_location_recommend),
                style = Medium16_SemiBold,
                color = Primary01,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = uiState.tripLocationRecommend,
                style = Small14_Reg,
                color = Gray002,
            )
        }
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
            onClick = {},
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
        Spacer(modifier = Modifier.height(24.dp))
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
