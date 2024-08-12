package com.tripmate.android.feature.personalization

import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Large20_SemiBold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.personalization.component.BirthRateTextField
import com.tripmate.android.feature.personalization.component.GenderSelectionBox
import com.tripmate.android.feature.personalization.component.UnderAgeDialog
import com.tripmate.android.feature.personalization.viewmodel.Gender
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType
import kotlin.system.exitProcess

@Composable
fun UserInfoRoute(
    navigateToResult: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToResult -> {
                navigateToResult()
            }

            is PersonalizationUiEvent.Finish -> {
                exitProcess(0)
            }

            else -> {}
        }
    }

    UserInfoScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
fun UserInfoScreen(
    uiState: PersonalizationUiState,
    innerPadding: PaddingValues,
    onAction: (PersonalizationUiAction) -> Unit,
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

        if (uiState.isUnderAgeDialogVisible) {
            UnderAgeDialog(
                onConfirmClick = { onAction(PersonalizationUiAction.OnUnderAgeDialogConfirmClick) },
            )
        }
    }
}

@Composable
fun UserInfoContent(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(id = R.string.user_info_title),
                style = Large20_Bold,
                color = Gray001,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.user_info_description),
                style = Small14_Reg,
                color = Gray004,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))
            GenderSelectionBox(
                selectedGender = uiState.selectedGender,
                onSelectedChange = { gender -> onAction(PersonalizationUiAction.OnGenderSelected(gender = gender)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(52.dp))
            Box(modifier = modifier) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.input_birth_date),
                        style = Medium16_SemiBold,
                        color = Gray001,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BirthRateTextField(
                        birthDateText = uiState.birthDate,
                        updateBirthDateText = { text -> onAction(PersonalizationUiAction.OnBirthDateUpdated(text)) },
                        clearText = { onAction(PersonalizationUiAction.OnClearIconClicked) },
                        errorText = uiState.birthDateErrorText?.asString(context),
                    )
                }
            }
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.USER_INFO)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = uiState.selectedGender != Gender.NOT_SPECIFIED && uiState.birthDate.length == 6 && uiState.birthDateErrorText == null,
        ) {
            Text(
                text = stringResource(R.string.select),
                style = Medium16_SemiBold,
            )
        }
    }
}

@DevicePreview
@Composable
fun UserInfoScreenPreview() {
    TripmateTheme {
        UserInfoScreen(
            uiState = PersonalizationUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
