package com.tripmate.android.feature.personalization

import androidx.compose.foundation.background
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
import com.tripmate.android.core.designsystem.theme.Background01
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
internal fun Question1Route(
    navigateToQuestion2: () -> Unit,
    innerPadding: PaddingValues,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToQuestion2 -> {
                navigateToQuestion2()
            }

            else -> {}
        }
    }

    Question1Screen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun Question1Screen(
    uiState: PersonalizationUiState,
    innerPadding: PaddingValues,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background01)
            .padding(innerPadding),
    ) {
        Question(
            number = 1,
            questionText = stringResource(R.string.question1),
            selectedAnswer = uiState.question1Answer,
            answerText1 = stringResource(R.string.question1_answer_1),
            answerText2 = stringResource(R.string.question1_answer_2),
            onAnswerSelected = { answer ->
                onAction(
                    PersonalizationUiAction.OnQuestionAnswerSelected(
                        questionNumber = 1,
                        answer = answer,
                    ),
                )
            },
            modifier = Modifier.align(Alignment.Center),
        )
        TripmateButton(
            onClick = {
                onAction(PersonalizationUiAction.OnSelectClick(ScreenType.QUESTION_1))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 62.dp)
                .height(56.dp)
                .align(Alignment.BottomCenter),
            enabled = if (uiState.question1Answer == 0) false else true,
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
private fun Question1ScreenPreview() {
    TripmateTheme {
        Question1Screen(
            uiState = PersonalizationUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
