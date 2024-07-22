@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.splash"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        libs.kotlinx.collections.immutable,
        libs.androidx.activity.compose,
        libs.compose.system.ui.controller,
        libs.timber,
    )
}
