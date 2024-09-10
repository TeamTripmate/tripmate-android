package com.tripmate.android.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.tripmate.android.core.designsystem.theme.Gray001
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.navigator.LoginNavigator
import com.tripmate.android.feature.navigator.PersonalizationNavigator
import dagger.hilt.android.AndroidEntryPoint
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var personalizationNavigator: PersonalizationNavigator

    @Inject
    lateinit var loginNavigator: LoginNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController: MainNavController = rememberMainNavController()
            val systemUiController = rememberExSystemUiController()
            val isDarkTheme = isSystemInDarkTheme()

            DisposableEffect(systemUiController) {
                systemUiController.setSystemBarsColor(
                    color = if (isDarkTheme) Gray001 else Color.White,
                    darkIcons = !isDarkTheme,
                    isNavigationBarContrastEnforced = false,
                )

                onDispose {}
            }

            TripmateTheme {
                MainScreen(
                    navController = navController,
                    navigateToPersonalization = {
                        personalizationNavigator.navigateFrom(
                            activity = this@MainActivity,
                            withFinish = true,
                        )
                    },
                    navigateToLogin = {
                        loginNavigator.navigateFrom(
                            activity = this@MainActivity,
                            withFinish = true,
                        )
                    },
                )
            }
        }
    }
}
