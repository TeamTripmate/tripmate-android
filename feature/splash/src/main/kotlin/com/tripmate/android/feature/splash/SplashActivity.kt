package com.tripmate.android.feature.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.navigator.PersonalizationNavigator
import com.tripmate.android.feature.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @Inject
    lateinit var personalizationNavigator: PersonalizationNavigator

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberExSystemUiController()

            DisposableEffect(systemUiController) {
                systemUiController.setSystemBarsColor(
                    color = Color.White,
                    darkIcons = true,
                    isNavigationBarContrastEnforced = false,
                )

                onDispose {}
            }

            TripmateTheme {
                SplashRoute(
                    navigateToPersonalization = {
                        personalizationNavigator.navigateFrom(
                            activity = this,
                            withFinish = true,
                        )
                    },
                    navigateToMain = {
                        mainNavigator.navigateFrom(
                            activity = this,
                            withFinish = true,
                        )
                    },
                )
            }
        }
    }
}
