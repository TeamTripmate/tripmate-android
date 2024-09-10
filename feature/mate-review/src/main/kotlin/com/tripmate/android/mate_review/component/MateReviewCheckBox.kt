package com.tripmate.android.mate_review.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Orange
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Mid
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.domain.entity.BadReviewEntity
import com.tripmate.android.domain.entity.GoodReviewEntity
import com.tripmate.android.feature.mate_review.R

@Composable
fun GoodReviewCheckBox(
    review: GoodReviewEntity,
    isSelected: Boolean,
    onSelectedChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) Primary01 else Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .background(
                color = if (isSelected) Primary01.copy(alpha = 0.05f) else Background02,
                shape = RoundedCornerShape(4.dp),
            )
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable { onSelectedChange() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        Text(
            text = stringResource(id = review.textResId),
            color = if (isSelected) Primary01 else Gray002,
            style = Small14_Mid,
        )
    }
}

@Composable
fun BadReviewCheckBox(
    review: BadReviewEntity,
    isSelected: Boolean,
    onSelectedChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .wrapContentWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) Orange else Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .background(
                color = if (isSelected) Orange.copy(alpha = 0.05f) else Background02,
                shape = RoundedCornerShape(4.dp),
            )
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable { onSelectedChange() }
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        Text(
            text = stringResource(id = review.textResId),
            color = if (isSelected) Orange else Gray002,
            style = Small14_Mid,
        )
    }
}

@ComponentPreview
@Composable
fun GoodReviewCheckBoxPreview() {
    TripmateTheme {
        GoodReviewCheckBox(
            review = GoodReviewEntity(id = 1, textResId = R.string.good_manner, isSelected = false),
            isSelected = true,
            onSelectedChange = {},
        )
    }
}

@ComponentPreview
@Composable
fun BadReviewCheckBoxPreview() {
    TripmateTheme {
        BadReviewCheckBox(
            review = BadReviewEntity(id = 1, textResId = R.string.bad_manner, isSelected = false),
            isSelected = true,
            onSelectedChange = {},
        )
    }
}
