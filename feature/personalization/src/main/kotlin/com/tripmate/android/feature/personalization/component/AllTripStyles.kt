package com.tripmate.android.feature.personalization.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Background03
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Primary03
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.feature.personalization.R
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AllTripStyles(
    tripStyles: ImmutableList<TripStyleEntity>,
    selectedTripStyles: ImmutableList<TripStyleEntity>,
    onAction: (PersonalizationUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.height(if (tripStyles.isEmpty()) 0.dp else (((tripStyles.size - 1) / 3 + 1) * 140).dp),
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        contentPadding = PaddingValues(bottom = 48.dp),
    ) {
        items(
            count = tripStyles.size,
            key = { index -> tripStyles[index].id },
        ) { index ->
            TripStyleItem(
                tripStyle = tripStyles[index],
                isSelected = selectedTripStyles.contains(tripStyles[index]),
                onSelectedChange = {
                    if (selectedTripStyles.contains(tripStyles[index])) {
                        onAction(PersonalizationUiAction.OnTripStyleDeselected(tripStyles[index]))
                    } else {
                        onAction(PersonalizationUiAction.OnTripStyleSelected(tripStyles[index]))
                    }
                },
            )
        }
    }
}

@Composable
fun TripStyleItem(
    tripStyle: TripStyleEntity,
    isSelected: Boolean,
    onSelectedChange: () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) Background03 else Background02)
            .border(
                width = 1.dp,
                color = if (isSelected) Primary03 else Background02,
                shape = RoundedCornerShape(8.dp),
            )
            .clickable {
                onSelectedChange()
            },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Image(
                painter = painterResource(id = tripStyle.imageResId),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = tripStyle.textResId),
                color = Gray001,
                style = Small14_SemiBold,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@ComponentPreview
@Composable
fun AllTripStylesPreview() {
    TripmateTheme {
        AllTripStyles(
            tripStyles = persistentListOf(
                TripStyleEntity(1, R.string.trip_style_1, R.drawable.img_shopping_bags, false),
                TripStyleEntity(2, R.string.trip_style_2, R.drawable.img_running_shoe, false),
                TripStyleEntity(3, R.string.trip_style_3, R.drawable.img_statue_of_liberty, false),
                TripStyleEntity(4, R.string.trip_style_4, R.drawable.img_diving_mask, false),
                TripStyleEntity(5, R.string.trip_style_5, R.drawable.img_mobile_phone, false),
                TripStyleEntity(6, R.string.trip_style_6, R.drawable.img_compass, false),
                TripStyleEntity(7, R.string.trip_style_7, R.drawable.img_backpack, false),
                TripStyleEntity(8, R.string.trip_style_8, R.drawable.img_camera_with_flash, false),
                TripStyleEntity(9, R.string.trip_style_9, R.drawable.img_framed_picture, false),
                TripStyleEntity(10, R.string.trip_style_10, R.drawable.img_pot_of_food, false),
                TripStyleEntity(11, R.string.trip_style_11, R.drawable.img_deciduous_tree, false),
                TripStyleEntity(12, R.string.trip_style_12, R.drawable.img_ferris_wheel, false),
            ),
            selectedTripStyles = persistentListOf(
                TripStyleEntity(1, R.string.trip_style_1, R.drawable.img_shopping_bags, false),
                TripStyleEntity(2, R.string.trip_style_2, R.drawable.img_running_shoe, false),
                TripStyleEntity(3, R.string.trip_style_3, R.drawable.img_statue_of_liberty, false),
            ),
            onAction = {},
        )
    }
}
