@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.mate_recruit_post"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementations(
        libs.kotlinx.datetime,
        libs.kotlinx.collections.immutable,

        libs.timber,
    )
}
