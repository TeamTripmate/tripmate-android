package com.tripmate.android.core.network.service

import com.tripmate.android.core.network.request.CompanionApplyRequest
import com.tripmate.android.core.network.request.CompanionRecruitmentRequest
import com.tripmate.android.core.network.request.MateSelectRequest
import com.tripmate.android.core.network.request.PersonalizedTestRequest
import com.tripmate.android.core.network.request.WithdrawalRequest
import com.tripmate.android.core.network.response.CompanionDetailInfoResponse
import com.tripmate.android.core.network.response.CreatedTripListResponse
import com.tripmate.android.core.network.response.LocationBasedSpotSearchResponse
import com.tripmate.android.core.network.response.ParticipatedTripListResponse
import com.tripmate.android.core.network.response.PersonalizedTestResultResponse
import com.tripmate.android.core.network.response.TripDetailInfoResponse
import com.tripmate.android.core.network.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TripmateService {
    @GET("/api/v1/spots")
    suspend fun getNearbyTouristSpots(
        @Query("searchType") searchType: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("range") range: String,
        @Query("spotTypeGroup") spotTypeGroup: String,
        @Query("spotType") spotType: String,
    ): LocationBasedSpotSearchResponse

    @GET("api/v1/spots/{spotId}")
    suspend fun getTripDetailInfo(
        @Path("spotId") spotId: String,
    ): TripDetailInfoResponse

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
        @Path("companionId") companionId: Long,
    ): CompanionDetailInfoResponse

    @POST("api/v1/companions/apply")
    suspend fun companionsApply(
        @Body companionApplyRequest: CompanionApplyRequest,
    )

    @POST("api/v1/users/{userId}/personalized-tests")
    suspend fun submitPersonalizedTest(
        @Path("userId") userId: Long,
        @Body personalizedTestRequest: PersonalizedTestRequest,
    ): PersonalizedTestResultResponse

    @GET("api/v1/trip-list/collect/companions/{userId}")
    suspend fun getCreatedTripList(
        @Path("userId") userId: Long,
    ): CreatedTripListResponse

    @GET("api/v1/trip-list/apply/companions/{userId}")
    suspend fun getParticipatedTripList(
        @Path("userId") userId: Long,
    ): ParticipatedTripListResponse

    @POST("api/v1/trip-list/choose/companion")
    suspend fun selectMate(
        @Body mateSelectRequest: MateSelectRequest,
    )

    @POST("api/v1/companions")
    suspend fun createCompanionRecruitment(
        @Body companionRecruitmentRequest: CompanionRecruitmentRequest,
    )
}
