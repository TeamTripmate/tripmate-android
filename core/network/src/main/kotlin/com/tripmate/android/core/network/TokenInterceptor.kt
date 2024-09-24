package com.tripmate.android.core.network

import com.tripmate.android.core.datastore.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val dataStore: TokenDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val jwtToken = runBlocking {
            dataStore.getJwtToken()
        }
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", jwtToken)
            .build()
        return chain.proceed(request)
    }
}
