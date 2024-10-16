package com.tripmate.android.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoginClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TripmateClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TripmateApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoginApi
