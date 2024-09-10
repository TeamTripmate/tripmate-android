package com.tripmate.android.feature.personalization.di

import android.app.Activity
import android.content.Intent
import com.tripmate.android.core.common.extension.startActivityWithAnimation
import com.tripmate.android.feature.navigator.PersonalizationNavigator
import com.tripmate.android.feature.personalization.PersonalizationActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class PersonalizationNavigatorImpl @Inject constructor() : PersonalizationNavigator {
    override fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        clearBackStack: Boolean,
        intentBuilder: Intent.() -> Intent,
    ) {
        activity.startActivityWithAnimation<PersonalizationActivity>(
            withFinish = withFinish,
            clearBackStack = clearBackStack,
            intentBuilder = intentBuilder,
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PersonalizationModule {
    @Singleton
    @Binds
    abstract fun bindPersonalizationNavigator(personalizationNavigatorImpl: PersonalizationNavigatorImpl): PersonalizationNavigator
}
