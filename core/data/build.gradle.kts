@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.library)
    alias(libs.plugins.tripmate.android.hilt)
    alias(libs.plugins.tripmate.android.firebase)
    id("kotlinx-serialization")
}

android {
    namespace = "com.tripmate.android.core.data"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "APP_VERSION", "\"${libs.versions.versionName.get()}\"")
        buildConfigField("String", "PACKAGE_NAME", "\"${libs.versions.packageName.get()}\"")
    }
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.database,
        projects.core.datastore,
        projects.core.domain,
        projects.core.network,

        libs.timber,
    )
}
