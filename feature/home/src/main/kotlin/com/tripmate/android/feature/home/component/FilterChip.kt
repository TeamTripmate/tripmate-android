package com.tripmate.android.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.R

@Composable
fun FilterChip(
    filterName: String,
    onChipClick: (String) -> Unit,
    isSelected: Boolean,
) {
    Card(
        shape = RoundedCornerShape(50.dp),
        colors = CardColors(
            containerColor = if (isSelected) Primary03.copy(alpha = 0.1f) else Background02,
            contentColor = Gray002,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        border = BorderStroke(1.dp, if (isSelected) Primary01 else Gray009),
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = { onChipClick(filterName) }),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(33.dp)
                    .padding(horizontal = 13.dp, vertical = 5.dp),
            ) {
                if (filterName != "전체") {
                    Icon(
                        painter = painterResource(id = getIconResourceForFilter(filterName)),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = filterName,
                    style = Small14_Med,
                )
            }
        }
    }
}

fun getIconResourceForFilter(filterName: String): Int {
    return when (filterName) {
        "체험" -> R.drawable.ic_paint
        "문화∙예술" -> R.drawable.ic_culture
        "축제∙공연" -> R.drawable.ic_show
        "자연∙휴양" -> R.drawable.ic_tree
        "역사" -> R.drawable.ic_history
        "Trip Original" -> R.drawable.ic_course
        "숙박" -> R.drawable.ic_hotel
        "레포츠" -> R.drawable.ic_sports
        "맛집∙카페" -> R.drawable.ic_dish
        "쇼핑" -> R.drawable.ic_shopping
        else -> R.drawable.ic_mate_type_checked
    }
}

@ComponentPreview
@Composable
fun BoothFilterChipPreview() {
    TripmateTheme {
        FilterChip(
            filterName = "Filter Chip",
            onChipClick = {},
            isSelected = true,
        )
    }
}
