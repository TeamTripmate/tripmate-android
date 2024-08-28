package com.tripmate.android.feature.recruit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTextField
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
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
        UserInfoContent(
            uiState = uiState,
            onAction = onAction,
        )
    }
}

@Composable
fun UserInfoContent(
    uiState: MateRecruitUiState,
    onAction: (MateRecruitUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_title),
                style = Large20_Bold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_description),
                style = Small14_Reg,
                color = Gray004,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_title),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.birthDate,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnBirthDateUpdated(text)) },
                searchTextHintRes = R.string.mate_recruit_title_hint,
                clearText = { onAction(MateRecruitUiAction.OnClearIconClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                errorText = uiState.birthDateErrorText?.asString(context),
            )
            Text(
                text = stringResource(id = R.string.trip_location),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.birthDate,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnBirthDateUpdated(text)) },
                searchTextHintRes = R.string.trip_location_hint,
                clearText = { onAction(MateRecruitUiAction.OnClearIconClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                errorText = uiState.birthDateErrorText?.asString(context),
            )
            Text(
                text = stringResource(id = R.string.schedule),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.mate_recruit_title),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            TripmateTextField(
                text = uiState.birthDate,
                onTextChange = { text -> onAction(MateRecruitUiAction.OnBirthDateUpdated(text)) },
                searchTextHintRes = R.string.mate_recruit_title_hint,
                clearText = { onAction(MateRecruitUiAction.OnClearIconClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                errorText = uiState.birthDateErrorText?.asString(context),
            )
            Text(
                text = stringResource(id = R.string.mate_type),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.setting_gender_age),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.open_kakao_link),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        TripmateButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = true,
        ) {
            Text(
                text = stringResource(R.string.done),
                style = Medium16_SemiBold,
            )
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
