package com.tripmate.android.feature.map.internal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.MapType
import com.kakao.vectormap.MapView
import com.tripmate.android.feature.map.extension.getMap
import com.tripmate.android.feature.map.internal.node.MapPropertiesNode
import com.tripmate.android.feature.map.settings.MapGestureSettings
import com.tripmate.android.feature.map.settings.MapViewSettings
import com.tripmate.android.feature.map.settings.adjustDimScreenType
import com.tripmate.android.feature.map.state.CameraPositionState

/**
 * MapProperty 를 최신 상태로 유지하기 위한 Composable.
 *
 * [MapUpdater] 는 [MapView] 의 Composition 범위 내에 항상 속해있으며, 컴포지션에 관여해 상태를 업데이트 할 수 있도록 한다.
 */
@Suppress("NOTHING_TO_INLINE")
@Composable
internal inline fun MapUpdater(
    cameraPositionState: CameraPositionState,
    mapEventListeners: MapEventListeners,
    mapViewSettings: MapViewSettings,
    mapGestureSettings: MapGestureSettings,
    mapPadding: PaddingValues,
) {
    // 지도 객체 및 화면 관련 정보
    val map = getMap()
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    // 주입받는 State 들에 대해 ComposeNode 를 recompose 해 상태를 갱신함.
    // 매번 LayoutNode 에 대한 직접적인 recompose 가 매번 일어나는 걸 방지할 수 있음.
    ComposeNode<MapPropertiesNode, MapApplier>(
        factory = {
            // baseMap 이 초기값이 기본 맵 타입인 경우, 불필요한 API 의 호출을 피함.
            if (mapViewSettings.baseMap != MapType.NORMAL) {
                map.adjustBaseMap(mapType = mapViewSettings.baseMap)
            }
            MapPropertiesNode(
                map = map,
                initialMapPadding = mapPadding,
                cameraPositionState = cameraPositionState,
                mapEventListeners = mapEventListeners,
                density = density,
                layoutDirection = layoutDirection,
            )
        },
        update = {
            // 노드에 density 와 layoutDirection 을 가지고 있도록 함으로써
            // 업데이트 블록이 캡쳐링 되지 않고, 컴파일러가 해당 블록을 싱글톤으로 변경할 수 있도록 한다.
            update(density) { this.density = it }
            update(layoutDirection) { this.layoutDirection = it }
            update(mapPadding) { this.setMapPadding(it) }
            // Map View Setting 설정
            update(mapViewSettings.baseMap) { mapType -> map.adjustBaseMap(mapType) }
            set(mapViewSettings.isCameraAnimateEnabled) { map.setCameraAnimateEnable(it) }
            set(mapViewSettings.isTrackingRotation) { map.trackingManager?.setTrackingRotation(it) }
            set(mapViewSettings.logoPosition) { position ->
                position?.let { map.logo?.setPosition(it.mapDirection.value, it.xPx, it.yPx) }
            }
            set(mapViewSettings.compassPosition) { position ->
                position?.let { map.compass?.setPosition(it.mapDirection.value, it.xPx, it.yPx) }
            }
            set(mapViewSettings.isCompassVisible) { compassVisibility ->
                if (compassVisibility) map.compass?.show() else map.compass?.hide()
            }
            set(mapViewSettings.isCompassBackToNorthOnClick) { map.compass?.isBackToNorthOnClick = it }
            set(mapViewSettings.scaleBarPosition) { position ->
                position?.let { map.scaleBar?.setPosition(it.mapDirection.value, it.xPx, it.yPx) }
            }
            set(mapViewSettings.isScaleBarVisible) { scaleBarVisibility ->
                if (scaleBarVisibility) map.scaleBar?.show() else map.scaleBar?.hide()
            }
            set(mapViewSettings.isScaleBarAutoHide) { map.scaleBar?.isAutoHide = it }
            set(mapViewSettings.isPoiVisible) { map.setPoiVisible(it) }
            set(mapViewSettings.isPoiClickable) { map.setPoiClickable(it) }
            set(mapViewSettings.poiLanguage) { map.setPoiLanguage(it.code) }
            set(mapViewSettings.poiScale) { map.setPoiScale(it) }
            set(mapViewSettings.buildingHeightScale) { map.buildingHeightScale = it }
            set(mapViewSettings.fixedCenterGestures) { fixedCenterGestures ->
                val nonFixedGestures = (gestureTypes - fixedCenterGestures).toTypedArray()
                if (nonFixedGestures.isNotEmpty()) map.setFixedCenter(false, *nonFixedGestures)
                if (fixedCenterGestures.isNotEmpty()) map.setFixedCenter(true, *fixedCenterGestures.toTypedArray())
            }
            set(mapViewSettings.dimScreenType) { map.adjustDimScreenType(it) }
            set(mapViewSettings.dimScreenColor) { color ->
                val dimScreenLayer = map.dimScreenManager?.dimScreenLayer ?: return@set
                dimScreenLayer.setColor(color.toArgb())
            }
            // Map Gesture Setting 설정
            set(mapGestureSettings.isOneFingerDoubleTapEnabled) {
                map.setGestureEnable(GestureType.OneFingerDoubleTap, it)
            }
            set(mapGestureSettings.isTwoFingerSingleTapEnabled) {
                map.setGestureEnable(GestureType.TwoFingerSingleTap, it)
            }
            set(mapGestureSettings.isPanEnabled) { map.setGestureEnable(GestureType.Pan, it) }
            set(mapGestureSettings.isRotateEnabled) { map.setGestureEnable(GestureType.Rotate, it) }
            set(mapGestureSettings.isZoomEnabled) { map.setGestureEnable(GestureType.Zoom, it) }
            set(mapGestureSettings.isTiltEnabled) { map.setGestureEnable(GestureType.Tilt, it) }
            set(mapGestureSettings.isLongTapAndDragEnabled) { map.setGestureEnable(GestureType.LongTapAndDrag, it) }
            set(mapGestureSettings.isRotateZoomEnabled) { map.setGestureEnable(GestureType.RotateZoom, it) }
            set(mapGestureSettings.isOneFingerZoomEnabled) { map.setGestureEnable(GestureType.OneFingerZoom, it) }
            // CameraPositionState 및 EventListener 변경 감지
            update(cameraPositionState) { this.cameraPositionState = it }
            update(mapEventListeners) { this.mapEventListeners = it }
        },
    )
}

private val gestureTypes = GestureType.values().toSet()

/**
 * 기본 맵 스타일을 지정합니다.
 *
 * 현재 아래와 같은 맵 스타일이 지원됩니다.
 * - [MapType.NORMAL] : 기본 맵뷰
 * - [MapType.SKYVIEW] : 스카이맵 맵뷰
 */
private fun KakaoMap.adjustBaseMap(mapType: MapType) = changeMapType(mapType)
