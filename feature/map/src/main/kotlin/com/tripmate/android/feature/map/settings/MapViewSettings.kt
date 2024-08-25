package com.tripmate.android.feature.map.settings

import androidx.compose.ui.graphics.Color
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.MapType
import com.kakao.vectormap.PoiScale

/**
 * 지도 뷰에 관련한 설정 객체.
 *
 * @param baseMap 맵 스타일을 지정한다. 기본값은 [MapType.NORMAL] 이다.
 * @param isCameraAnimateEnabled 애니메이션 있는 카메라 움직임 가능 여부를 지정한다.
 * @param isTrackingRotation 특정 라벨을 트래킹할 때 라벨이 회전 시 지도 또한 회전을 따라갈지 지정한다.
 * @param logoPosition kakao 로고의 맵 상 위치를 지정한다.
 * @param compassPosition 컴퍼스의 맵 상 위치를 지정한다.
 * @param isCompassVisible 컴퍼스가 보이게 할지 여부를 지정한다.
 * @param isCompassBackToNorthOnClick 컴퍼스 터치 시, 다시 지도 방향을 원위치 시킬지 지정한다.
 * @param scaleBarPosition 축척의 맵 상 위치를 지정한다.
 * @param isScaleBarVisible 축척이 보이게 할지 여부를 지정한다.
 * @param isScaleBarAutoHide 축척이 일정 시간이 지나면 사라지게 할지 여부를 지정한다.
 * @param isPoiVisible POI 가 보이게 할지 여부를 지정한다.
 * @param isPoiClickable POI 가 클릭 가능하게 할지 여부를 지정한다.
 * @param poiLanguage POI 를 표시할 언어를 지정한다.
 * @param poiScale POI 크기 비율을 지정한다.
 * @param buildingHeightScale 지도 빌딩 높이를 지정한다. ( 0.01 ~ 1.0 )
 * @param fixedCenterGestures 카메라 중심이 고정된 채로 정해진 동작을 수행할 Gesture 들을 지정한다.
 * @param dimScreenType 어떤 DimScreen 을 지도에 적용할지를 지정한다.
 * @param dimScreenColor 현재 적용된 DimScreen 의 색상을 지정한다. ( 기본값 : 검은색 - 알파 0.2f )
 *
 * [com.kakao.vectormap.KakaoMap.isVisible] 은 현재 활동중인 카메라를 종료하지만, 마지막으로 보여진 스냅샷은 노출한다.
 * 즉, 사용자에게는 단순히 visible 한 마지막 시점의 지도가 조작 불가능한 상태로 제공될 뿐이다. 이는 맵이 보이지 않게 하기 위한
 * 기존 의도와 다르므로, API 에서 제공하지 않습니다.
 */
public data class MapViewSettings(
    val baseMap: MapType = MapType.NORMAL,
    val isCameraAnimateEnabled: Boolean = true,
    val isTrackingRotation: Boolean = true,
    val logoPosition: MapWidgetPosition? = null,
    val compassPosition: MapWidgetPosition? = null,
    val isCompassVisible: Boolean = true,
    val isCompassBackToNorthOnClick: Boolean = true,
    val scaleBarPosition: MapWidgetPosition? = null,
    val isScaleBarVisible: Boolean = false,
    val isScaleBarAutoHide: Boolean = true,
    val isPoiVisible: Boolean = true,
    val isPoiClickable: Boolean = true,
    val poiLanguage: PoiLanguage = PoiLanguage.Korean,
    val poiScale: PoiScale = PoiScale.REGULAR,
    val buildingHeightScale: Float = 0.5f,
    val fixedCenterGestures: Set<GestureType> = emptySet(),
    val dimScreenType: DimScreenType = DimScreenType.NONE,
    val dimScreenColor: Color = Color.Black.copy(alpha = 0.2f),
) {
    init {
        require(buildingHeightScale in 0f..1f) { "buildingHeightScale must be between 0.01f and 1f!" }
    }
}

internal val DefaultMapViewSettings = MapViewSettings()
