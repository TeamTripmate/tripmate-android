package com.tripmate.android.feature.splash.di

import android.app.Activity
import android.content.Intent
import com.tripmate.android.core.common.extension.startActivityWithAnimation
import com.tripmate.android.feature.navigator.SplashNavigator
import com.tripmate.android.feature.splash.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class SplashNavigatorImpl @Inject constructor() : SplashNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<SplashActivity>(
            withFinish = withFinish,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SplashNavigatorModule {
    @Singleton
    @Binds
    abstract fun bindSplashNavigator(splashNavigatorImpl: SplashNavigatorImpl): SplashNavigator
}
