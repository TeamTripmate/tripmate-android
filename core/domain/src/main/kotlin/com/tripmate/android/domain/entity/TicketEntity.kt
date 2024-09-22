package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

/**
 * 티켓 관련 엔티티입니다
 * 3개의 해시태그는 개인화 화면에서 유저가 고른 3개의 특징입니다
 * 위의 해시태그와 개인화로 생성된 6가지 동물의 이름이 characterName을 만들고
 * 동물이름을 바탕으로 charaterImgUrl이 나옵니다(동물그림이)
 * 유저 아이디도 함께 보내주시고, 여행 동행 수락을 할때 파라미터로 해당값을 같이 보내드릴 예정입니다
 *
 */
@Stable
data class TicketEntity(
    val characterName: String,
    val hashtag1: String,
    val hashtag2: String,
    val hashtag3: String,
    val characterImgUrl: String,
    val userId: Int,
)
