package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun Q2Route(
    navigateToQ3: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToQ3 -> {
                navigateToQ3()
            }

            else -> {}
        }
    }

    Q2Screen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
fun Q2Screen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Question(
                number = 2,
                questionText = "설레는 여향길, 기차를 타고 가는데\n나른함에 잠이 쏟아진다.\n이때 드는 생각은?",
                answerText1 = "잠든 사이에 내 짐 훔쳐가면 어떡해? 잡을 수 있을까?",
                answerText2 = "아무 걱정하지 않고 냅다 잠이 든다"
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
}
