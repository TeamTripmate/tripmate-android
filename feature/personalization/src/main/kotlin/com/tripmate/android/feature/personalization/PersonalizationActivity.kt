package com.tripmate.android.feature.personalization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tripmate.android.core.designsystem.theme.Background01
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.navigator.MainNavigator
import com.tripmate.android.feature.personalization.navigation.PERSONALIZATION_ROUTE
import com.tripmate.android.feature.personalization.navigation.Question2_ROUTE
import com.tripmate.android.feature.personalization.navigation.Question3_ROUTE
import com.tripmate.android.feature.personalization.navigation.Question4_ROUTE
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
        enableEdgeToEdge()
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
                val navController = rememberNavController()

                Scaffold(
                    containerColor = Background01,
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = PERSONALIZATION_ROUTE,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        personalizationNavGraph(
                            innerPadding = innerPadding,
                            navController = navController,
                            navigateToQuestion2 = { navController.navigate(Question2_ROUTE) },
                            navigateToQuestion3 = { navController.navigate(Question3_ROUTE) },
                            navigateToQuestion4 = { navController.navigate(Question4_ROUTE) },
                            navigateToTripStyle = { navController.navigate(TRIP_STYLE_ROUTE) },
                            navigateToUserInfo = { navController.navigate(USER_INFO_ROUTE) },
                            navigateToResult = { navController.navigate(RESULT_ROUTE) },
                            navigateToMain = {
                                mainNavigator.navigateFrom(
                                    activity = this@PersonalizationActivity,
                                    withFinish = true,
                                )
                            },
                        )
                    }
                }
            }
        }
    }
}
