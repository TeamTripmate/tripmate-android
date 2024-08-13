package com.tripmate.android.feature.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tripmate.android.core.designsystem.theme.TripmateTheme
import com.tripmate.android.feature.navigator.MainNavigator
import com.tripmate.android.feature.navigator.PersonalizationNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @Inject
    lateinit var mainNavigator: MainNavigator

    @Inject
    lateinit var personalizationNavigator: PersonalizationNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            TripmateTheme {
                LoginRoute(
                    navigateToMain = {
                        mainNavigator.navigateFrom(
                            activity = this,
                            withFinish = true,
                        )
                    },
                    navigateToPersonalization = {
                        personalizationNavigator.navigateFrom(
                            activity = this,
                            withFinish = true,
                        )
                    },
                )
            }
        }
    }
}
