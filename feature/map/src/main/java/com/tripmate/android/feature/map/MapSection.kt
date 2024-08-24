package com.tripmate.android.feature.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.tripmate.android.feature.map.settings.MapDirection
import com.tripmate.android.feature.map.settings.MapViewSettings
import com.tripmate.android.feature.map.settings.MapWidgetPosition
import com.tripmate.android.feature.map.state.CameraPositionState

@Composable
fun MapSection(
    modifier: Modifier = Modifier,
    toggleBars: () -> Unit = {},
    cameraPositionState: CameraPositionState,
) {
    val density = LocalDensity.current

    val mapViewSettings = remember {
        MapViewSettings(
            compassPosition = MapWidgetPosition(
                mapDirection = MapDirection.TopRight,
                xPx = with(density) { 8.dp.toPx() },
                yPx = with(density) { 100.dp.toPx() },
            ),
        )
    }
    KakaoMap(
        modifier = modifier,
        mapViewSettings = mapViewSettings,
        onMapClick = { _ ->
            toggleBars()
        },
        cameraPositionState = cameraPositionState
    )
}
