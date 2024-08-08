package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun Q3Route(
    navigateToQ4: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToQ4 -> {
                navigateToQ4()
            }

            else -> {}
        }
    }

    Q3Screen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}


@Composable
fun Q3Screen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Column {
        Question(
            number = 3,
            questionText = "'나 우울해서 떡볶이 시켜먹었어\nㅜㅜ'친구가 문자를 보냈을 때\n당신의 답장은?",
            answerText1 = "무슨 일 있었어? 나한테 고민 이야기 해봐 다 들어줄게!",
            answerText2 = "떡볶이를 먹었어? 너 다이어트 한다며. 떡볶이가 다이어트할 때 최악의 음식이래..."
        )
        TripmateButton(
            onClick = {
                onAction(PersonalizationUiAction.OnSelectClick)
            },
        ) {
            Text("선택하기")
        }
    }
}
