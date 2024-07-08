@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.library)
    alias(libs.plugins.tripmate.android.library.compose)
    alias(libs.plugins.tripmate.android.hilt)
    alias(libs.plugins.tripmate.android.retrofit)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tripmate.android.core.common"
}

dependencies {
    implementations(
        projects.core.domain,

        libs.androidx.hilt.navigation.compose,

        libs.timber,
        libs.bundles.androidx.lifecycle,
    )
}
