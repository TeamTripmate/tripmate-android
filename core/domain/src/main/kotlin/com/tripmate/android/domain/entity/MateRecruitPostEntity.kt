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
    val requestYn: Boolean = false,
)

enum class MateReviewType(val code: String, val reviewText: String) {
    P1("P1", "매너가 좋았어요"),
    P2("P2", "약속시간을 잘 지켰어요"),
    P3("P3", "대화가 편했어요"),
    P4("P4", "의견을 존중해줬어요"),
    P5("P5", "소통이 원활했어요"),
    P6("P6", "상황 대처능력이 좋았어요"),
    N1("N1", "배려가 부족했어요"),
    N2("N2", "약속시간을 지키지 않았어요"),
    N3("N3", "수다스러웠어요"),
    N4("N4", "농담이 지나쳤어요"),
    N5("N5", "정해진 일정을 지키지 않았어요"),
    N6("N6", "자기 주장이 강했어요"),
    N7("N7", "소통이 어려웠어요"),
}
