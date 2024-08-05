package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
fun Q4Route(
    viewModel: PersonalizationViewModel,
) {
    Q4Screen()
}


@Composable
fun Q4Screen() {
    Box {
        Question(
            number = 4,
            questionText = "'강원도 여행을 가기로 했다.\n여행이 일주일 남았다면?",
            answerText1 = "여행 계획을 시간단위, 분 단위로 촘촘히 세운다",
            answerText2 = "첫째날 갈 곳, 둘째날 갈 곳 등 장소정도 찾아본다",
            answerText3 = "여행 전 날 어디 갈지 생각해보기로 한다",
        )
    }
}
