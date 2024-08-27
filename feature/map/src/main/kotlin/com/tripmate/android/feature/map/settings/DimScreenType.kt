package com.tripmate.android.feature.map.settings

import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.shape.DimScreenCover

/**
 * 적용할 DimScreen 을 결정합니다.
 *
 * - [NONE] : DimScreen 을 제거합니다. ( visible = false )
 * - [OnlyMap] : 지도만 가리는 DimScreen 이 적용됩니다. ( 라벨은 가려지지 않습니다. )
 * - [MapAndLabel] : 지도 및 라벨을 가리는 DimScreen 이 적용됩니다.
 * - [All] : 전체를 다 가리는 DimScreen 이 적용됩니다.
 */
public enum class DimScreenType {
    NONE,
    OnlyMap,
    MapAndLabel,
    All,
}

internal fun KakaoMap.adjustDimScreenType(type: DimScreenType) {
    val dimScreen = this.dimScreenManager?.dimScreenLayer ?: return
    if (type == DimScreenType.NONE) {
        dimScreen.setVisible(false)
    } else {
        dimScreen.setVisible(true)
        when (type) {
            DimScreenType.OnlyMap -> dimScreen.setDimScreenCover(DimScreenCover.Map)
            DimScreenType.MapAndLabel -> dimScreen.setDimScreenCover(DimScreenCover.MapAndLabel)
            DimScreenType.All -> dimScreen.setDimScreenCover(DimScreenCover.All)
            else -> error("Error on adjusting DimScreenType !")
        }
    }
}
