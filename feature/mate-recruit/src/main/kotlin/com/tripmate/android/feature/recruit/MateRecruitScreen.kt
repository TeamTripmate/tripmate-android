package com.tripmate.android.feature.recruit

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTextField
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitUiAction
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitUiState
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitViewModel

@Composable
fun MateRecruitRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: MateRecruitViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            else -> popBackStack()
        }
    }

    MateRecruitScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
fun MateRecruitScreen(
    uiState: MateRecruitUiState,
    innerPadding: PaddingValues,
    onAction: (MateRecruitUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(id = R.string.mate_writing),
            )
            MateRecruitContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@Composable
fun MateRecruitContent(
    uiState: MateRecruitUiState,
    onAction: (MateRecruitUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_description),
                style = Large20_Bold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.trip_location),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Gray010)

            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterStart),
                ) {
                    Text(
                        text = uiState.tripLocation,
                        style = Small14_SemiBold,
                        color = Gray001,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = uiState.tripLocationAddress,
                        style = XSmall12_Reg,
                        color = Gray004,
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_title),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.mateRecruitTitle,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnMateRecruitTitleUpdated(text)) },
                searchTextHintRes = R.string.mate_recruit_title_hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                maxLength = 25,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.schedule),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_content),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.mateRecruitContent,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnMateRecruitContentUpdated(text)) },
                searchTextHintRes = R.string.mate_recruit_content_hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                maxLength = 200,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.mate_type),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.setting_gender_age),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.open_kakao_link),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.open_kakao_link_description),
                style = XSmall12_Reg,
                color = Gray004,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.openKakaoLink,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnOpenKakaoLinkUpdated(text)) },
                searchTextHintRes = R.string.open_kakao_link_hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
            )
            Spacer(modifier = Modifier.height(40.dp))
            TripmateButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 18.dp),
                enabled = true,
            ) {
                Text(
                    text = stringResource(R.string.done),
                    style = Medium16_SemiBold,
                )
            }
            Spacer(modifier = Modifier.height(55.dp))
        }
    }
}

@DevicePreview
@Composable
fun MateRecruitScreenPreview() {
    TripmateTheme {
        MateRecruitScreen(
            uiState = MateRecruitUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
