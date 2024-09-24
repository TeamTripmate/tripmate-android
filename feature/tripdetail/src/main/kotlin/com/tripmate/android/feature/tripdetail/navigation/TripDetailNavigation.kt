package com.tripmate.android.feature.tripdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tripmate.android.feature.tripdetail.TripDetailRoute

const val TRIP_DETAIL_ROUTE = "trip_detail_route"

fun NavController.navigateToTripDetail() {
    navigate(TRIP_DETAIL_ROUTE)
}

fun NavGraphBuilder.tripDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(route = TRIP_DETAIL_ROUTE) { entry ->
        TripDetailRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
