package com.tripmate.android.domain.entity.tripListEntity

import androidx.compose.runtime.Stable
import com.tripmate.android.domain.entity.TicketEntity

/**
 * 이미 종료된 동행관련 아이템에 사용될 엔티티입니다
 *
 */
@Stable
data class MateRequestFinishedEntity(
    val mateTitle: String,
    // 동행 모집글 타이틀입니다
    val mateDate: String,
    // 동행 날짜입니다. String안주셔도 프론트단에서 처리하면 되니 편하게 주셔도 됩니다
    val userId: String,
    // 유저아이디 형식을 몰라서 String으로 했는데, userId 이용해서 해당유저에 후기를 작성할 수 있게 파라미터로 전달드릴예정입니다
)
