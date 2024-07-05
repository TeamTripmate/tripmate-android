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
    ":feature:main",
    ":feature:menu",

)
include(":core:designsystem")
include(":core:domain")
