package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class MateRecruitPostEntity(
    val spotId: String = "",
    val title: String = "",
    val date: String = "",
    val chatLink: String = "",
    val accompanyYn: Boolean = false,
    val description: String = "",
    val gender: String = "",
    val ageRange: String = "",
    val matchingRatio: String = "",
    val hostInfo: UserInfoEntity = UserInfoEntity(),
    val reviewRanks: List<String> = emptyList(),
    val mateRecruitPostReviewList: List<TripDetailMateReviewEntity> = emptyList(),
)

enum class MateReviewType(val code: String, val reviewText: String) {
    P1("P1", "매너가 좋았어요"),
    P2("P2", "약속시간을 잘 지켰어요"),
    P3("P3", "대화가 편했어요"),
    P4("P4", "의견을 존중해줬어요"),
    P5("P5", "소통이 원활했어요"),
    P6("P6", "상황 대처능력이 좋았어요"),
}
