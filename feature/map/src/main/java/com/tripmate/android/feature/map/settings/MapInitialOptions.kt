package io.hlab.vectormap.compose.settings

import com.kakao.vectormap.MapType

/**
 * 맵 초기 시작 정보 설정
 *
 * 기존 [com.kakao.vectormap.MapReadyCallback] 을 override 해 제공하던 초기 정보를 세팅합니다.
 *
 * @property appName 등록된 앱 이름. 기본값은 "openmap".
 * @property mapType 지도의 타입. 기본값은 [MapType.NORMAL].
 * @property styleName 지도의 스타일 이름
 * @property timeout 지도 데이터 로딩 시 Http 통신 timeout. 단위는 ms. ( 기본값 : 5000ms )
 *
 * @see com.kakao.vectormap.MapReadyCallback
 */
public data class MapInitialOptions(
    val appName: String? = null,
    val mapType: MapType? = null,
    val styleName: String? = null,
    val timeout: Int? = null,
)

internal val DefaultMapInitialOptions = MapInitialOptions()