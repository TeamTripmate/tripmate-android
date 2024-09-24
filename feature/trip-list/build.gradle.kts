@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.triplist"
}

dependencies {
    implementations(
        projects.core.data,

        libs.kotlinx.collections.immutable,
        libs.compose.system.ui.controller,
        libs.timber,
    )
}
