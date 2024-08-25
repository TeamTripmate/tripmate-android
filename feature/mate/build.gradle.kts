@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

import com.tripmate.android.implementation

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.mate"
}

dependencies {
    implementation(project(":feature:map"))
    implementations(
        libs.timber,
        libs.androidx.compose.material3,
        libs.android.play.services.location,
        libs.accompanist.permissions,
        libs.kakao.maps,
    )
}
