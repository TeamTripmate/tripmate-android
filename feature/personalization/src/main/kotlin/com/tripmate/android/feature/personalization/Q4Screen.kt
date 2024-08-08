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
internal fun Q4Route(
    navigateToTripStyle: () -> Unit,
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

    Q4Screen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}


@Composable
fun Q4Screen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Column {
        Question(
            number = 3,
            questionText = "강원도 여행을 가기로 했다.\n여행이 일주일 남았다면?",
            answerText1 = "여행 계획을 시간단위, 분 단위로 촘촘히 세운다",
            answerText2 = "첫째날 갈 곳, 둘째날 갈 곳 등 장소정도 찾아본다",
            answerText3 = "여행 전 날 어디 갈지 생각해보기로 한다"
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
