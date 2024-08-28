package com.tripmate.android.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tripmate.android.core.designsystem.component.TripmateButton

@Composable
internal fun HomeRoute(
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
) {
    HomeScreen(
        innerPadding = innerPadding,
        navigateToMateRecruit =  navigateToMateRecruit,
    )
}

@Composable
internal fun HomeScreen(
    innerPadding: PaddingValues,
    navigateToMateRecruit: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "홈화면",
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            TripmateButton(
                onClick = navigateToMateRecruit,
            ) {
                Text(text = "동행 모집")
            }
        }
    }
}
