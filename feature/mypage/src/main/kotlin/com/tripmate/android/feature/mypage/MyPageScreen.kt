package com.tripmate.android.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.common.extension.noRippleClickable
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.Ticket
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mypage.component.VerticalDottedDivider
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiAction
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiEvent
import com.tripmate.android.feature.mypage.viewmodel.MyPageUiState
import com.tripmate.android.feature.mypage.viewmodel.MyPageViewModel

@Composable
internal fun MyPageRoute(
    innerPadding: PaddingValues,
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MyPageUiEvent.Logout -> {}
            is MyPageUiEvent.Withdraw -> {}
            is MyPageUiEvent.ShowToast -> {}
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
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.None,
                title = stringResource(id = R.string.my_page),
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
                .height(72.dp)
                .clip(RoundedCornerShape(36.dp)),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = uiState.nickname,
            style = Medium16_SemiBold,
            color = Gray001,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Ticket),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                NetworkImage(
                    imgUrl = uiState.characterImgUrl,
                    contentDescription = stringResource(id = R.string.profile_image),
                    modifier = Modifier
                        .height(48.dp)
                        .clip(RoundedCornerShape(24.dp)),
                )
                Spacer(modifier = Modifier.width(16.dp))
                VerticalDottedDivider(
                    modifier = Modifier.fillMaxHeight(),
                    thickness = 2.dp,
                    color = White,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = uiState.personalizationResult,
                        style = Medium16_SemiBold,
                        color = Gray001,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Background02)
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                        ) {
                            Text(
                                text = uiState.type1,
                                style = XSmall12_Reg,
                                color = Primary03,
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Background02)
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                        ) {
                            Text(
                                text = uiState.type2,
                                style = XSmall12_Reg,
                                color = Primary03,
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Background02)
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                        ) {
                            Text(
                                text = uiState.type3,
                                style = XSmall12_Reg,
                                color = Primary03,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable { },
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
                .noRippleClickable { },
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
                .noRippleClickable { },
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
fun MyPagePreview() {
    TripmateTheme {
        MyPageScreen(
            innerPadding = PaddingValues(),
            uiState = MyPageUiState(),
            onAction = {},
        )
    }
}
