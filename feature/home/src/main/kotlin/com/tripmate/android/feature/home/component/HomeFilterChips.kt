package com.tripmate.android.feature.home.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

val homeFiltersActivity = persistentListOf("전체", "Trip Original", "체험", "문화∙예술", "축제∙공연", "레포츠", "숙박", "쇼핑", "맛집∙카페")
val homeFiltersHealing = persistentListOf("전체", "Trip Original", "자연∙휴양", "문화∙예술", "역사", "숙박", "쇼핑", "맛집∙카페")

@Composable
fun HomeFilterChips(
    onChipClick: (String) -> Unit,
    selectedChips: ImmutableList<String>,
    tabIndex: Int,
    modifier: Modifier = Modifier,
) {
    val filters = if (tabIndex == 0) homeFiltersActivity else homeFiltersHealing
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        item {
            Spacer(modifier = Modifier.width(16.dp))
        }
        items(
            items = filters,
            key = { it.hashCode() },
        ) {
            FilterChip(
                filterName = it,
                onChipClick = onChipClick,
                isSelected = selectedChips.contains(it),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@ComponentPreview
@Composable
fun HomeFilterChipsPreview() {
    TripmateTheme {
        HomeFilterChips(
            onChipClick = {},
            selectedChips = persistentListOf("전체"),
            tabIndex = 0,
        )
    }
}
