package com.tripmate.android.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.viewinterop.AndroidView
import com.kakao.vectormap.Compass
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapView
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.camera.CameraPosition
import com.tripmate.android.feature.map.extension.NoPadding
import com.tripmate.android.feature.map.extension.disposingComposition
import com.tripmate.android.feature.map.extension.newComposition
import com.tripmate.android.feature.map.internal.MapEventListeners
import com.tripmate.android.feature.map.internal.MapLifecycleCallbacks
import com.tripmate.android.feature.map.internal.MapUpdater
import com.tripmate.android.feature.map.settings.DefaultMapGestureSettings
import com.tripmate.android.feature.map.settings.DefaultMapViewSettings
import com.tripmate.android.feature.map.settings.MapGestureSettings
import com.tripmate.android.feature.map.settings.MapViewSettings
import com.tripmate.android.feature.map.state.CameraPositionState
import com.tripmate.android.feature.map.state.LocalCameraPositionState
import com.tripmate.android.feature.map.state.rememberCameraPositionState
import io.hlab.vectormap.compose.annotation.KakaoMapComposable
import io.hlab.vectormap.compose.settings.DefaultMapInitialOptions
import io.hlab.vectormap.compose.settings.MapInitialOptions

/**
 * [com.kakao.vectormap.KakaoMap] 을 제공하는 컴포저블
 *
 * 주어진 파라미터를 기반으로 지도 뷰를 그려주는 Compose View.
 *
 * @param modifier 지도가 그려질 영역에 대한 [Modifier]
 * @param content 다양한 컴포저블을 이용해 지도 뷰를 풍부하게 사용할 수 있음.
 */
@Composable
public fun KakaoMap(
    modifier: Modifier = Modifier,
    mapInitialOptions: MapInitialOptions = DefaultMapInitialOptions,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    mapViewSettings: MapViewSettings = DefaultMapViewSettings,
    mapGestureSettings: MapGestureSettings = DefaultMapGestureSettings,
    contentDescription: String? = null,
    mapPadding: PaddingValues = NoPadding,
    onMapReady: (KakaoMap) -> Unit = {},
    onMapResumed: () -> Unit = {},
    onMapPaused: () -> Unit = {},
    onMapError: (Exception) -> Unit = {},
    onMapDestroy: () -> Unit = {},
    onMapPaddingChange: () -> Unit = {},
    onMapViewInfoChange: (MapViewInfo) -> Unit = {},
    onMapClick: (LatLng) -> Unit = {},
    onCompassClick: (Compass) -> Unit = {},
    onPoiClick: (LatLng, String, String) -> Unit = { _, _, _ -> },
    onTerrainClick: (LatLng) -> Unit = { _ -> },
    onCameraMoveStart: (GestureType) -> Unit = {},
    onCameraMoveEnd: (CameraPosition, GestureType) -> Unit = { _, _ -> },
    content: (
        @Composable @KakaoMapComposable
        () -> Unit
    )? = null,
) {
    // Compose preview 일 때, 빈 영역을 반환해 렌더링을 허용할 수 있도록 한다.
    if (LocalInspectionMode.current) {
        Box(modifier = modifier)
        return
    }

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val mapView = remember { MapView(context) }

    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
        }
    } else {
        Modifier
    }

    AndroidView(modifier = modifier.then(semantics), factory = { mapView })

    // callback Containers
    // remember 를 이용해 sub-composition 을 구독해 content invoke 가 매번 일어나지 않도록 컨트롤함
    // 이와 같이 구성하면, 새로운 content invoke 를 제공하지 않으면서 sub-composition 에 대한 갱신이 가능함
    val mapLifecycleCallbacks = remember { MapLifecycleCallbacks() }.also {
        it.onMapReady = onMapReady
        it.onMapResumed = onMapResumed
        it.onMapPaused = onMapPaused
        it.onMapError = onMapError
        it.onMapDestroy = onMapDestroy
    }
    val mapEventListeners = remember { MapEventListeners() }.also {
        it.onMapPaddingChange = onMapPaddingChange
        it.onMapViewInfoChange = onMapViewInfoChange
        it.onMapClick = onMapClick
        it.onCompassClick = onCompassClick
        it.onPoiClick = onPoiClick
        it.onTerrainClick = onTerrainClick
        it.onCameraMoveStart = onCameraMoveStart
        it.onCameraMoveEnd = onCameraMoveEnd
    }
    // map settings
    val currentCameraPositionState by rememberUpdatedState(cameraPositionState)
    val currentMapPadding by rememberUpdatedState(mapPadding)
    val currentMapViewSettings by rememberUpdatedState(mapViewSettings)
    val currentMapGestureSettings by rememberUpdatedState(mapGestureSettings)

    // compositions
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)

    LaunchedEffect(Unit) {
        disposingComposition {
            mapView.newComposition(
                lifecycle = lifecycle,
                parentComposition = parentComposition,
                mapInitialOptions = mapInitialOptions,
                mapCallbackContainer = mapLifecycleCallbacks,
            ) {
                MapUpdater(
                    cameraPositionState = currentCameraPositionState,
                    mapEventListeners = mapEventListeners,
                    mapViewSettings = currentMapViewSettings,
                    mapGestureSettings = currentMapGestureSettings,
                    mapPadding = currentMapPadding,
                )
                CompositionLocalProvider(
                    LocalCameraPositionState provides cameraPositionState,
                ) {
                    currentContent?.invoke()
                }
            }
        }
    }
}
