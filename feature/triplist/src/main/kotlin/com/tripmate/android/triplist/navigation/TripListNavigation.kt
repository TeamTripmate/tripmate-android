package com.tripmate.android.triplist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.triplist.TripListRoute

const val TRIPLIST_ROUTE = "tripList_route"

fun NavController.navigateToTripList(navOptions: NavOptions) {
    navigate(TRIPLIST_ROUTE, navOptions)
}

fun NavGraphBuilder.tripListNavGraph(
    padding: PaddingValues,
//    popBackStack: () -> Unit
) {
    composable(route = TRIPLIST_ROUTE) {
        TripListRoute(
            innerPadding = padding,
        )
    }
}
