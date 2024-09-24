@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.library)
    alias(libs.plugins.tripmate.android.hilt)
    alias(libs.plugins.tripmate.android.room)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tripmate.android.core.database"
}

dependencies {
    implementations(
        libs.timber,
    )
}
