@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
    alias(libs.plugins.tripmate.android.retrofit)
}

android {
    namespace = "com.tripmate.android.feature.map"
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.kakao.maps)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.compose)
    implementation(libs.compose.system.ui.controller)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
