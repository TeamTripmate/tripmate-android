package com.tripmate.android.core.data.repository

import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource
) : LoginRepository {
    override suspend fun saveAuthToken(accessToken: String, refreshToken: String) {
        tokenDataSource.saveAuthToken(accessToken, refreshToken)
    }

    override suspend fun getAccessToken(): String {
        return tokenDataSource.getAccessToken()
    }

    override suspend fun getRefreshToken(): String {
        return tokenDataSource.getRefreshToken()
    }
}
