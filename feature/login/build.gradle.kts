@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.login"
}

dependencies {
    implementations(
        projects.feature.navigator,

        libs.androidx.activity.compose,
        libs.compose.system.ui.controller,
        libs.timber,
        libs.kakao.auth,
    )
}
