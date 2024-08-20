package com.tripmate.android.mate.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.mate.MateRoute

const val MATE_ROUTE = "mate_route"

fun NavController.navigateToMate(navOptions: NavOptions) {
    navigate(MATE_ROUTE, navOptions)
}

fun NavGraphBuilder.mateNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit,
) {
    composable(route = MATE_ROUTE) {
        MateRoute(
            innerPadding = padding,
        )
    }
}
