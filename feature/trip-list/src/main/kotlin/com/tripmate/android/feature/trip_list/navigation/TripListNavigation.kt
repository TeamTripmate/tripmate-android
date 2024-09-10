package com.tripmate.android.feature.trip_list.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.trip_list.TripListRoute

const val TRIP_LIST_ROUTE = "trip_list_route"

fun NavController.navigateToTripList(navOptions: NavOptions) {
    navigate(TRIP_LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.tripListNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit
) {
    composable(route = TRIP_LIST_ROUTE) {
        TripListRoute(
            innerPadding = padding,
        )
    }
}
