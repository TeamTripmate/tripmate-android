package com.tripmate.android.feature.personalization

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.NetworkImage
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.TripStyleEntity
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiAction
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiEvent
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel
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
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        TripStyleContent(
            uiState = uiState,
            onAction = onAction,
            innerPadding = innerPadding,
        )
    }
}

@Composable
fun TripStyleContent(
    uiState: PersonalizationUiState,
    onAction: (PersonalizationUiAction) -> Unit,
    innerPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp),
        ) {
            Text(
                text = "당신의 여행 스타일을 알려주세요.",
                style = Medium16_SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            )
            Text(
                text = "최대 6개",
                style = Medium16_SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            )
            Spacer(modifier = Modifier.height(18.dp))
            AllTripStyles(
                tripStyles = uiState.allTripStyles,
                onAction = onAction,
                selectedTripStyles = uiState.selectedTripStyles,
                modifier = Modifier.weight(1f),
            )
        }
        TripmateButton(
            onClick = { onAction(PersonalizationUiAction.OnSelectClick) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 20.dp),
            contentPadding = PaddingValues(vertical = 17.dp),
            enabled = uiState.selectedTripStyles.isNotEmpty(),
        ) {
            Text(
                text = "선택하기",
                style = Medium16_SemiBold,
            )
        }
    }
}

@Composable
fun AllTripStyles(
    tripStyles: ImmutableList<TripStyleEntity>,
    onAction: (PersonalizationUiAction) -> Unit,
    selectedTripStyles: ImmutableList<TripStyleEntity>,
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .height(130.dp)
            .width(120.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            NetworkImage(
                imgUrl = tripStyle.image,
                contentDescription = "TripStyle Image",
                modifier = Modifier
                    .size(40.dp)
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
fun IntroScreenPreview() {
    TripmateTheme {
        TripStyleScreen(
            uiState = PersonalizationUiState(
                allTripStyles = persistentListOf(),
            ),
            onAction = {},
        )
    }
}
