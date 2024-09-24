package com.tripmate.android.core.data.repository

import com.tripmate.android.core.data.util.runSuspendCatching
import com.tripmate.android.core.datastore.TokenDataSource
import com.tripmate.android.core.network.request.LoginRequest
import com.tripmate.android.core.network.request.WithdrawalRequest
import com.tripmate.android.core.network.service.LoginService
import com.tripmate.android.core.network.service.TripmateService
import com.tripmate.android.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val tripMateService: TripmateService,
    private val loginService: LoginService,
) : AuthRepository {
    override suspend fun saveAuthToken(id: Long, accessToken: String, refreshToken: String) {
        tokenDataSource.saveAuthToken(id, accessToken, refreshToken)
    }

    override suspend fun serverLogin(
        id: Long,
        nickname: String,
        thumbnailImageUrl: String,
        profileImageUrl: String,
        accessToken: String,
        refreshToken: String,
    ) = runSuspendCatching {
        loginService.login(LoginRequest(id, nickname, thumbnailImageUrl, profileImageUrl, accessToken, refreshToken))
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

    override suspend fun withdrawal()= runSuspendCatching {
        tripMateService.withdrawal(WithdrawalRequest(tokenDataSource.getId()))
    }
}
