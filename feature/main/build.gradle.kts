@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.main"
}

dependencies {
    implementations(
        projects.feature.home,
        projects.feature.menu,
        projects.feature.navigator,

        libs.androidx.activity.compose,
        libs.kotlinx.collections.immutable,
        libs.coil.compose,
        libs.compose.system.ui.controller,
        libs.androidx.navigation.compose,
    )
}
