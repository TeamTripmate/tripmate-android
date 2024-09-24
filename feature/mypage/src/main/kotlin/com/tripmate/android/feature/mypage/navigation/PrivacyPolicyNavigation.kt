package com.tripmate.android.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.PrivacyPolicyRoute

const val PRIVACY_POLICY_ROUTE = "privacy_policy_route"

fun NavController.navigateToPrivacyPolicy(navOptions: NavOptions) {
    navigate(PRIVACY_POLICY_ROUTE, navOptions)
}

fun NavGraphBuilder.privacyPolicyNavGraph(
    popBackStack: () -> Unit,
) {
    composable(route = PRIVACY_POLICY_ROUTE) {
        PrivacyPolicyRoute(
            popBackStack = popBackStack,
        )
    }
}
