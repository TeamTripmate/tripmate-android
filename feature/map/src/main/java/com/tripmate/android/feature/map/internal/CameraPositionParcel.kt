package com.tripmate.android.feature.map.internal

import android.os.Parcelable
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraPosition
import com.tripmate.android.feature.map.extension.cameraPosition
import kotlinx.parcelize.Parcelize


@Parcelize
public data class CameraPositionParcel(
    val position: LatLng,
    val zoomLevel: Int,
    val tiltAngle: Double,
    val rotationAngle: Double,
    val height: Double,
) : Parcelable

public fun CameraPosition.parcelize(): CameraPositionParcel =
    CameraPositionParcel(
        position = position,
        zoomLevel = zoomLevel,
        tiltAngle = tiltAngle,
        rotationAngle = rotationAngle,
        height = height,
    )

public fun CameraPositionParcel.restore(): CameraPosition {
    val parcel = this
    return cameraPosition {
        position = parcel.position
        zoomLevel = parcel.zoomLevel
        tiltAngle = parcel.tiltAngle
        rotationAngle = parcel.rotationAngle
        height = parcel.height
    }
}
