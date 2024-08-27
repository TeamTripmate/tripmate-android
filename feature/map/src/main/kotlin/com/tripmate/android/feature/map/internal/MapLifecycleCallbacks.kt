package com.tripmate.android.feature.map.internal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.kakao.vectormap.KakaoMap

/**
 * [com.kakao.vectormap.KakaoMap] 의 맵에 직접 연관된 Callback 컨테이너.
 *
 * 맵 객체를 초기화하면서 [com.kakao.vectormap.MapLifeCycleCallback] 과
 * [com.kakao.vectormap.MapReadyCallback] 으로 등록할 콜백을 관리한다.
 */
internal class MapLifecycleCallbacks {
    var onMapReady: (KakaoMap) -> Unit by mutableStateOf({})
    var onMapResumed: () -> Unit by mutableStateOf({})
    var onMapPaused: () -> Unit by mutableStateOf({})
    var onMapError: (Exception) -> Unit by mutableStateOf({})
    var onMapDestroy: () -> Unit by mutableStateOf({})
}
