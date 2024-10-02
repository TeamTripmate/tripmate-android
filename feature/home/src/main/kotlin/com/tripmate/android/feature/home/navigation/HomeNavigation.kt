package com.tripmate.android.feature.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tripmate.android.feature.home.HomeRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToMateReview: () -> Unit,
    navigateToTripDetail: (spotId: String) -> Unit,
    navigateToMateReviewPost: (Int) -> Unit,
    navigateToReport: () -> Unit,
    navigateToTripOriginal: (Int) -> Unit,
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            innerPadding = padding,
            navigateToMateReview = navigateToMateReview,
            navigateToTripDetail = navigateToTripDetail,
            navigateToMateReviewPost = navigateToMateReviewPost,
            navigateToReport = navigateToReport,
            navigateToTripOriginal = navigateToTripOriginal,
        )
    }
}
