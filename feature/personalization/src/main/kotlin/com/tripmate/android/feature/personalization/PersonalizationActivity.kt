package com.tripmate.android.feature.personalization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.navigator.MainNavigator
import com.tripmate.android.feature.personalization.navigation.PERSONALIZATION_ROUTE
import com.tripmate.android.feature.personalization.navigation.Q2_ROUTE
import com.tripmate.android.feature.personalization.navigation.Q3_ROUTE
import com.tripmate.android.feature.personalization.navigation.Q4_ROUTE
import com.tripmate.android.feature.personalization.navigation.RESULT_ROUTE
import com.tripmate.android.feature.personalization.navigation.TRIP_STYLE_ROUTE
import com.tripmate.android.feature.personalization.navigation.USER_INFO_ROUTE
import com.tripmate.android.feature.personalization.navigation.personalizationNavGraph
import dagger.hilt.android.AndroidEntryPoint
import tech.thdev.compose.exteions.system.ui.controller.rememberExSystemUiController
import javax.inject.Inject

@AndroidEntryPoint
class PersonalizationActivity : ComponentActivity() {
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
//                PersonalizationRoute(
//                    navigateToMain = {
//                        mainNavigator.navigateFrom(
//                            activity = this,
//                            withFinish = true,
//                        )
//                    },
//                )
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = PERSONALIZATION_ROUTE,
                ) {
                    personalizationNavGraph(
                        padding = PaddingValues(),
                        navController = navController,
                        navigateToQ2 = { navController.navigate(Q2_ROUTE) },
                        navigateToQ3 = { navController.navigate(Q3_ROUTE) },
                        navigateToQ4 = { navController.navigate(Q4_ROUTE) },
                        navigateToTripStyle = { navController.navigate(TRIP_STYLE_ROUTE) },
                        navigateToUserInfo = { navController.navigate(USER_INFO_ROUTE) },
                        navigateToResult = { navController.navigate(RESULT_ROUTE) },
                        navigateToMain = {
                            mainNavigator.navigateFrom(
                                activity = this@PersonalizationActivity,
                                withFinish = true,
                            )
                        }
                    )
                }
            }
        }
    }
}
