package com.tripmate.android.core.datastore

interface TokenDataSource {
    suspend fun saveAuthToken(id: Long, accessToken: String, refreshToken: String)
    suspend fun saveJwtToken(jwtToken: String)
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun getId(): Long
    suspend fun getJwtToken(): String
    suspend fun clearAuthToken()
}
