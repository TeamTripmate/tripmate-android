package com.tripmate.android.mate_review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.common.ObserveAsEvents
import com.tripmate.android.core.designsystem.component.TopAppBarNavigationType
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.component.TripmateTopAppBar
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Large20_Bold
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.designsystem.theme.XSmall12_SemiBold
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.mate_review.R
import com.tripmate.android.mate_review.component.BadReviewCheckBox
import com.tripmate.android.mate_review.component.GoodReviewCheckBox
import com.tripmate.android.mate_review.viewmodel.MateReviewUiAction
import com.tripmate.android.mate_review.viewmodel.MateReviewUiEvent
import com.tripmate.android.mate_review.viewmodel.MateReviewUiState
import com.tripmate.android.mate_review.viewmodel.MateReviewViewModel

@Composable
internal fun MateReviewRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: MateReviewViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MateReviewUiEvent.NavigateBack -> popBackStack()
            is MateReviewUiEvent.Finish -> popBackStack()
            is MateReviewUiEvent.ShowToast -> {}
        }
    }

    MateReviewScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun MateReviewScreen(
    uiState: MateReviewUiState,
    innerPadding: PaddingValues,
    onAction: (MateReviewUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(id = R.string.mate_review_writing),
                onNavigationClick = { onAction(MateReviewUiAction.OnBackClicked) },
            )
            MateRecruitContent(
                uiState = uiState,
                onAction = onAction,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MateRecruitContent(
    uiState: MateReviewUiState,
    onAction: (MateReviewUiAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.mate_review_title),
            style = Large20_Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Gray010),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = uiState.mateReviewTitle,
                    style = Medium16_SemiBold,
                    color = Gray001,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = uiState.tripLocation,
                        style = XSmall12_Reg,
                        color = Gray002,
                    )
                    Text(
                        text = " ·",
                        style = XSmall12_Reg,
                        color = Gray002,
                    )
                    Text(
                        text = " " + uiState.tripDate,
                        style = XSmall12_SemiBold,
                        color = Gray002,
                    )
                    Text(
                        text = " " + uiState.tripTime,
                        style = XSmall12_SemiBold,
                        color = Gray002,
                    )
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(),
            color = Gray010,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.good_point),
            style = Medium16_SemiBold,
            color = Gray002,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.select_trip_description_keyword),
            style = XSmall12_Reg,
            color = Gray004,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow {
            uiState.allGoodReviews.forEach { goodReview ->
                GoodReviewCheckBox(
                    review = goodReview,
                    isSelected = uiState.selectedGoodReviews.contains(goodReview),
                    onSelectedChange = {
                        if (uiState.selectedGoodReviews.contains(goodReview)) {
                            onAction(MateReviewUiAction.OnGoodReviewDeselected(goodReview))
                        } else {
                            onAction(MateReviewUiAction.OnGoodReviewSelected(goodReview))
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = stringResource(id = R.string.bad_point),
            style = Medium16_SemiBold,
            color = Gray002,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.select_trip_description_keyword),
            style = XSmall12_Reg,
            color = Gray004,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow {
            uiState.allBadReviews.forEach { badReview ->
                BadReviewCheckBox(
                    review = badReview,
                    isSelected = uiState.selectedBadReviews.contains(badReview),
                    onSelectedChange = {
                        if (uiState.selectedBadReviews.contains(badReview)) {
                            onAction(MateReviewUiAction.OnBadReviewDeselected(badReview))
                        } else {
                            onAction(MateReviewUiAction.OnBadReviewSelected(badReview))
                        }
                    },
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(74.dp))
        TripmateButton(
            onClick = { onAction(MateReviewUiAction.OnDoneClicked) },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 18.dp),
            enabled = uiState.selectedGoodReviews.isNotEmpty() && uiState.selectedBadReviews.isNotEmpty(),
        ) {
            Text(
                text = stringResource(R.string.done),
                style = Medium16_SemiBold,
            )
        }
        Spacer(modifier = Modifier.height(55.dp))
    }
}

@DevicePreview
@Composable
private fun MateRecruitScreenPreview() {
    TripmateTheme {
        MateReviewScreen(
            uiState = MateReviewUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
