@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.library)
    alias(libs.plugins.tripmate.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tripmate.android.core.domain"
}

dependencies {
    implementations(
        libs.kotlinx.serialization.json,
    )
}
