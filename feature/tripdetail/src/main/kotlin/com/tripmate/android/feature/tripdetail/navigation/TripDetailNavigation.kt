package com.tripmate.android.feature.tripdetail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.tripmate.android.feature.tripdetail.TripDetailRoute
import androidx.navigation.navArgument

const val SPOT_ID = "spot_Id"
const val TRIP_DETAIL_ROUTE = "trip_detail_route/{$SPOT_ID}"

fun NavController.navigateToTripDetail(spotId: String) {
    navigate("trip_detail_route/$spotId")
}

fun NavGraphBuilder.tripDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
) {
    composable(
        route = TRIP_DETAIL_ROUTE,
        arguments = listOf(
            navArgument(SPOT_ID) {
                type = NavType.StringType
            },
        ),
    ) {
        TripDetailRoute(
            innerPadding = padding,
            popBackStack = popBackStack,
        )
    }
}
