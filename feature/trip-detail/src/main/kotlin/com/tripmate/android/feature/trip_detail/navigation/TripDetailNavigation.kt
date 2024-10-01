package com.tripmate.android.feature.trip_detail.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.tripmate.android.feature.trip_detail.TripDetailRoute
import androidx.navigation.navArgument

const val SPOT_ID = "spot_Id"
const val TRIP_DETAIL_ROUTE = "trip_detail_route/{$SPOT_ID}"

fun NavController.navigateToTripDetail(spotId: String) {
    navigate("trip_detail_route/$spotId")
}

fun NavGraphBuilder.tripDetailNavGraph(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    navigateToMateRecruit: (String, String, String) -> Unit,
    navigateToMateReviewPost: (Int) -> Unit,
    navigateToReport: () -> Unit,
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
            navigateToMateRecruit = navigateToMateRecruit,
            navigateToMateReviewPost = navigateToMateReviewPost,
            navigateToReport = navigateToReport,
        )
    }
}
