package com.tripmate.android.feature.personalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun Q1Route(
    navigateToQ2: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToQ2 -> {
                navigateToQ2()
            }

            else -> {}
        }
    }

    Q1Screen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}


@Composable
fun Q1Screen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background01),
    ) {
        Question(
            number = 1,
            questionText = "숙소 주최 파티에서 장기자랑하면\n술값이 무료! 일 때 당신의 선택은?",
            answerText1 = "술값이 무료라고? 당연 참가!😄",
            answerText2 = "그냥 내 돈 내고 말지...",
            modifier = Modifier.align(Alignment.Center),
        )
        TripmateButton(
            onClick = {
                onAction(PersonalizationUiAction.OnSelectClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 62.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter),
        ) {
            Text("선택하기")
        }
    }
}

