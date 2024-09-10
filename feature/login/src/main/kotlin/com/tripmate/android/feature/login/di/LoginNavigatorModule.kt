package com.tripmate.android.feature.login.di

import android.app.Activity
import android.content.Intent
import com.tripmate.android.core.common.extension.startActivityWithAnimation
import com.tripmate.android.feature.login.LoginActivity
import com.tripmate.android.feature.navigator.LoginNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class LoginNavigatorImpl @Inject constructor() : LoginNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        clearBackStack: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<LoginActivity>(
            withFinish = withFinish,
            clearBackStack = clearBackStack,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginNavigatorModule {
    @Singleton
    @Binds
    abstract fun bindLoginNavigator(loginNavigatorImpl: LoginNavigatorImpl): LoginNavigator
}
