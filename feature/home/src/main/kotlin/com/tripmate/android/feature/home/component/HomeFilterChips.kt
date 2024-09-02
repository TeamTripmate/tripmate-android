package com.tripmate.android.feature.home.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

val homeFiltersActivity = persistentListOf("전체", "체험", "문화예술", "축제∙공연", "코스", "레포츠", "숙박", "쇼핑", "맛집∙카페")
val homeFiltersHealing = persistentListOf("전체", "자연∙휴양", "문화예술", "역사", "숙박", "쇼핑", "맛집∙카페")

@Composable
fun HomeFilterChips(
    onChipClick: (String) -> Unit,
    selectedChips: ImmutableList<String>,
) {
    LazyRow {
        items(
            items = homeFiltersActivity,
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
    HomeFilterChips(
        onChipClick = {},
        selectedChips = persistentListOf("전체"),
    )
}
