package com.tripmate.android.feature.recruit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTextField
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray007
import com.tripmate.android.core.designsystem.theme.Gray008
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mate_recruit.R
import com.tripmate.android.feature.recruit.component.MateRecruitCheckBox
import com.tripmate.android.feature.recruit.component.ScheduleDialog
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitUiAction
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitUiEvent
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitUiState
import com.tripmate.android.feature.recruit.viewmodel.MateRecruitViewModel
import com.tripmate.android.feature.recruit.viewmodel.MateType
import com.tripmate.android.feature.recruit.viewmodel.PickerType

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
            is MateRecruitUiEvent.NavigateBack -> popBackStack()
            is MateRecruitUiEvent.Finish -> popBackStack()
            is MateRecruitUiEvent.ShowToast -> {}
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
                onNavigationClick = { onAction(MateRecruitUiAction.OnBackClicked) },
            )
            MateRecruitContent(
                uiState = uiState,
                onAction = onAction,
            )
        }

        if (uiState.isDatePickerVisible) {
            ScheduleDialog(
                pickerType = PickerType.DATE,
                uiState = uiState,
                onAction = onAction,
                onDismissRequest = {},
            )
        }

        if (uiState.isTimePickerVisible) {
            ScheduleDialog(
                pickerType = PickerType.TIME,
                uiState = uiState,
                onAction = onAction,
                onDismissRequest = {},
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
                    .background(Gray010),
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
                    Spacer(modifier = Modifier.height(4.dp))
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
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Gray008, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp)
                        .clickable { onAction(MateRecruitUiAction.OnScheduleDateClicked) },
                ) {
                    Text(
                        text = uiState.mateRecruitDate,
                        style = Small14_SemiBold,
                        color = if (uiState.isMateRecruitDateUpdated) Gray001 else Gray007,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Gray008, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .padding(vertical = 16.dp, horizontal = 12.dp)
                        .clickable { onAction(MateRecruitUiAction.OnScheduleTimeClicked) },
                ) {
                    Text(
                        text = uiState.mateRecruitTime,
                        style = Small14_SemiBold,
                        color = if (uiState.isMateRecruitTimeUpdated) Gray001 else Gray007,
                    )
                }
            }
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
                    .height(200.dp),
                multiline = true,
                maxLength = 200,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(id = R.string.mate_type),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                MateRecruitCheckBox(
                    text = stringResource(R.string.similar_mate_type),
                    isSelected = uiState.selectedMateType == MateType.SIMILAR,
                    onSelectedChange = { onAction(MateRecruitUiAction.OnMateTypeSelected(MateType.SIMILAR)) },
                    iconRes = R.drawable.ic_mate_type,
                    checkedIconRes = R.drawable.ic_mate_type_checked,
                )
                Spacer(modifier = Modifier.height(10.dp))
                MateRecruitCheckBox(
                    text = stringResource(R.string.all_mate_type),
                    isSelected = uiState.selectedMateType == MateType.ALL,
                    onSelectedChange = { onAction(MateRecruitUiAction.OnMateTypeSelected(MateType.ALL)) },
                    iconRes = R.drawable.ic_mate_type,
                    checkedIconRes = R.drawable.ic_mate_type_checked,
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(id = R.string.setting_gender_age),
                style = Medium16_SemiBold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = modifier.height((uiState.allGenderAgeGroups.size * 32).dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(
                    count = uiState.allGenderAgeGroups.size,
                    key = { index -> uiState.allGenderAgeGroups[index].id },
                ) { index ->
                    MateRecruitCheckBox(
                        text = stringResource(id = uiState.allGenderAgeGroups[index].textResId),
                        isSelected = uiState.selectedGenderAgeGroups.contains(uiState.allGenderAgeGroups[index]),
                        onSelectedChange = {
                            if (uiState.selectedGenderAgeGroups.contains(uiState.allGenderAgeGroups[index])) {
                                onAction(MateRecruitUiAction.OnGenderAgeGroupDeselected(uiState.allGenderAgeGroups[index]))
                            } else {
                                onAction(MateRecruitUiAction.OnGenderAgeGroupSelected(uiState.allGenderAgeGroups[index]))
                            }
                        },
                        iconRes = R.drawable.ic_mate_setting,
                        checkedIconRes = R.drawable.ic_mate_setting_checked,
                    )
                }
            }
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
                onClick = { onAction(MateRecruitUiAction.OnDoneClicked) },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 18.dp),
                enabled = uiState.mateRecruitTitle.isNotEmpty() &&
                    uiState.mateRecruitContent.isNotEmpty() &&
                    uiState.selectedGenderAgeGroups.isNotEmpty() &&
                    uiState.openKakaoLink.isNotEmpty(),
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
