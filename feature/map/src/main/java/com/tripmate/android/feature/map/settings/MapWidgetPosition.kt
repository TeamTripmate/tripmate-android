package com.tripmate.android.feature.map.settings

import com.kakao.vectormap.MapGravity

/**
 * [com.kakao.vectormap.KakaoMap] 에서 지원하는 위젯의 포지션 정보를 담는 Data Util
 *
 * 카카오 지도 SDK 의 경우, 각 위젯들의 포지션에 대해 setPosition 함수를 통해 제공하고 있는데,
 * 컴포저블에서 파라미터를 통해 이를 쉽게 설정할 수 있도록 지원하기 위해서 해당 Data Util 을 지원합니다.
 *
 * @param mapDirection [com.kakao.vectormap.MapGravity] 값을 이용한 정렬 정보
 * @param xPx x축 패딩 값. 기본값은 0 입니다.
 * @param yPx y축 패딩 값. 기본값은 0 입니다.
 */
public data class MapWidgetPosition(
    val mapDirection: MapDirection,
    val xPx: Float = 0f,
    val yPx: Float = 0f,
)

/**
 * 쉽게 위젯의 방향을 설정할 수 있도록 제공하는 Data Util
 */
public enum class MapDirection(internal val value: Int) {
    Center(MapGravity.CENTER),
    Top(MapGravity.TOP or MapGravity.CENTER_HORIZONTAL),
    Right(MapGravity.RIGHT or MapGravity.CENTER_VERTICAL),
    Bottom(MapGravity.BOTTOM or MapGravity.CENTER_HORIZONTAL),
    Left(MapGravity.LEFT or MapGravity.CENTER_VERTICAL),
    TopLeft(MapGravity.TOP or MapGravity.LEFT),
    TopRight(MapGravity.TOP or MapGravity.RIGHT),
    BottomRight(MapGravity.BOTTOM or MapGravity.RIGHT),
    BottomLeft(MapGravity.BOTTOM or MapGravity.LEFT),
}
