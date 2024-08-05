package com.tripmate.android.core.ui.component

import android.app.DownloadManager.Query
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Background03
import com.tripmate.android.core.designsystem.theme.Large20_SemiBold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.XLarge26_Bold

@Composable
fun Question(
    number: Int,
    questionText: String,
    answerText1: String,
    answerText2: String,
    answerText3: String = "",
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background01),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Q$number",
            style = XLarge26_Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = questionText,
            style = Large20_SemiBold,
        )
        AnswerBox(
            answerText = answerText1,
            isChecked = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp, vertical = 5.dp),
        )
        AnswerBox(
            answerText = answerText2,
            isChecked = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 31.dp, vertical = 5.dp),
        )
        if (answerText3.isNotEmpty()) {
            Box {
                Text(
                    text = questionText,
                    style = Medium16_SemiBold,
                )
            }
        }
    }
}

@Composable
fun AnswerBox(
    answerText: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (isChecked) Background03 else Background02)
            .border(
                width = 2.dp,
                color = if (isChecked) Primary03 else Background02,
                shape = RoundedCornerShape(8.dp),
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 31.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = answerText,
                style = Medium16_SemiBold,
            )
            Icon(
                imageVector = if (isChecked) ImageVector.vectorResource(R.drawable.ic_checked) else ImageVector.vectorResource(R.drawable.ic_unchecked),
                contentDescription = "Check Icon",
                tint = Color.Unspecified,
            )
        }
    }
}

@ComponentPreview
@Composable
fun QuestionPreview() {
    Question(
        number = 1,
        questionText = "What is your favorite color?",
        answerText1 = "Red",
        answerText2 = "Blue",
    )
}

@ComponentPreview
@Composable
fun AnswerBoxUncheckedPreview() {
    AnswerBox(
        answerText = "Red",
        isChecked = false,
        modifier = Modifier.fillMaxWidth(),
    )
}

@ComponentPreview
@Composable
fun AnswerBoxCheckedPreview() {
    AnswerBox(
        answerText = "Blue",
        isChecked = true,
        modifier = Modifier.fillMaxWidth(),
    )
}
