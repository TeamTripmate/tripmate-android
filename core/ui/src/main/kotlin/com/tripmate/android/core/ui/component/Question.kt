package com.tripmate.android.core.ui.component

import android.app.DownloadManager.Query
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
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
    modifier: Modifier = Modifier,
    answerText3: String = "",
) {
    var checkedAnswer by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .background(color = Background01)
            .padding(horizontal = 31.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Q$number",
                    style = XLarge26_Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = questionText,
                    style = Large20_SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnswerBox(
                    answerText = answerText1,
                    isChecked = checkedAnswer == 1,
                    onCheckedChange = { checkedAnswer = if (checkedAnswer == 1) 0 else 1 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                )
                AnswerBox(
                    answerText = answerText2,
                    isChecked = checkedAnswer == 2,
                    onCheckedChange = { checkedAnswer = if (checkedAnswer == 2) 0 else 2 },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                )
                if (answerText3.isNotEmpty()) {
                    AnswerBox(
                        answerText = answerText3,
                        isChecked = checkedAnswer == 3,
                        onCheckedChange = { checkedAnswer = if (checkedAnswer == 3) 0 else 3 },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f, fill = false))
        }
    }
}

@Composable
fun AnswerBox(
    answerText: String,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
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
            )
            .clickable (onClick = onCheckedChange),
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
        onCheckedChange = {},
        modifier = Modifier.fillMaxWidth(),
    )
}

@ComponentPreview
@Composable
fun AnswerBoxCheckedPreview() {
    AnswerBox(
        answerText = "Blue",
        isChecked = true,
        onCheckedChange = {},
        modifier = Modifier.fillMaxWidth(),
    )
}
