package com.tripmate.android.domain.entity

import androidx.compose.runtime.Stable

@Stable
data class TripDetailEntity(
    val spotId: Long = 0L,
    val title: String = "",
    val description: String = "",
    val phoneNumber: String = "",
    val imageUrl: String = "",
    val location: Location = Location(),
    val category: String = "",
    val tripDetailFee: String = "",
    val tripRecommendStyleEntity: List<TripDetailStyleEntity> = emptyList(),
    val tripDetailMateRecruit: List<TripDetailMateRecruitEntity> = emptyList(),
    val tripDetailMateReviewAdvantage: List<String> = emptyList(),
    val tripDetailMateReviewList: List<TripDetailMateReviewEntity> = emptyList(),
)

data class Location(
    val longitude: String = "",
    val latitude: String = "",
    val address: Address = Address(),
)

data class Address(
    val address1: String = "",
    val address2: String = "",
)
