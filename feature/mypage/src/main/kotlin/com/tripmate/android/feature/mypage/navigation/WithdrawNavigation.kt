package com.tripmate.android.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.mypage.WithdrawRoute

const val WITHDRAW_ROUTE = "withdraw_route"

fun NavController.navigateToWithdraw() {
    navigate(WITHDRAW_ROUTE)
}

fun NavGraphBuilder.withdrawNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToMain: () -> Unit,
) {
    composable(route = WITHDRAW_ROUTE) {
        WithdrawRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
            navigateToMain = navigateToMain,
            navigateToLogin = navigateToLogin
        )
    }
}
