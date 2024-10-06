package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.personalization.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.CurrentScreen

@Composable
internal fun Question4Route(
    navigateToTripStyle: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToTripStyle -> {
                navigateToTripStyle()
            }

            else -> {}
        }
    }

    Question4Screen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun Question4Screen(
    uiState: PersonalizationUiState,
    innerPadding: PaddingValues,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Question(
            number = 4,
            questionText = stringResource(R.string.question4),
            selectedAnswer = uiState.question4Answer,
            answerText1 = stringResource(R.string.question4_answer1),
            answerText2 = stringResource(R.string.question4_answer2),
            answerText3 = stringResource(R.string.question4_answer3),
            onAnswerSelected = { answer ->
                onAction(
                    PersonalizationUiAction.OnQuestionAnswerSelected(
                        questionNumber = 4,
                        answer = answer,
                    ),
                )
            },
            modifier = Modifier.align(Alignment.Center),
        )
        TripmateButton(
            onClick = {
                onAction(PersonalizationUiAction.OnSelectClick(CurrentScreen.QUESTION_4))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp)
                .align(Alignment.BottomCenter),
            enabled = if (uiState.question4Answer == 0) false else true,
        ) {
            Text(
                text = stringResource(R.string.select),
                style = Medium16_SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}

@DevicePreview
@Composable
private fun Question4ScreenPreview() {
    TripmateTheme {
        Question4Screen(
            uiState = PersonalizationUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
