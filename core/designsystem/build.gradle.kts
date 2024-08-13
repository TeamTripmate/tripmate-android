@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.library)
    alias(libs.plugins.tripmate.android.library.compose)
}

android {
    namespace = "com.tripmate.android.core.designsystem"
}

dependencies {
    implementations(
        projects.core.common,

        libs.androidx.splash,
        libs.coil.compose,
        libs.timber,
        libs.compose.keyboard.state,

        libs.bundles.landscapist,
    )
}
