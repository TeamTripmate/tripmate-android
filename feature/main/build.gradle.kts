@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.android.feature)
}

android {
    namespace = "com.tripmate.android.feature.main"
}

dependencies {
    implementations(
        projects.feature.home,
        projects.feature.mypage,
        projects.feature.navigator,
        projects.feature.mate,
        projects.feature.mateRecruit,
        projects.feature.mateReview,
        projects.feature.tripDetail,
        projects.feature.tripList,
        projects.feature.mateRecruitPost,
        projects.feature.map,
        projects.feature.tripOriginal,
        libs.androidx.activity.compose,
        libs.kotlinx.collections.immutable,
        libs.coil.compose,
        libs.compose.system.ui.controller,
        libs.androidx.navigation.compose,
    )
}
