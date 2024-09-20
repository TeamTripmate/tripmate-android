package com.tripmate.android.feature.mypage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.utils.dpToPx
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.Ticket
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.MyPageViewModel

@Composable
internal fun MyPageRoute(
    innerPadding: PaddingValues,
    navigateToMyTripCharacterInfo: (Long) -> Unit,
    navigateToMyPick: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToWithdraw: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.NavigateToMyTripCharacterInfo -> navigateToMyTripCharacterInfo(event.characterId)
            is MyPageUiEvent.NavigateToMyPick -> navigateToMyPick()
            is MyPageUiEvent.NavigateToLogin -> navigateToLogin()
            is MyPageUiEvent.NavigateToWithdraw -> navigateToWithdraw()
            is MyPageUiEvent.ShowToast -> {
                Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
            }

            is MyPageUiEvent.NavigateToMain -> navigateToMain()
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
            .background(Background02)
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(id = R.string.my_page),
                containerColor = Background02,
            )
            MyPageContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Composable
internal fun MyPageContent(
    uiState: MyPageUiState,
    onAction: (MyPageUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val widthPx = dpToPx(328.dp)
    val heightPx = dpToPx(162.dp)

    val gradient = Brush.linearGradient(
        colorStops = arrayOf(
            0.0f to Color(0xFFE3A8FF),
            0.49f to Color(0xFF8BA4FF),
            1.0f to Color(0xFF8DD6FF),
        ),
        start = Offset(0f, 0f),
        end = Offset(widthPx, heightPx),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NetworkImage(
                imgUrl = uiState.profileImgUrl,
                contentDescription = stringResource(id = R.string.profile_image),
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(22.dp)),
            )
            Spacer(modifier = Modifier.width(9.dp))
            Text(
                text = uiState.nickname,
                style = Medium16_SemiBold,
                color = Gray001,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .background(Gray010, shape = RoundedCornerShape(8.dp))
                .clickable {
                    onAction(MyPageUiAction.OnMyPickClicked)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(id = R.string.my_pick),
                style = Medium16_SemiBold,
                color = Gray002,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right_24),
                contentDescription = stringResource(id = R.string.my_pick),
                tint = Gray002,
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))
        Ticket(
            characterName = uiState.characterName,
            type1 = uiState.type1,
            type2 = uiState.type2,
            type3 = uiState.type3,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(gradient, shape = RoundedCornerShape(12.dp))
                .clickable {
                    onAction(MyPageUiAction.OnTicketClicked(characterId = uiState.characterId))
                },
        )
        Spacer(modifier = Modifier.height(32.dp))
        VerticalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            thickness = 1.dp,
            color = Gray009,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAction(MyPageUiAction.OnLogoutClicked)
                }
                .padding(horizontal = 16.dp),
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
                .clickable {
                    onAction(MyPageUiAction.OnWithdrawClicked)
                }
                .padding(horizontal = 16.dp),
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
