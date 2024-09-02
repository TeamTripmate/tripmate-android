package com.tripmate.android.feature.mate_recruit.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import com.tripmate.android.core.common.extension.formatToDate
import com.tripmate.android.core.common.extension.formatToTime
import com.tripmate.android.core.common.extension.parseToLocalDate
import com.tripmate.android.core.common.extension.parseToLocalTime
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray008
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Large20_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.mate_recruit.R
import com.tripmate.android.feature.mate_recruit.viewmodel.MateRecruitUiAction
import com.tripmate.android.feature.mate_recruit.viewmodel.MateRecruitUiState
import com.tripmate.android.feature.mate_recruit.viewmodel.PickerType
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleBottomSheet(
    pickerType: PickerType,
    uiState: MateRecruitUiState,
    onAction: (MateRecruitUiAction) -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedDate by remember { mutableStateOf(uiState.mateRecruitDate) }
    var selectedTime by remember { mutableStateOf(uiState.mateRecruitTime) }

    ModalBottomSheet(
        onDismissRequest = {
            onAction(MateRecruitUiAction.OnDismiss(pickerType))
        },
        sheetState = bottomSheetState,
        shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
        containerColor = Background02,
        dragHandle = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Background02)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalDivider(
                    thickness = 5.dp,
                    color = Gray008,
                    modifier = Modifier
                        .width(80.dp)
                        .clip(RoundedCornerShape(43.dp)),
                )
            }
        },
        windowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier.wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .background(Background02)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (pickerType == PickerType.DATE) {
                WheelDatePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    startDate = uiState.mateRecruitDate.parseToLocalDate(),
                    minDate = LocalDate.now(),
                    maxDate = LocalDate.of(2030, 12, 31),
                    yearsRange = IntRange(2024, 2030),
                    rowCount = 5,
                    textStyle = Large20_Mid,
                    textColor = Gray001,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        color = Gray010,
                        border = BorderStroke(2.dp, Gray010),
                    ),
                ) { snappedDate -> run { selectedDate = snappedDate.formatToDate() } }
            } else {
                WheelTimePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    startTime = uiState.mateRecruitTime.parseToLocalTime(),
                    minTime = LocalTime.now(),
                    maxTime = LocalTime.of(23, 59),
                    timeFormat = TimeFormat.AM_PM,
                    rowCount = 5,
                    textStyle = Large20_Mid,
                    textColor = Gray001,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        color = Gray010,
                        border = BorderStroke(2.dp, Gray010),
                    ),
                ) { snappedTime -> run { selectedTime = snappedTime.formatToTime() } }
            }
            Spacer(modifier = Modifier.height(26.dp))
            TripmateButton(
                onClick = {
                    if (pickerType == PickerType.DATE) {
                        onAction(MateRecruitUiAction.OnScheduleDateUpdated(selectedDate))
                    } else {
                        onAction(MateRecruitUiAction.OnScheduleTimeUpdated(selectedTime))
                    }
                    onAction(MateRecruitUiAction.OnDismiss(pickerType))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 15.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    style = Medium16_SemiBold,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDialog(
    pickerType: PickerType,
    uiState: MateRecruitUiState,
    onAction: (MateRecruitUiAction) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
) {
    var selectedDate by remember { mutableStateOf(uiState.mateRecruitDate) }
    var selectedTime by remember { mutableStateOf(uiState.mateRecruitTime) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Background02),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            if (pickerType == PickerType.DATE) {
                WheelDatePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    startDate = uiState.mateRecruitDate.parseToLocalDate(),
                    minDate = LocalDate.now(),
                    maxDate = LocalDate.of(2030, 12, 31),
                    rowCount = 5,
                    textStyle = Large20_Mid,
                    textColor = Gray001,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        color = Gray010,
                        border = BorderStroke(2.dp, Gray010),
                    ),
                ) { snappedDate -> run { selectedDate = snappedDate.formatToDate() } }
            } else {
                WheelTimePicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    startTime = uiState.mateRecruitTime.parseToLocalTime(),
                    minTime = LocalTime.now(),
                    maxTime = LocalTime.of(23, 59),
                    timeFormat = TimeFormat.AM_PM,
                    rowCount = 5,
                    textStyle = Large20_Mid,
                    textColor = Gray001,
                    selectorProperties = WheelPickerDefaults.selectorProperties(
                        enabled = true,
                        shape = RoundedCornerShape(10.dp),
                        color = Gray010,
                        border = BorderStroke(2.dp, Gray010),
                    ),
                ) { snappedTime -> run { selectedTime = snappedTime.formatToTime() } }
            }
            Spacer(modifier = Modifier.height(24.dp))
            TripmateButton(
                onClick = {
                    if (pickerType == PickerType.DATE) {
                        onAction(MateRecruitUiAction.OnScheduleDateUpdated(selectedDate))
                    } else {
                        onAction(MateRecruitUiAction.OnScheduleTimeUpdated(selectedTime))
                    }
                    onAction(MateRecruitUiAction.OnDismiss(pickerType))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 15.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    style = Medium16_SemiBold,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@ComponentPreview
@Composable
fun ScheduleBottomSheetPreview() {
    TripmateTheme {
        ScheduleBottomSheet(
            pickerType = PickerType.DATE,
            uiState = MateRecruitUiState(),
            onAction = {},
        )
    }
}

@ComponentPreview
@Composable
fun ScheduleDialogPreview() {
    TripmateTheme {
        ScheduleDialog(
            pickerType = PickerType.DATE,
            uiState = MateRecruitUiState(),
            onAction = {},
            onDismissRequest = {},
        )
    }
}
