package com.tripmate.android.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    MapScreen(
        state = state,
        modifier = modifier,
        toggleBars = viewModel::toggleBarShowingState,
    )
}

@Composable
private fun MapScreen(
    state: MapState,
    modifier: Modifier = Modifier,
    toggleBars: () -> Unit = {},
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { contentPadding ->

        Box(modifier = Modifier.padding(contentPadding)) {
            MapSection(
                modifier = Modifier.fillMaxSize(),
                toggleBars = toggleBars,
            )
        }
    }
}
