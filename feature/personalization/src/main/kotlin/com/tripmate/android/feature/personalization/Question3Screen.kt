package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import com.tripmate.android.feature.personalization.viewmodel.ScreenType

@Composable
internal fun Question3Route(
    navigateToQuestion4: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToQuestion4 -> {
                navigateToQuestion4()
            }

            else -> {}
        }
    }

    Question3Screen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
fun Question3Screen(
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
            number = 3,
            questionText = stringResource(R.string.question3),
            selectedAnswer = uiState.question3Answer,
            answerText1 = stringResource(R.string.question3_answer_1),
            answerText2 = stringResource(R.string.question3_answer_2),
            onAnswerSelected = { answer ->
                onAction(
                    PersonalizationUiAction.OnQuestionAnswerSelected(
                        questionNumber = 3,
                        answer = answer,
                    ),
                )
            },
            modifier = Modifier.align(Alignment.Center),
        )
        TripmateButton(
            onClick = {
                onAction(PersonalizationUiAction.OnSelectClick(ScreenType.QUESTION_3))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 62.dp)
                .height(56.dp)
                .align(Alignment.BottomCenter),
            enabled = if (uiState.question3Answer == 0) false else true,
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
fun Question3ScreenPreview() {
    TripmateTheme {
        Question3Screen(
            uiState = PersonalizationUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
