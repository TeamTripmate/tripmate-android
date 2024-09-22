rootProject.name = "tripmate-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
        maven { url = uri("https://jitpack.io") }
    }
}

include(
    ":app",

    ":core:common",
    ":core:data",
    ":core:database",
    ":core:datastore",
    ":core:designsystem",
    ":core:domain",
    ":core:network",
    ":core:ui",

    ":feature:home",
    ":feature:login",
    ":feature:main",
    ":feature:mypage",
    ":feature:mate-recruit",
    ":feature:mate-recruit-post",
    ":feature:mate-review",
    ":feature:navigator",
    ":feature:splash",
    ":feature:personalization",
    ":feature:map",
    ":feature:mate",
    ":feature:writing",
    ":feature:tripdetail",
    ":feature:trip-list",
)
