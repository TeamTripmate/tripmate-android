package com.tripmate.android.feature.mate_recruit_post.viewmodel

import com.tripmate.android.core.designsystem.R
import com.tripmate.android.domain.entity.MateRecruitPostEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity
import com.tripmate.android.domain.entity.TripDetailMateReviewEntity
import kotlinx.collections.immutable.persistentListOf

data class MateRecruitPostUiState(
    var mateRecruitPostEntity: MateRecruitPostEntity = getTestMateRecruitContent(),
)

fun getTestMateRecruitContent(): MateRecruitPostEntity {
    // Todo 서버 작업 완료 후 제거 필요
    return MateRecruitPostEntity(
        mateRecruitPostReviewAdvantage = persistentListOf(
            "뷰가 좋아요",
            "사진이 잘 나와요",
            "친구와 함께하기 좋아요",
        ),
        mateRecruit = TripDetailMateRecruitEntity(
            imageResId = R.drawable.ic_sample_character,
            mateName = "춤추는 심바",
            mateStyleName = "인생이 즐거운 쇼핑 비버",
            mateMatchingRatio = "70% 일치",
            mateStyleType = persistentListOf("맛집탐험형", "액티비티형", "쇼핑형"),
            mateRecruitTitle = "서피비치 인근에서 같이 식사할 사람 구해요!",
            mateRecruitDescription = "저는 올 해 처음 낙산사 템플스테이가서 서핑을 해봤는데, 너무 재밌었어요. 그래서 또 가고싶은데 같이 가실 분 있나요? 서핑하고 근처에 피자 한 판 먹고 헤어져요.저는 올 해 처음 낙산사 템플스테이가서 서핑을 해봤는데, 너무 재밌었어요. 그래서 또 가고싶은데 같이 가실 분 있나요? 서핑하고 근처에 피자 한 판 먹고 헤어져요.저는 올 해 처음 낙산사 템플스테이가서 서핑을 해봤는데, 너무 재밌었어요. 그래서 또 가고싶은데 같이 가실 분 있나요? 서핑하고 근처에 피자 한 판 먹고 헤",
            mateRecruitSubDescription = "식사동행∙여자만∙20대",
            mateRecruitAddress = "양양서핑학교",
            mateRecruitDate = "8.24(일) 오전11:00",
        ),
        mateRecruitPostReviewList = persistentListOf(
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
