package com.tripmate.android.domain.repository

interface AuthRepository {
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    suspend fun serverLogin(
        id: Long,
        nickname: String,
        thumbnailImageUrl: String,
        profileImageUrl: String,
        accessToken: String,
        refreshToken: String,
    ): Result<Unit>

    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun clearAuthToken()
}
