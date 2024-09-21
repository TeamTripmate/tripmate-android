package com.tripmate.android.feature.mate_recruit_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray004
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.MateReview
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Med
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall10_Mid
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity
import com.tripmate.android.feature.mate_recruit_post.viewmodel.MateRecruitPostUiAction
import com.tripmate.android.feature.mate_recruit_post.viewmodel.MateRecruitPostUiEvent
import com.tripmate.android.feature.mate_recruit_post.viewmodel.MateRecruitPostUiState
import com.tripmate.android.feature.mate_recruit_post.viewmodel.MateRecruitPostViewModel
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
internal fun MateRecruitPostRoute(
    innerPadding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: MateRecruitPostViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is MateRecruitPostUiEvent.NavigateBack -> popBackStack()
            is MateRecruitPostUiEvent.Finish -> popBackStack()
        }
    }

    MateRecruitPostScreen(
        uiState = uiState,
        innerPadding = innerPadding,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun MateRecruitPostScreen(
    uiState: MateRecruitPostUiState,
    innerPadding: PaddingValues,
    onAction: (MateRecruitPostUiAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            TripmateTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(id = R.string.mate_recruit_post_title),
                onNavigationClick = { onAction(MateRecruitPostUiAction.OnBackClicked) },
            )

            MateRecruitPostContent(
                mateRecruit = uiState.mateRecruitPostEntity.mateRecruit,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Gray009,
                modifier = Modifier.height(1.dp),
            )

            MateRecruitRequest(
                uiState = uiState,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Gray009,
                modifier = Modifier.height(1.dp),
            )

            MateRecruitPostReview(
                mateRecruitPostEntity = uiState.mateRecruitPostEntity,
            )
        }

        if (uiState.mateRecruitPostEntity.isAvailableMateRequest) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(top = 20.dp, bottom = 40.dp)
                    .align(Alignment.BottomCenter),
            ) {
                TripmateButton(
                    onClick = {
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                        .height(56.dp),
                ) {
                    Text(
                        text = stringResource(R.string.mate_recruit_post_request_button_title),
                        style = Medium16_SemiBold,
                    )
                }
            }
        }
    }
}

@Composable
fun MateRecruitPostContent(
    mateRecruit: TripDetailMateRecruitEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = mateRecruit.imageResId),
                contentDescription = "Profile Image Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = mateRecruit.mateName,
                    color = Gray001,
                    style = Small14_SemiBold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = mateRecruit.mateStyleName,
                        color = Gray003,
                        style = XSmall12_Reg,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = mateRecruit.mateMatchingRatio,
                        color = Primary01,
                        style = XSmall12_Reg,
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    items(mateRecruit.mateStyleType) { item ->
                        TripDetailMateStyleTypeItem(item)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = mateRecruit.mateRecruitTitle,
            color = Gray001,
            style = Medium16_Mid,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Text(
                text = mateRecruit.mateRecruitAddress + "·",
                color = Gray002,
                style = XSmall12_Reg,
            )

            Text(
                text = mateRecruit.mateRecruitDate,
                color = Gray001,
                style = XSmall12_Reg,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mateRecruit.mateRecruitDescription,
            color = Gray002,
            style = Small14_Reg,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = mateRecruit.mateRecruitSubDescription,
            color = Gray006,
            style = Small14_Reg,
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun MateRecruitRequest(
    uiState: MateRecruitPostUiState,
) {
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = designSystemR.drawable.ic_introduce),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.mate_recruit_post_open_kakao_url),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    TripmateButton(
        onClick = {
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            .height(56.dp),
        enabled = uiState.mateRecruitPostEntity.isAvailableMateRequest.not(),
    ) {
        Text(
            text = stringResource(designSystemR.string.trip_mate_recruit_text),
            style = Medium16_SemiBold,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = stringResource(id = R.string.mate_recruit_post_url_description),
        color = Primary01,
        style = Small14_Med,
    )

    Spacer(modifier = Modifier.height(32.dp))

    HorizontalDivider(
        thickness = 1.dp,
        color = Gray009,
        modifier = Modifier.height(1.dp),
    )
}

@Composable
fun MateRecruitPostReview(
    mateRecruitPostEntity: MateRecruitPostEntity,
) {
    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = designSystemR.drawable.ic_introduce),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.mate_recruit_post_advantage_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    val advantageTextAndColor: List<Triple<Int, Color, Color>> = listOf(
        Triple(com.tripmate.android.core.designsystem.R.string.trip_mate_review_advantage_first, Primary01, Gray001),
        Triple(com.tripmate.android.core.designsystem.R.string.trip_mate_review_advantage_second, Gray003, Gray003),
        Triple(com.tripmate.android.core.designsystem.R.string.trip_mate_review_advantage_third, Gray005, Gray005),
    )

    Spacer(modifier = Modifier.height(12.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                width = 1.dp,
                color = Gray009,
                shape = RoundedCornerShape(4.dp),
            )
            .background(MateReview)
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        mateRecruitPostEntity.mateRecruitPostReviewAdvantage.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
            ) {
                Text(
                    text = stringResource(advantageTextAndColor[index].first),
                    color = advantageTextAndColor[index].second,
                    style = Medium16_Mid,
                )

                Spacer(modifier = Modifier.width(14.dp))

                Text(
                    text = item,
                    color = advantageTextAndColor[index].third,
                    style = Medium16_Mid,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    HorizontalDivider(
        thickness = 1.dp,
        color = Gray009,
        modifier = Modifier.height(1.dp),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        mateRecruitPostEntity.mateRecruitPostReviewList.forEach { item ->
            MateReviewItem(item)
        }
    }
}

@Composable
fun MateReviewItem(
    tripDetailMateReviewEntity: TripDetailMateReviewEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = tripDetailMateReviewEntity.imageResId),
                contentDescription = "Profile Image Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = tripDetailMateReviewEntity.mateName,
                    color = Gray001,
                    style = Small14_SemiBold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = tripDetailMateReviewEntity.mateStyleName,
                    color = Gray003,
                    style = XSmall12_Reg,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = tripDetailMateReviewEntity.mateReviewDate,
                color = Gray005,
                style = XSmall12_Reg,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = tripDetailMateReviewEntity.mateReviewDescription,
            color = Gray006,
            style = Small14_Reg,
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            items(tripDetailMateReviewEntity.mateReviewType) { item ->
                TripDetailMateStyleTypeItem(item)
            }
        }

        Spacer(modifier = Modifier.height(34.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = Gray009,
            modifier = Modifier.height(1.dp),
        )
    }
}

@Composable
fun TripDetailMateStyleTypeItem(
    mateStyleTypeItem: String,
) {
    Text(
        modifier = Modifier
            .background(color = Gray010)
            .padding(2.dp),
        text = mateStyleTypeItem,
        color = Gray004,
        style = XSmall10_Mid,
    )
}

@DevicePreview
@Composable
private fun MateRecruitScreenPreview() {
    TripmateTheme {
        MateRecruitPostScreen(
            uiState = MateRecruitPostUiState(),
            innerPadding = PaddingValues(),
            onAction = {},
        )
    }
}
