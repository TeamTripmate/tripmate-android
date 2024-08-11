package com.tripmate.android.feature.personalization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.R
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
import com.tripmate.android.feature.personalization.viewmodel.ScreenType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun TripStyleRoute(
    navigateToUserInfo: () -> Unit,
    viewModel: PersonalizationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is PersonalizationUiEvent.NavigateToUserInfo -> {
                navigateToUserInfo()
            }

            else -> {}
        }
    }

    TripStyleScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
fun TripStyleScreen(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background01),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "당신의 여행 스타일을\n알려주세요.",
                style = Large20_Bold,
                color = Gray001,
                modifier = Modifier.padding(top = 70.dp),
            )
            Text(
                text = "최대 3개",
                style = Small14_Reg,
                color = Gray001,
                modifier = Modifier.padding(top = 18.dp),
            )
            Spacer(modifier = Modifier.height(48.dp))
            AllTripStyles(
                tripStyles = uiState.allTripStyles,
                selectedTripStyles = uiState.selectedTripStyles,
                onAction = onAction,
                modifier = Modifier.weight(1f),
            )
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick(ScreenType.TRIP_STYLE)) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = uiState.selectedTripStyles.isNotEmpty(),
        ) {
            Text(
                stringResource(R.string.select),
                style = Medium16_SemiBold,
            )
        }
    }
}

@Composable
fun AllTripStyles(
    tripStyles: ImmutableList<TripStyleEntity>,
    selectedTripStyles: ImmutableList<TripStyleEntity>,
    onAction: (PersonalizationUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(if (tripStyles.isEmpty()) 0.dp else (((tripStyles.size - 1) / 3 + 1) * 140).dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 48.dp),
    ) {
        items(
            count = tripStyles.size,
            key = { index -> tripStyles[index].id },
        ) { index ->
            TripStyleItem(
                tripStyle = tripStyles[index],
                onAction = onAction,
            )
        }
    }
}

@Composable
fun TripStyleItem(
    tripStyle: TripStyleEntity,
    onAction: (PersonalizationUiAction) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Background02),
        border = BorderStroke(1.dp, Background02),
        modifier = Modifier
            .height(130.dp)
            .width(100.dp)
            .clickable {
                onAction(PersonalizationUiAction.OnTripStyleSelected(tripStyle))
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
                text = tripStyle.text,
                color = Gray001,
                style = Medium16_SemiBold,
                fontSize = 15.sp,
            )
        }
    }
}

@DevicePreview
@Composable
fun TripStyleScreenPreview() {
    TripmateTheme {
        TripStyleScreen(
            uiState = PersonalizationUiState(
                allTripStyles = persistentListOf(),
            ),
            onAction = {},
        )
    }
}
