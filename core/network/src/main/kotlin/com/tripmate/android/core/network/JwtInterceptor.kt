package com.tripmate.android.core.network

import com.tripmate.android.core.datastore.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class JwtInterceptor @Inject constructor(
    private val dataStore: TokenDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.isSuccessful && response.request.url.encodedPath.endsWith("/api/v1/login")) {
            val jwtToken = response.header("Authorization")
            jwtToken?.let {
                // JWT 토큰을 로컬에 저장
                runBlocking {
                    dataStore.saveJwtToken(it)
                }
            }
        }

        return response
    }
}
