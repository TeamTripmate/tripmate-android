package com.tripmate.android.domain.entity.tripListEntity

import androidx.compose.runtime.Stable
import com.tripmate.android.domain.entity.TicketEntity

/**
 * 여행 목록의 내가 신청한 동행 관련 엔티티 입니다
 *
 */
@Stable
data class MateLeaderStatusEntity(
    val status: String,
    // 신청자보기, 매칭완료, 동행시작, 동행 종료
    val mateTitle: String,
    // 동행 모집글 타이틀입니다
    val mateDate: String,
    // 동행 날짜입니다. String으로 안주셔도 프론트단에서 처리하면 되니 편하게 주셔도 됩니다
    val leaderInfo: TicketEntity,
    // 동행 모집글 작성자의 정보입니다. TicketEntity에 있는 정보와 일치해 TicketEntity를 사용하였습니다
    val mateKakaoUrl: String,
    // 동행 모집글의 카카오톡 오픈톡 주소입니다
)
