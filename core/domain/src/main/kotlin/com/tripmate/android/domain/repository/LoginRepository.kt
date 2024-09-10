package com.tripmate.android.domain.repository

interface LoginRepository {
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
}
