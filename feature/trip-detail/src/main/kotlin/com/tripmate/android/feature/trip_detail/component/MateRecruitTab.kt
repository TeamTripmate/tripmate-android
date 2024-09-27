package com.tripmate.android.feature.trip_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tripmate.android.core.designsystem.ComponentPreview
import com.tripmate.android.core.designsystem.component.ProfileImage
import com.tripmate.android.core.designsystem.component.TripmateButton
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.Gray002
import com.tripmate.android.core.designsystem.theme.Gray003
import com.tripmate.android.core.designsystem.theme.Gray005
import com.tripmate.android.core.designsystem.theme.Gray006
import com.tripmate.android.core.designsystem.theme.Gray009
import com.tripmate.android.core.designsystem.theme.Gray010
import com.tripmate.android.core.designsystem.theme.Medium16_Mid
import com.tripmate.android.core.designsystem.theme.Medium16_SemiBold
import com.tripmate.android.core.designsystem.theme.Primary01
import com.tripmate.android.core.designsystem.theme.Small14_Reg
import com.tripmate.android.core.designsystem.theme.Small14_SemiBold
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.designsystem.theme.XSmall10_Mid
import com.tripmate.android.core.designsystem.theme.XSmall10_Reg
import com.tripmate.android.core.designsystem.theme.XSmall12_Reg
import com.tripmate.android.core.designsystem.theme.XXSmall8_Reg
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity
import com.tripmate.android.feature.trip_detail.R
import com.tripmate.android.feature.trip_detail.viewmodel.TripDetailUiAction
import com.tripmate.android.core.designsystem.R as designSystemR

@Composable
fun MateRecruitTab(
    tripDetail: TripDetailEntity,
    onAction: (TripDetailUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TripDetailMateRecruit(onAction)

        TripDetailMateRecruitList(tripDetail, onAction)
    }
}

@Composable
fun TripDetailMateRecruit(
    onAction: (TripDetailUiAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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
            text = stringResource(id = designSystemR.string.trip_mate_recruit_title),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    TripmateButton(
        onClick = {
            onAction(TripDetailUiAction.OnClickMateRecruit)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 24.dp)
            .height(56.dp),
    ) {
        Text(
            text = stringResource(designSystemR.string.trip_mate_recruit_text),
            style = Medium16_SemiBold,
        )
    }

    HorizontalDivider(
        thickness = 1.dp,
        color = Gray009,
        modifier = Modifier.height(1.dp),
    )
}

@Composable
fun TripDetailMateRecruitList(
    tripDetail: TripDetailEntity,
    onAction: (TripDetailUiAction) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = designSystemR.drawable.ic_mate_recruit_list),
            contentDescription = "introduce icon",
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = designSystemR.string.trip_mate_recruit_list),
            color = Gray001,
            style = Medium16_Mid,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        tripDetail.tripDetailMateRecruit.forEach { item ->
            TripDetailMateRecruitItem(item, onAction)
        }
    }
}

@Composable
fun TripDetailMateRecruitItem(
    tripDetailMateRecruitEntity: TripDetailMateRecruitEntity,
    onAction: (TripDetailUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onAction(TripDetailUiAction.OnClickMateReviewPost(tripDetailMateRecruitEntity.companionId))
            }
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
            ProfileImage(
                imgUrl = tripDetailMateRecruitEntity.profileImage,
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(22.dp)),
                contentDescription = "Profile Image Icon",
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = tripDetailMateRecruitEntity.mateName,
                        color = Gray001,
                        style = Small14_SemiBold,
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = stringResource(R.string.user_report),
                        color = Gray005,
                        style = XXSmall8_Reg,
                        modifier = Modifier.clickable {
                            onAction(TripDetailUiAction.OnClickReport)
                        },
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = tripDetailMateRecruitEntity.mateStyleName,
                        color = Gray003,
                        style = XSmall12_Reg,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = tripDetailMateRecruitEntity.mateMatchingRatio + "% 일치",
                        color = Primary01,
                        style = XSmall12_Reg,
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.article_report),
                        color = Gray005,
                        style = XSmall10_Reg,
                        modifier = Modifier.clickable {
                            onAction(TripDetailUiAction.OnClickReport)
                        },
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    items(tripDetailMateRecruitEntity.mateStyleType) { item ->
                        TripDetailMateStyleTypeItem(item)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = tripDetailMateRecruitEntity.mateRecruitTitle,
            color = Gray001,
            style = Medium16_Mid,
        )

        Spacer(modifier = Modifier.height(4.dp))

        val genderString =
            if (tripDetailMateRecruitEntity.gender.isNotEmpty())
                if (tripDetailMateRecruitEntity.ageRange.isNotEmpty()) tripDetailMateRecruitEntity.gender + "만∙"
                else tripDetailMateRecruitEntity.gender
            else ""
        Text(
            text = genderString + tripDetailMateRecruitEntity.ageRange,
            color = Gray006,
            style = Small14_Reg,
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
        textAlign = TextAlign.Center,
        color = Gray002,
        style = XSmall10_Mid,
    )
}

@ComponentPreview
@Composable
fun MateRecruitTabPreview() {
    TripmateTheme {
        MateRecruitTab(
            tripDetail = TripDetailEntity(),
            onAction = {},
        )
    }
}

@ComponentPreview
@Composable
fun TripDetailMateRecruitItemPreview() {
    TripmateTheme {
        TripDetailMateRecruitItem(
            tripDetailMateRecruitEntity = TripDetailMateRecruitEntity(
                companionId = 1,
                profileImage = "",
                mateName = "김철수",
                mateStyleName = "힙합",
                mateMatchingRatio = "80",
                mateStyleType = listOf("힙합", "팝", "클래식"),
                mateRecruitTitle = "힙합을 좋아하는 친구를 찾아요",
                mateRecruitDescription = "힙합을 좋아하는 친구를 찾아요",
                mateRecruitSubDescription = "힙합을 좋아하는 친구를 찾아요",
                mateRecruitAddress = "서울시 강남구",
                date = "2021.10.01",
                mateRecruitDate = "2021.10.01",
            ),
            onAction = {},
        )
    }
}
