package com.tripmate.android.core.network.service

import com.tripmate.android.core.network.request.LikeSpotRequest
import com.tripmate.android.core.network.request.WithdrawalRequest
import com.tripmate.android.core.network.response.SpotDetailResponse
import com.tripmate.android.core.network.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TripmateService {
    @GET("api/home/spot/{spot-id}")
    suspend fun getSpotDetail(
        @Path("spot-id") spotId: Long,
    ): SpotDetailResponse

    @POST("api/likes")
    suspend fun likeSpot(
        @Body likeSpotRequest: LikeSpotRequest,
    ): LikeSpotRequest

    @POST("api/v1/user/withdrawal")
    suspend fun withdrawal(
        @Body withdrawalRequest: WithdrawalRequest,
    )

    @GET("api/v1/user/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Long,
    ): UserInfoResponse
}
