package com.tripmate.android.feature.map.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kakao.vectormap.Compass
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapViewInfo
import com.kakao.vectormap.camera.CameraPosition

/**
 * [KakaoMap] 의 Event Listener Container
 *
 * 현재 지원하는 이벤트
 * - 지도 설정 : [onMapPaddingChange], [onMapViewInfoChange]
 * - 지도 클릭 : [onMapClick], [onCompassClick], [onPoiClick], [onTerrainClick]
 * - 카메라 : [onCameraMoveStart], [onCameraMoveEnd]
 */
public class MapEventListeners {
    public var onMapPaddingChange: () -> Unit by mutableStateOf({})
    public var onMapViewInfoChange: (MapViewInfo) -> Unit by mutableStateOf({})

    public var onMapClick: (LatLng) -> Unit by mutableStateOf({})
    public var onCompassClick: (Compass) -> Unit by mutableStateOf({})
    public var onPoiClick: (LatLng, String, String) -> Unit by mutableStateOf({ _, _, _ -> })
    public var onTerrainClick: (LatLng) -> Unit by mutableStateOf({ _ -> })

    public var onCameraMoveStart: (GestureType) -> Unit by mutableStateOf({})
    public var onCameraMoveEnd: (CameraPosition, GestureType) -> Unit by mutableStateOf({ _, _ -> })
}
