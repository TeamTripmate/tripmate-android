package com.tripmate.android.feature.map.internal.node

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMap.OnMapViewInfoChangeListener
import com.kakao.vectormap.MapViewInfo
import com.tripmate.android.feature.map.internal.MapEventListeners
import com.tripmate.android.feature.map.state.CameraPositionState

/**
 * 맵 객체의 설정을 관리하는 Node
 *
 * 아래와 같은 책임을 이행한다.
 * - [mapEventListeners] 를 [KakaoMap] 에 세팅
 * - 현재 맵이 위치한 화면의 [density] 와 [layoutDirection] 를 기록, 맵 패딩 설정에 이용가능하도록 한다.
 */
internal class MapPropertiesNode(
    val map: KakaoMap,
    initialMapPadding: PaddingValues,
    cameraPositionState: CameraPositionState,
    var mapEventListeners: MapEventListeners,
    var density: Density,
    var layoutDirection: LayoutDirection,
) : MapNode {

    init {
        // 초기 맵 패딩 세팅이 카메라 포지션보다 먼저 실행되어야 카메라가 정상적인 위치에서 실행됨을 보장할 수 있음.
        setMapPadding(paddingValues = initialMapPadding)
        cameraPositionState.setMap(map)
    }

    var cameraPositionState: CameraPositionState = cameraPositionState
        set(value) {
            if (value == field) return
            field.setMap(null)
            field = value
            value.setMap(map)
        }

    private val onCameraMoveStartListener = KakaoMap.OnCameraMoveStartListener { _, gestureType ->
        cameraPositionState.isMoving = true
        mapEventListeners.onCameraMoveStart(gestureType)
    }

    private val onCameraMoveEndListener = KakaoMap.OnCameraMoveEndListener { _, position, gestureType ->
        cameraPositionState.isMoving = false
        cameraPositionState.rawPosition = position
        mapEventListeners.onCameraMoveEnd(position, gestureType)
    }

    override fun onAttached() {
        map.setOnPaddingChangeListener { _ ->
            mapEventListeners.onMapPaddingChange()
        }
        map.setOnMapViewInfoChangeListener(
            object : OnMapViewInfoChangeListener {
                override fun onMapViewInfoChanged(mapViewInfo: MapViewInfo?) {
                    mapViewInfo?.let { mapEventListeners.onMapViewInfoChange(it) }
                }

                override fun onMapViewInfoChangeFailed() = Unit
            },
        )
        map.setOnMapClickListener { _, latLng, _, _ ->
            mapEventListeners.onMapClick(latLng)
        }
        map.setOnCompassClickListener { _, compass ->
            compass?.let { mapEventListeners.onCompassClick(it) }
        }
        map.setOnPoiClickListener { _, position, poiType, poiId ->
            mapEventListeners.onPoiClick(position, poiType, poiId)
        }
        map.setOnTerrainClickListener { _, position, _ ->
            mapEventListeners.onTerrainClick(position)
        }
        map.setOnCameraMoveStartListener(onCameraMoveStartListener)
        map.setOnCameraMoveEndListener(onCameraMoveEndListener)
    }

    override fun onRemoved() {
        cameraPositionState.setMap(null)
    }

    /**
     * Map 의 Padding 을 설정하는 함수
     */
    internal fun setMapPadding(paddingValues: PaddingValues) {
        val node = this
        with(density) {
            map.setPadding(
                paddingValues.calculateLeftPadding(node.layoutDirection).roundToPx(),
                paddingValues.calculateTopPadding().roundToPx(),
                paddingValues.calculateRightPadding(node.layoutDirection).roundToPx(),
                paddingValues.calculateBottomPadding().roundToPx(),
            )
        }
    }
}
