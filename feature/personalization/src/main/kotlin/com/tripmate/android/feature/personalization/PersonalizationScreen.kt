package com.tripmate.android.feature.personalization

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationUiState
import com.tripmate.android.feature.personalization.viewmodel.PersonalizationViewModel

@Composable
internal fun PersonalizationRoute(
    navigateToMain: () -> Unit,
    viewModel: PersonalizationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PersonalizationScreen(
        uiState = uiState,
        navigateToMain = navigateToMain,
    )
}

@Suppress("UnusedParameter")
@Composable
internal fun PersonalizationScreen(
    uiState: PersonalizationUiState,
    navigateToMain: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { navigateToMain() },
        ) {
            Text(
                text = "개인화 화면에서 메인화면으로",
                fontSize = 20.sp,
            )
        }
    }
}
