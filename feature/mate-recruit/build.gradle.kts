@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.mate_recruit"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        projects.feature.navigator,

        libs.kotlinx.datetime,
        libs.kotlinx.collections.immutable,
        libs.androidx.activity.compose,
        libs.androidx.splash,
        libs.compose.system.ui.controller,
        libs.timber,
    )
}
