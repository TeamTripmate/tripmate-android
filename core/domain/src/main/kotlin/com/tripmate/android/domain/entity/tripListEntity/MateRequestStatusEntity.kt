package com.tripmate.android.domain.entity.tripListEntity

import androidx.compose.runtime.Stable

/**
 * 여행 목록의 내가 모집하는 동행 관련 엔티티 입니다
 *
 */
@Stable
data class MateRequestStatusEntity(
    val status: String,
    // 신청완료, 수락완료, 동행시작, 동행 종료, 동행 거절
    val mateTitle: String,
    // 동행 모집글 타이틀입니다
    val mateDate: String,
    // 동행 날짜입니다. String안주셔도 프론트단에서 처리하면 되니 편하게 주셔도 됩니다

)
