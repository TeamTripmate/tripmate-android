package com.tripmate.android.feature.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tripmate.android.core.designsystem.component.LoadingIndicator
import com.tripmate.android.core.designsystem.theme.Background02
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.core.ui.DevicePreview
import com.tripmate.android.feature.splash.viewmodel.SplashUiState
import com.tripmate.android.feature.splash.viewmodel.SplashViewModel

@Composable
internal fun SplashRoute(
    navigateToLogin: () -> Unit,
    navigateToMain: () -> Unit,
    navigateToPersonalization: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateTo) {
        when (uiState.navigateTo) {
            "login" -> navigateToLogin()
            "main" -> navigateToMain()
            "personalization" -> navigateToPersonalization()
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02),
    ) {
        LoadingIndicator(
            isLoading = uiState.isLoading,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@DevicePreview
@Composable
private fun SplashScreenPreview() {
    TripmateTheme {
        SplashScreen(
            uiState = SplashUiState(isLoading = true),
        )
    }
}
