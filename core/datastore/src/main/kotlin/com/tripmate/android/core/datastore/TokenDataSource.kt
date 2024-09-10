package com.tripmate.android.core.datastore

interface TokenDataSource {
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
}
