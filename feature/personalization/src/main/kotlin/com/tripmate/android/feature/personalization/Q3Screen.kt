package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.tripmate.android.core.ui.component.Question
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun Q3Route(
    navigateToQ4: () -> Unit,
    viewModel: PersonalizationViewModel,
) {
    Q3Screen()
}


@Composable
fun Q3Screen() {
    Box {
        Question(
            number = 3,
            questionText = "'나 우울해서 떡볶이 시켜먹었어ㅜㅜ'친구가 문자를 보냈을 때 당신의 답장은?",
            answerText1 = "무슨 일 있었어? 나한테 고민 이야기 해봐 다 들어줄게!",
            answerText2 = "떡볶이를 먹었어? 너 다이어트 한다며. 떡볶이가 다이어트할 때 최악의 음식이래..."
        )
    }
}
