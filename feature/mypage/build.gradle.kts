@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
    alias(libs.plugins.tripmate.android.retrofit)
}

android {
    namespace = "com.tripmate.android.feature.mypage"
}

dependencies {
    implementations(
        libs.androidx.activity.compose,
        libs.kotlinx.collections.immutable,
        libs.coil.compose,
        libs.compose.system.ui.controller,
        libs.androidx.navigation.compose,
        libs.timber,
    )
}
