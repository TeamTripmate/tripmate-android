package com.tripmate.android.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.tripmate.android.core.common.extension.noRippleClickable
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.Ticket
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.mypage.MyPageViewModel

@Composable
internal fun MyPageRoute(
    innerPadding: PaddingValues,
    navigateToMyTripCharacterInfo: (Long) -> Unit,
    navigateToMyPick: () -> Unit,
    navigateToWithdraw: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateToMyTripCharacterInfo -> navigateToMyTripCharacterInfo(event.characterId)
            is MyPageUiEvent.NavigateToMyPick -> navigateToMyPick()
            is MyPageUiEvent.Logout -> {}
            is MyPageUiEvent.NavigateToWithdraw -> navigateToWithdraw()
            is MyPageUiEvent.Withdraw -> {}
            is MyPageUiEvent.ShowToast -> {}
            else -> {}
        }
    }

    MyPageScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun MyPageScreen(
    innerPadding: PaddingValues,
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background01)
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(id = R.string.my_page),
                containerColor = Background01,
            )
            MyPageContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Suppress("UnusedParameter")
@Composable
internal fun MyPageContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        NetworkImage(
            imgUrl = uiState.profileImgUrl,
            contentDescription = stringResource(id = R.string.profile_image),
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(36.dp)),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = uiState.nickname,
            style = Medium16_SemiBold,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Ticket(
            uiState = uiState,
            modifier = Modifier.clickable {
                onAction(MyPageUiAction.OnTicketClicked(characterId = uiState.characterId))
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    onAction(MyPageUiAction.OnMyPickClicked)
                },
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = R.string.my_pick),
                style = Medium16_SemiBold,
                color = Gray002,
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    onAction(MyPageUiAction.OnLogoutClicked)
                },
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = R.string.logout),
                style = Medium16_SemiBold,
                color = Gray002,
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    onAction(MyPageUiAction.OnWithdrawClicked)
                },
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = R.string.withdraw),
                style = Medium16_SemiBold,
                color = Gray002,
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@DevicePreview
@Composable
private fun MyPagePreview() {
    TripmateTheme {
        MyPageScreen(
            innerPadding = PaddingValues(),
            uiState = MyPageUiState(),
            onAction = {},
        )
    }
}
