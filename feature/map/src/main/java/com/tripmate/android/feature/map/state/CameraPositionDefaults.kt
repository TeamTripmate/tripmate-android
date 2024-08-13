package com.tripmate.android.feature.map.state

import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraPosition
import com.tripmate.android.feature.map.extension.cameraPosition

public object CameraPositionDefaults {
    // GangNam Station
    private val Position: LatLng = LatLng.from(37.517235, 127.047325)

    // ZoomLevel
    internal const val MinZoomLevel: Int = 6
    internal const val MaxZoomLevel: Int = 21
    private const val ZoomLevel: Int = 16

    // Camera Options
    internal const val TiltAngle: Double = 0.0
    internal const val RotationAngle: Double = 0.0
    internal const val Height: Double = -1.0

    // DefaultPosition
    public val DefaultCameraPosition: CameraPosition =
        cameraPosition {
            // GangNam Station
            setPosition(Position)
            // Default ZoomLevel
            setZoomLevel(ZoomLevel)
        }
}
