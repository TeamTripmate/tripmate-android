@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.personalization"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "PACKAGE_NAME", "\"${libs.versions.packageName.get()}\"")
    }
}

dependencies {
    implementations(
        projects.core.data,
        projects.feature.navigator,

        libs.kotlinx.collections.immutable,
        libs.androidx.activity.compose,
        libs.androidx.splash,
        libs.compose.system.ui.controller,
        libs.timber,
    )
}
