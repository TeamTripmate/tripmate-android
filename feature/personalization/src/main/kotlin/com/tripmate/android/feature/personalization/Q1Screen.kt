package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun Q1Route(
    navigateToQ2: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    Q1Screen()
}


@Composable
fun Q1Screen() {
    Box {
        Question(
            number = 1,
            questionText = "숙소 주최 파티에서 장기자랑하면 술값이 무료! 일 때 당신의 선택은?",
            answerText1 = "술값이 무료라고? 당연 참가!😄",
            answerText2 = "그냥 내 돈 내고 말지..."
        )
    }
}

