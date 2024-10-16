package com.tripmate.android.feature.main.di

import android.app.Activity
import android.content.Intent
import com.tripmate.android.core.common.extension.startActivityWithAnimation
import com.tripmate.android.feature.main.MainActivity
import com.tripmate.android.feature.navigator.MainNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        clearBackStack: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<MainActivity>(
            withFinish = withFinish,
            clearBackStack = clearBackStack,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainNavigatorModule {
    @Singleton
    @Binds
    abstract fun bindMainNavigator(mainNavigatorImpl: MainNavigatorImpl): MainNavigator
}
