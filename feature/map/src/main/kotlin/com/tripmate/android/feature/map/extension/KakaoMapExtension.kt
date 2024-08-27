package com.tripmate.android.feature.map.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionContext
import androidx.lifecycle.Lifecycle
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapViewInfo
import com.tripmate.android.feature.map.internal.LifecycleAwareKakaoMapReadyCallback
import com.tripmate.android.feature.map.internal.MapApplier
import com.tripmate.android.feature.map.internal.MapLifecycleCallbacks
import com.tripmate.android.feature.map.settings.MapInitialOptions
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Map 에 대한 [Composition] 을 생성.
 *
 * - Lifecycle Aware 하게 [KakaoMap] 객체를 가져옴.
 * - [content] 를 명시적으로 Layout 하고, Composition 객체를 반환함.
 *
 * @return [MapApplier] 를 적용한 [MapView] 의 Composition 반환.
 */
internal suspend inline fun MapView.newComposition(
    lifecycle: Lifecycle,
    parentComposition: CompositionContext,
    mapInitialOptions: MapInitialOptions,
    mapCallbackContainer: MapLifecycleCallbacks,
    noinline content: @Composable () -> Unit,
): Composition {
    val map = awaitMap(
        lifecycle = lifecycle,
        mapInitialOptions = mapInitialOptions,
        mapCallbackContainer = mapCallbackContainer,
    )
    return Composition(applier = MapApplier(map = map, mapView = this), parent = parentComposition)
        .apply { setContent(content) }
}

private suspend inline fun MapView.awaitMap(
    lifecycle: Lifecycle,
    mapInitialOptions: MapInitialOptions,
    mapCallbackContainer: MapLifecycleCallbacks,
): KakaoMap {
    return suspendCoroutine { continuation ->
        getMapAsync(
            lifecycle = lifecycle,
            mapInitialOptions = mapInitialOptions,
            mapCallbackContainer = mapCallbackContainer,
        ) { kakaoMap ->
            continuation.resume(kakaoMap)
        }
    }
}

private fun MapView.getMapAsync(
    lifecycle: Lifecycle,
    mapInitialOptions: MapInitialOptions,
    mapCallbackContainer: MapLifecycleCallbacks,
    onMapReady: (KakaoMap) -> Unit,
) {
    start(
        object : MapLifeCycleCallback() {
            override fun onMapPaused() {
                mapCallbackContainer.onMapPaused
            }

            override fun onMapResumed() {
                mapCallbackContainer.onMapResumed
            }

            override fun onMapDestroy() {
                mapCallbackContainer.onMapDestroy
            }

            override fun onMapError(error: Exception) {
                mapCallbackContainer.onMapError(error)
            }
        },

        object : LifecycleAwareKakaoMapReadyCallback(lifecycle = lifecycle) {
            override fun onLifecycleAwareMapReady(kakaoMap: KakaoMap) {
                onMapReady(kakaoMap)
                mapCallbackContainer.onMapReady(kakaoMap)
            }

            override fun getMapViewInfo(): MapViewInfo {
                val mapViewInfo = mapInitialOptions.appName ?.let { MapViewInfo.from(it) } ?: super.getMapViewInfo()
                return mapViewInfo.apply {
                    mapInitialOptions.mapType?.let { setMapType(it) }
                    mapInitialOptions.styleName?.let { mapStyle = it }
                }
            }

            override fun getTimeout(): Int {
                return mapInitialOptions.timeout ?: super.getTimeout()
            }
        },
    )
}
