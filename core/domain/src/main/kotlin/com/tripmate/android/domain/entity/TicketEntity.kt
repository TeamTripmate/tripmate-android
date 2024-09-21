package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TicketEntity(
    val characterName: String,
    val hashtag1: String,
    val hashtag2: String,
    val hashtag3: String,
    val characterImgUrl: String,
    val ticketId: Int,
)
