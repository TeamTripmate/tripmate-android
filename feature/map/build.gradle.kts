@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

import com.tripmate.android.implementation


plugins {
    alias(libs.plugins.tripmate.android.feature)
    alias(libs.plugins.tripmate.android.retrofit)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.tripmate.android.feature.map"
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.kakao.maps)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.compose.system.ui.controller)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.android.play.services.location)
    implementation(libs.accompanist.permissions)
}
