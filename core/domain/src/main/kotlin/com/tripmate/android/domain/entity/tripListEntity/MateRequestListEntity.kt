package com.tripmate.android.domain.entity.tripListEntity

import androidx.compose.runtime.Stable
import com.tripmate.android.domain.entity.TicketEntity
import com.tripmate.android.domain.entity.TripDetailStyleEntity

/**
 * 신청자 목록 화면의 티켓들의 리스트 관련 엔티티입니다
 *
 */
@Stable
data class MateRequestListEntity(
    val mateRequestList: List<TicketEntity> = emptyList(),
)
