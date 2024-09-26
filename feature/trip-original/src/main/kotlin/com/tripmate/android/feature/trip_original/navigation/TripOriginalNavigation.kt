package com.tripmate.android.feature.trip_original.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tripmate.android.feature.trip_original.TripOriginalRoute

const val SPOT_ID = "spot_id"
const val TRIP_ORIGINAL_ROUTE = "trip_original_route/{$SPOT_ID}"

fun NavController.navigateToTripOriginal(spotId: Int) {
    navigate("trip_original_route/$spotId")
}

fun NavGraphBuilder.tripOriginalNavGraph(
    padding: PaddingValues,
    navigateToMateList: () -> Unit,
//    popBackStack: () -> Unit
) {
    composable(
        route = TRIP_ORIGINAL_ROUTE,
        arguments = listOf(
            navArgument(SPOT_ID) {
                type = NavType.IntType
            },
        ),

    ) {
        TripOriginalRoute(
            innerPadding = padding,
            navigateToMateList = navigateToMateList,
        )
    }
}
