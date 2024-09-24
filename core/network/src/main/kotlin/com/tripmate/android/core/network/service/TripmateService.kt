package com.tripmate.android.core.network.service

import com.tripmate.android.core.network.request.CompanionApplyRequest
import com.tripmate.android.core.network.request.LikeSpotRequest
import com.tripmate.android.core.network.response.CompanionDetailInfoResponse
import com.tripmate.android.core.network.request.WithdrawalRequest
import com.tripmate.android.core.network.response.LocationBasedSpotSearchResponse
import com.tripmate.android.core.network.response.SpotDetailResponse
import com.tripmate.android.core.network.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TripmateService {
    @GET("api/home/spot/{spot-id}")
    suspend fun getSpotDetail(
        @Path("spot-id") spotId: Long,
    ): SpotDetailResponse

    @POST("api/likes")
    suspend fun likeSpot(
        @Body likeSpotRequest: LikeSpotRequest,
    ): LikeSpotRequest

    @GET("/api/v1/spots")
    suspend fun getNearbyTouristSpots(
        @Query("searchType") searchType: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("range") range: String,
        @Query("spotTypeGroup") spotTypeGroup: String,
        @Query("category") category: String,
    ): LocationBasedSpotSearchResponse

    @POST("api/v1/user/withdrawal")
    suspend fun withdrawal(
        @Body withdrawalRequest: WithdrawalRequest,
    )

    @GET("api/v1/user/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Long,
    ): UserInfoResponse

    @GET("api/v1/companions/user/{companionId}")
    suspend fun getCompanionsDetailInfo(
        @Path("companionId") companionId: Int,
    ): CompanionDetailInfoResponse

    @POST("api/v1/companions/apply")
    suspend fun companionsApply(
        @Body companionApplyRequest: CompanionApplyRequest,
    )
}
