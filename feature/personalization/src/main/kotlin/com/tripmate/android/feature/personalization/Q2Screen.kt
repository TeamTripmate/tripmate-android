package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
fun Q2Route(
    viewModel: PersonalizationViewModel,
) {
    Q2Screen()
}


@Composable
fun Q2Screen() {
    Box {
        Question(
            number = 2,
            questionText = "설레는 여향길, 기차를 타고 가는데 나른함에 잠이 쏟아진다.\n이때 드는 생각은?",
            answerText1 = "잠든 사이에 내 짐 훔쳐가면 어떡해? 잡을 수 있을까?",
            answerText2 = "아무 걱정하지 않고 냅다 잠이 든다"
        )
    }
}
