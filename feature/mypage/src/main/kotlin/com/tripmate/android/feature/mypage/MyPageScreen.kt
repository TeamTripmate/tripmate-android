package com.tripmate.android.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.designsystem.theme.XSmall12_SemiBold
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
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = uiState.nickname,
            style = Large20_Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Gray010),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = uiState.personalizationResult,
                    style = Medium16_SemiBold,
                    color = Gray001,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = uiState.type1,
                        style = XSmall12_Reg,
                        color = Gray002,
                    )
                    Text(
                        text = " " + uiState.type2,
                        style = XSmall12_SemiBold,
                        color = Gray002,
                    )
                    Text(
                        text = " " + uiState.type3,
                        style = XSmall12_SemiBold,
                        color = Gray002,
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.my_pick),
            style = Medium16_SemiBold,
            color = Gray002,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.logout),
            style = Medium16_SemiBold,
            color = Gray002,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.withdraw),
            style = XSmall12_Reg,
            color = Gray004,
        )
    }
}
