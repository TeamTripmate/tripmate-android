@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.home"
}

dependencies {
    implementations(
        projects.core.data,
        projects.feature.mateRecruit,

        libs.kotlinx.collections.immutable,
        libs.timber,
    )
}
