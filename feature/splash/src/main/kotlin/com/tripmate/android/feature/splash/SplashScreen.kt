package com.tripmate.android.feature.splash
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.component.LoadingWheel
import com.tripmate.android.feature.splash.viewmodel.SplashUiState
import com.tripmate.android.feature.splash.viewmodel.SplashViewModel

@Composable
internal fun SplashRoute(
    navigateToMain: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateTo) {
        if (uiState.navigateTo.isEmpty()) {
            viewModel.checkPersonalizationCompletion()
        } else {
            when (uiState.navigateTo) {
                "main" -> navigateToMain()
                "personalization" -> navigateToPersonalization()
            }
        }
    }

    SplashScreen(
        uiState = uiState,
    )
}

@Composable
internal fun SplashScreen(
    uiState: SplashUiState,
) {
    if (uiState.isLoading) {
        LoadingWheel(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        )
    }
}
