package com.tripmate.android.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeRoute(
    innerPadding: PaddingValues,
) {
    HomeScreen(
        innerPadding = innerPadding,
    )
}

@Composable
internal fun HomeScreen(
    innerPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Text(
            text = "홈화면",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 24.sp,
        )
    }
}
