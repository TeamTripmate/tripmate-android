@file:Suppress("INLINE_FROM_HIGHER_PLATFORM")

plugins {
    alias(libs.plugins.tripmate.jvm.kotlin)
    id("kotlinx-serialization")
}

dependencies {
    compileOnly(
        libs.compose.stable.marker,
    )
    implementations(
        libs.kotlinx.serialization.json,
        libs.kotlinx.coroutines.core,
    )
}
