package com.tripmate.android.writing

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
internal fun WritingRoute(
    innerPadding: PaddingValues,
) {
    WritingScreen(
        innerPadding = innerPadding,
    )
}

@Composable
internal fun WritingScreen(
    innerPadding: PaddingValues,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Text(
            text = "글쓰기 화면",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 24.sp,
        )
    }
}