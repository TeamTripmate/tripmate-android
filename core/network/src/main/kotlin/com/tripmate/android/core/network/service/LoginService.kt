package com.tripmate.android.core.network.service

import com.tripmate.android.core.network.request.LoginRequest
import com.tripmate.android.core.network.response.SpotDetailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/v1/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    )
}
