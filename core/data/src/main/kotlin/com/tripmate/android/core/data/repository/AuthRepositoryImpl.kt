package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : AuthRepository {
    override suspend fun saveAuthToken(accessToken: String, refreshToken: String) {
        tokenDataSource.saveAuthToken(accessToken, refreshToken)
    }

    override suspend fun getAccessToken(): String {
        return tokenDataSource.getAccessToken()
    }

    override suspend fun getRefreshToken(): String {
        return tokenDataSource.getRefreshToken()
    }

    override suspend fun clearAuthToken() {
        tokenDataSource.clearAuthToken()
    }
}
