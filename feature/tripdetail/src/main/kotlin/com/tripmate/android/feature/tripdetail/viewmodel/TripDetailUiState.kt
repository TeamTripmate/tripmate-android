package com.tripmate.android.feature.tripdetail.viewmodel

import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.Address
import com.tripmate.android.domain.entity.Location
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity
import com.tripmate.android.domain.entity.TripDetailStyleEntity
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class TripDetailUiState(
    val tabs: ImmutableList<String> = persistentListOf("상세정보", "동행모집", "후기"),
    val selectedTabIndex: Int = 0,
    val tripDetail: TripDetailEntity = getTestTripDetail(),
)

fun getTestTripDetail(): TripDetailEntity {
    // Todo 서버 작업 완료 후 제거
    return TripDetailEntity(
        title = "서피 비치",
        description = "Quad eius ea nihilism quasi militia consectetur naus repudiandae aut.",
        phoneNumber = "010-1234-1234",
        location = Location(
            latitude = "37.5",
            longitude = "127.0",
            address = Address(
                address1 = "강원 양양군 현복면",
                address2 = "강원 양양군 현북면 하조대해안길 119",
            ),
        ),
        category = "EXPERIENCE",
        tripDetailFee = "10000원",
        tripRecommendStyleEntity = persistentListOf(
            TripDetailStyleEntity(1, "자유로운 영혼의 댄싱 여우", R.drawable.ic_sample_character),
            TripDetailStyleEntity(2, "자유로운 영혼의 댄싱 여우", R.drawable.ic_sample_character),
            TripDetailStyleEntity(3, "자유로운 영혼의 댄싱 여우", R.drawable.ic_sample_character),
            TripDetailStyleEntity(4, "자유로운 영혼의 댄싱 여우", R.drawable.ic_sample_character),
        ),
        tripDetailMateRecruit = persistentListOf(
            TripDetailMateRecruitEntity(
                imageResId = R.drawable.ic_sample_character,
                mateName = "춤추는 심바",
                mateStyleName = "인생이 즐거운 쇼핑 비버",
                mateMatchingRatio = "70% 일치",
                mateStyleType = persistentListOf("맛집탐험형", "액티비티형", "쇼핑형"),
                mateRecruitTitle = "서피비치 인근에서 같이 식사할 사람 구해요!",
                mateRecruitDescription = "식사동행∙여자만∙20대",
            ),
            TripDetailMateRecruitEntity(
                imageResId = R.drawable.ic_sample_character,
                mateName = "춤추는 심바",
                mateStyleName = "인생이 즐거운 쇼핑 비버",
                mateMatchingRatio = "70% 일치",
                mateStyleType = persistentListOf("맛집탐험형", "액티비티형", "쇼핑형"),
                mateRecruitTitle = "8/24일 서피비치에서 서핑타고 간맥할 사람 구해요",
                mateRecruitDescription = "식사동행∙여자만∙20대",
            ),
            TripDetailMateRecruitEntity(
                imageResId = R.drawable.ic_sample_character,
                mateName = "춤추는 심바",
                mateStyleName = "인생이 즐거운 쇼핑 비버",
                mateMatchingRatio = "70% 일치",
                mateStyleType = persistentListOf("맛집탐험형", "액티비티형", "쇼핑형"),
                mateRecruitTitle = "양양에서 가장 재밌는 남자 2명이랑 노실 분~^^",
                mateRecruitDescription = "식사동행∙여자만∙20대",
            ),
        ),
        tripDetailMateReviewAdvantage = persistentListOf(
            "뷰가 좋아요",
            "사진이 잘 나와요",
            "친구와 함께하기 좋아요",
        ),
        tripDetailMateReviewList = persistentListOf(
            TripDetailMateReviewEntity(
                imageResId = R.drawable.ic_sample_character,
                mateName = "춤추는 심바",
                mateStyleName = "쇼핑을 즐기는 비버",
                mateReviewDate = "24.08.03",
                imageReviewUrl = "https://picsum.photos/36",
                mateReviewDescription = "아짱더워요 최고기온30도 근데서핑 너무 재밌어요~아짱더워요 최고기온30도 근데서핑 너무재밌어요~.\n" +
                    "동행 없이 가기에는 좀 후미진 곳에 있어요. 그래서저는요말이",
                mateReviewType = persistentListOf(
                    "뷰가 좋아요",
                    "사진이 잘 나와요",
                    "친구와 함께하기 좋아요",
                ),
            ),
            TripDetailMateReviewEntity(
                imageResId = R.drawable.ic_sample_character,
                mateName = "춤추는 심바",
                mateStyleName = "쇼핑을 즐기는 비버",
                mateReviewDate = "24.08.03",
                imageReviewUrl = "https://picsum.photos/36",
                mateReviewDescription = "아짱더워요 최고기온30도 근데서핑 너무 재밌어요~아짱더워요 최고기온30도 근데서핑 너무재밌어요~.\n" +
                    "동행 없이 가기에는 좀 후미진 곳에 있어요. 그래서저는요말이",
                mateReviewType = persistentListOf(
                    "뷰가 좋아요",
                    "사진이 잘 나와요",
                    "친구와 함께하기 좋아요",
                ),
            ),
        ),
    )
}
