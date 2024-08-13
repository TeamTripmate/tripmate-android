package com.tripmate.android.feature.map.extension

import com.kakao.vectormap.camera.CameraPosition

/**
 * [CameraPosition] 을 쉽게 만들기 위한 DSL
 */
public fun cameraPosition(block: CameraPosition.Builder.() -> Unit): CameraPosition =
    CameraPosition.from(CameraPosition.Builder().apply(block))
