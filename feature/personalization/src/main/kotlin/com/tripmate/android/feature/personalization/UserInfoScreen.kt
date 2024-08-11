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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Large20_SemiBold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.personalization.component.BirthRateTextField
import com.tripmate.android.feature.personalization.component.GenderSelectionBox
import com.tripmate.android.feature.personalization.viewmodel.Gender
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType

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

            is PersonalizationUiEvent.ShowToast -> {
                Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = stringResource(id = R.string.user_info_title),
                style = Large20_SemiBold,
                color = Gray001,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(52.dp))
            GenderSelectionBox(
                selectedGender = uiState.selectedGender,
                onSelectedChange = { gender -> onAction(PersonalizationUiAction.OnGenderSelected(gender = gender)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            )
            Spacer(modifier = Modifier.height(52.dp))
            BirthRateTextField(
                birthDateText = uiState.birthDate,
                updateBirthDateText = { text -> onAction(PersonalizationUiAction.OnBirthDateUpdated(text)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            )
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.USER_INFO)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = uiState.selectedGender != Gender.NOT_SPECIFIED && uiState.birthDate.length == 6,
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
