package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.entity.Address
import com.tripmate.android.domain.entity.Location
import com.tripmate.android.domain.entity.TripDetailEntity
import com.tripmate.android.domain.entity.TripDetailMateRecruitEntity
import com.tripmate.android.domain.entity.TripDetailStyleEntity
import com.tripmate.android.domain.repository.TripDetailRepository
import javax.inject.Inject

internal class TripDetailRepositoryImpl @Inject constructor(
    private val service: TripmateService,
) : TripDetailRepository {
    override suspend fun getTripDetail(spotId: String): Result<TripDetailEntity> = runSuspendCatching {
        val tripDetailInfo = service.getTripDetailInfo(spotId).data

        TripDetailEntity(
            title = tripDetailInfo.title,
            description = tripDetailInfo.description,
            phoneNumber = tripDetailInfo.phoneNumber,
            location = Location(
                latitude = tripDetailInfo.location.latitude,
                longitude = tripDetailInfo.location.longitude,
                address = Address(
                    address1 = tripDetailInfo.location.address.address1,
                ),
            ),
            imageUrl = tripDetailInfo.imageUrl.replace("http:", "https:"),
            category = tripDetailInfo.spotType,
//            tripDetailFee = "10000원",
            tripRecommendStyleEntity = arrayListOf<TripDetailStyleEntity>().apply {
                tripDetailInfo.recommendedStyles.forEach { recommendedStyles ->
                    this.add(
                        TripDetailStyleEntity(
                            recommendedStyles.characterName,
                            recommendedStyles.characterType,
                        ),
                    )
                }
            },
            tripDetailMateRecruit = arrayListOf<TripDetailMateRecruitEntity>().apply {
                tripDetailInfo.companionRecruits.forEach { item ->

                    this.add(
                        TripDetailMateRecruitEntity(
                            companionId = item.companionId,
                            mateRecruitTitle = item.title,
                            mateName = item.hostInfo.kakaoNickname,
                            mateStyleName = item.hostInfo.characterName,
                            mateMatchingRatio = item.hostInfo.matchingRatio.toString(),
                            mateStyleType = item.hostInfo.selectedKeyword,
                            profileImage = item.hostInfo.profileImage.replace("http:", "https:"),
                            gender = item.gender,
                            ageRange = item.ageRange,
                        ),
                    )
                }
            },
        )
    }
}
