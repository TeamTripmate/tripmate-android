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
        projects.feature.mypage,
        projects.feature.navigator,
        projects.feature.mate,
        projects.feature.writing,
        projects.feature.notification,

        libs.androidx.activity.compose,
        libs.kotlinx.collections.immutable,
        libs.coil.compose,
        libs.compose.system.ui.controller,
        libs.androidx.navigation.compose,
    )
}
