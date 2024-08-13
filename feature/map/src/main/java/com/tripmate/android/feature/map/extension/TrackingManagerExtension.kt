package com.tripmate.android.feature.map.extension

import com.kakao.vectormap.label.Label
import com.kakao.vectormap.label.TrackingManager
import com.tripmate.android.feature.map.state.CameraPositionState

internal fun TrackingManager.startTrackingCompat(label: Label, cameraPositionState: CameraPositionState?) {
    startTracking(label)
    cameraPositionState?.onLabelTrackStarted(label.position)
}

internal fun TrackingManager.stopTrackingCompat(cameraPositionState: CameraPositionState?) {
    stopTracking()
    cameraPositionState?.onLabelTrackStopped()
}
