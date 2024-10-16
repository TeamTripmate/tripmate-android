@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

import java.util.Properties

plugins {
    alias(libs.plugins.tripmate.android.application)
    alias(libs.plugins.tripmate.android.application.compose)
    alias(libs.plugins.tripmate.android.firebase)
    alias(libs.plugins.tripmate.android.hilt)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "com.tripmate.android"

    signingConfigs {
        create("release") {
            val propertiesFile = rootProject.file("keystore.properties")
            val properties = Properties()
            properties.load(propertiesFile.inputStream())
            storeFile = file(properties["STORE_FILE"] as String)
            storePassword = properties["STORE_PASSWORD"] as String
            keyAlias = properties["KEY_ALIAS"] as String
            keyPassword = properties["KEY_PASSWORD"] as String
        }
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".dev"
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name_dev",
            )
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            manifestPlaceholders += mapOf(
                "appName" to "@string/app_name",
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementations(
        projects.core.common,
        projects.core.data,
        projects.core.designsystem,
        projects.core.network,
        projects.core.datastore,
        projects.core.ui,

        projects.feature.home,
        projects.feature.main,
        projects.feature.login,
        projects.feature.mypage,
        projects.feature.navigator,
        projects.feature.splash,
        projects.feature.personalization,
        projects.feature.map,

        libs.androidx.activity.compose,
        libs.androidx.startup,
        libs.timber,
        libs.kakao.maps,
        libs.kakao.auth,
    )
}

secrets {
    defaultPropertiesFileName = "secrets.properties"
}
