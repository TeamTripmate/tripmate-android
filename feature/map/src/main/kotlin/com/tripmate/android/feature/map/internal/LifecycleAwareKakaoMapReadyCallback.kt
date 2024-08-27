package com.tripmate.android.feature.map.internal

import androidx.lifecycle.Lifecycle
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback

internal abstract class LifecycleAwareKakaoMapReadyCallback(
    private val lifecycle: Lifecycle,
) : KakaoMapReadyCallback() {

    /**
     * [onMapReady] 의 override fun
     *
     * KakaoMap 이 실행될 Context Lifecycle 의 초기화가 보장되었을 경우에만 콜백이 실행되게 하여
     * 지도 객체에 대한 LifecycleAware 한 접근을 제공한다.
     */
    final override fun onMapReady(kakaoMap: KakaoMap) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            onLifecycleAwareMapReady(kakaoMap = kakaoMap)
        }
    }

    abstract fun onLifecycleAwareMapReady(kakaoMap: KakaoMap)
}
