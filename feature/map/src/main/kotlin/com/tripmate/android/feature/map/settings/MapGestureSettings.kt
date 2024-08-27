package com.tripmate.android.feature.map.settings

/**
 * 지도 제스쳐 설정 객체.
 *
 * @param isOneFingerDoubleTapEnabled 한 손가락 더블-탭을 허용할지 여부를 지정합니다.
 * @param isTwoFingerSingleTapEnabled 두 손가락 탭을 허용할지 여부를 지정합니다.
 * @param isPanEnabled 카메라 이동을 허용할지 여부를 지정합니다.
 * @param isRotateEnabled 카메라 회전을 허용할지 여부를 지정합니다.
 * @param isZoomEnabled 카메라 줌을 허용할지 여부를 지정합니다.
 * @param isTiltEnabled 카메라 기울이기를 허용할지 여부를 지정합니다.
 * @param isLongTapAndDragEnabled 길게 누른 채로 드래그를 허용할지 여부를 지정합니다.
 * @param isRotateZoomEnabled 회전 줌을 허용할지 여부를 지정합니다.
 * @param isOneFingerZoomEnabled 한 손가락 줌을 허용할지 여부를 지정합니다.
 */
public data class MapGestureSettings(
    val isOneFingerDoubleTapEnabled: Boolean = true,
    val isTwoFingerSingleTapEnabled: Boolean = true,
    val isPanEnabled: Boolean = true,
    val isRotateEnabled: Boolean = true,
    val isZoomEnabled: Boolean = true,
    val isTiltEnabled: Boolean = true,
    val isLongTapAndDragEnabled: Boolean = true,
    val isRotateZoomEnabled: Boolean = true,
    val isOneFingerZoomEnabled: Boolean = true,
)

internal val DefaultMapGestureSettings = MapGestureSettings()

/**
 * 사용자의 제스처를 모두 허용하지 않는 세팅입니다.
 */
public val DisabledMapGestureSettings: MapGestureSettings = MapGestureSettings(
    isOneFingerDoubleTapEnabled = false,
    isTwoFingerSingleTapEnabled = false,
    isPanEnabled = false,
    isRotateEnabled = false,
    isZoomEnabled = false,
    isTiltEnabled = false,
    isLongTapAndDragEnabled = false,
    isRotateZoomEnabled = false,
    isOneFingerZoomEnabled = false,
)
