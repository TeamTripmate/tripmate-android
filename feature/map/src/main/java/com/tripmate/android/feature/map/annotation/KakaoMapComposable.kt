package io.hlab.vectormap.compose.annotation

import androidx.compose.runtime.ComposableTargetMarker

@Retention(AnnotationRetention.BINARY)
@ComposableTargetMarker(description = "KakaoMap Composable")
@Target(
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.TYPE,
    AnnotationTarget.TYPE_PARAMETER,
)
public annotation class KakaoMapComposable