@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.mate"
}

dependencies {
    implementations(
        projects.feature.map,

        libs.timber,
        libs.kotlinx.collections.immutable,
        libs.androidx.compose.material3,
        libs.android.play.services.location,
        libs.accompanist.permissions,
        libs.kakao.maps,
    )
}
